package com.betacom.backend.services.implementations.order;

import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.stream.Collectors;

import com.betacom.backend.repositories.order.IOrderItemRepository;
import com.betacom.backend.services.interfaces.messages.MessageServices;
import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;

import com.betacom.backend.dto.order.OrderItemDTO;
import com.betacom.backend.model.order.Order;
import com.betacom.backend.model.order.OrderItem;
import com.betacom.backend.model.products.Product;
import com.betacom.backend.repositories.order.IOrderRepository;
import com.betacom.backend.repositories.products.IProductRepository;
import com.betacom.backend.request.order.OrderItemRequest;
import com.betacom.backend.services.interfaces.order.OrderItemsServices;
import org.springframework.stereotype.Service;

@Service
public class OrderItemImpl implements OrderItemsServices {

	@Autowired
	IOrderRepository orderRep;

	@Autowired
	MessageServices msgS;

	@Autowired
	IOrderItemRepository orderItemRep;

	@Autowired
	IProductRepository prodRep;

	@Autowired
	Logger log;

	/**
	 * Recupera gli elementi di un ordine specifico.
	 * @param orderId L'ID dell'ordine.
	 * @return Una lista di OrderItemDTO.
	 * @throws Exception Se l'ID dell'ordine è mancante o gli elementi non esistono.
	 */
	@Override
	public List<OrderItemDTO> listByOrder(Long orderId) throws Exception {
		log.debug("OII.listByOrder: list req received");

		if(orderId == null){
			log.debug("OII.listByOrder: orderId is null");
			throw new Exception(msgS.getMessage("missing-id-get-orderiItem-by-orderId"));
		}

		List<OrderItem> orderItems = orderItemRep.findByOrder_id(orderId);
		if(orderItems.isEmpty()){
			throw new Exception(msgS.getMessage("does-not-exist-get-orderiItem-by-orderId"));
		}

		return orderItems.stream().map(oi ->
				new OrderItemDTO(oi)
		).collect(Collectors.toList());
	}

	/**
	 * Recupera un elemento di un ordine per ID.
	 * @param id L'ID dell'elemento dell'ordine.
	 * @return Un OrderItemDTO.
	 * @throws Exception Se l'ID è mancante o l'elemento non esiste.
	 */
	@Override
	public OrderItemDTO get(Long id) throws Exception {
		if(id == null){
			throw new Exception(msgS.getMessage("missing-id-get"));
		}
		Optional<OrderItem> orderItem = orderItemRep.findById(id);
		if(orderItem.isEmpty())
			throw new Exception(msgS.getMessage("does-not-exist-get"));

		return new OrderItemDTO(orderItem.get());
	}

	/**
	 * Aggiunge un elemento a un ordine.
	 * @param itemReq La richiesta contenente i dettagli dell'elemento da aggiungere.
	 * @throws Exception Se ci sono errori nella richiesta o mancano dati.
	 */
	@Override
	public void addItemToOrder(OrderItemRequest itemReq) throws Exception {
		log.debug("OII.addItemToOrder: add req received");

		if(itemReq == null || itemReq.getOrderId() == null || itemReq.getProductId() == null)
			throw new Exception(msgS.getMessage("bad-orderitem-add-request"));

		log.debug("OII.addItemToOrder: fetching order");
		Optional<Order> orderOptional = orderRep.findById(itemReq.getOrderId());
		if(orderOptional.isEmpty()){
			throw new Exception(msgS.getMessage("order-not-found-for-orderItem-add"));
		}
		Order order = orderOptional.get();

		log.debug("OII.addItemToOrder: fetching product");
		Optional<Product> productOptional = prodRep.findById(itemReq.getProductId());
		if(productOptional.isEmpty()){
			throw new Exception(msgS.getMessage("product-not-found-for-orderItem-add"));
		}
		Product product = productOptional.get();

		if(itemReq.getUnitPrice() == null){
			itemReq.setUnitPrice(product.getPrice());
		}

		if(itemReq.getQuantity() == null){
			itemReq.setQuantity(1);
		}

		log.debug("OII.addItemToOrder: checking stock");
		if(!product.removeStock(itemReq.getQuantity()))
			throw new Exception(msgS.getMessage("quantity-exceeds-stock-order-orderItems-add"));

		order.getOrderItems().stream()
				.filter(item -> item.getProduct().getId().equals(product.getId()))
				.findFirst()
				.ifPresentOrElse(
						existingItem -> existingItem.setQuantity(
								existingItem.getQuantity() + itemReq.getQuantity()
						),
						() -> order.getOrderItems().add(
								new OrderItem(product, order, itemReq.getQuantity(), itemReq.getUnitPrice())
						)
				);

		log.debug("OII.addItemToOrder: finalizing....");
		order.setTotalPrice(order.getTotalPrice() + (itemReq.getQuantity() * itemReq.getUnitPrice()));
		orderRep.save(order);
		prodRep.save(product);
		log.debug("OII.addItemToOrder: saved order");
	}

	/**
	 * Rimuove un elemento da un ordine.
	 * @param itemReq La richiesta contenente i dettagli dell'elemento da rimuovere.
	 * @throws Exception Se ci sono errori nella richiesta o mancano dati.
	 */
	@Override
	public void removeItemFromOrder(OrderItemRequest itemReq) throws Exception {
		log.debug("OII.removeItemFromOrder: remove req received");

		Objects.requireNonNull(itemReq, "Request cannot be null");
		Objects.requireNonNull(itemReq.getOrderId(), "Order ID cannot be null");
		Objects.requireNonNull(itemReq.getProductId(), "Product ID cannot be null");

		Order order = orderRep.findById(itemReq.getOrderId())
				.orElseThrow(() -> new Exception(msgS.getMessage("order-not-found-for-orderItem-remove")));

		Product product = prodRep.findById(itemReq.getProductId())
				.orElseThrow(() -> new Exception(msgS.getMessage("product-not-found-for-orderItem-remove")));

		List<OrderItem> orderItems = order.getOrderItems();

		int quantityToRemove = (itemReq.getQuantity() == null) ? 1 : itemReq.getQuantity();

		product.addStock(quantityToRemove);

		log.debug("OII.removeItemFromOrder: updating order and stock");

		OrderItem updateMe = orderItems.stream()
				.filter(o -> Objects.equals(o.getProduct().getId(), product.getId()))
				.findFirst()
				.orElseThrow(() -> new Exception(msgS.getMessage("product-not-found-in-orderItem-remove")));

		if (updateMe.getQuantity() > quantityToRemove) {
			updateMe.setQuantity(updateMe.getQuantity() - quantityToRemove);
		} else {
			orderItems.remove(updateMe);
		}

		orderRep.save(order);
		prodRep.save(product);
		log.debug("OII.removeItemFromOrder: saved order");

		if (order.getOrderItems().isEmpty()) {
			log.debug("OII.delete: deleting order because no orderitems");
			orderRep.delete(order);
		}
	}

	/**
	 * Elimina un elemento di un ordine per ID.
	 * @param id L'ID dell'elemento dell'ordine da eliminare.
	 * @throws Exception Se l'ID è mancante o l'elemento non esiste.
	 */
	@Override
	public void delete(Long id) throws Exception {
		log.debug("OII.delete: deleting req received");
		if(id == null){
			throw new Exception(msgS.getMessage("missing-id-delete"));
		}
		Optional<OrderItem> orderItem = orderItemRep.findById(id);

		log.debug("OII.delete: checking orderItem");
		if(orderItem.isEmpty())
			throw new Exception(msgS.getMessage("does-not-exist-delete"));

		log.debug("OII.delete: updating stock");
		Product prod = prodRep.findById(orderItem.get().getProduct().getId()).get();
		prod.addStock(orderItem.get().getQuantity());

		log.debug("OII.delete: updating order and stock");
		orderItemRep.deleteById(id);
		prodRep.save(prod);
		log.debug("OII.delete: deleted order");

		Order order = orderRep.findById(orderItem.get().getOrder().getId()).get();

		if(order.getOrderItems().isEmpty()){
			log.debug("OII.delete: deleting order because no orderitems");
			orderRep.delete(order);
		}
	}
}