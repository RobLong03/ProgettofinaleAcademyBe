package com.betacom.backend.services.implementations.order;

import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.stream.Collectors;

import com.betacom.backend.repositories.order.IOrderItemRepository;
import com.betacom.backend.services.interfaces.messages.MessageServices;
import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;

import com.betacom.backend.dto.order.OrderDTO;
import com.betacom.backend.dto.order.OrderItemDTO;
import com.betacom.backend.model.order.Order;
import com.betacom.backend.model.order.OrderItem;
import com.betacom.backend.model.products.Product;
import com.betacom.backend.repositories.cart.ICartRepository;
import com.betacom.backend.repositories.customer.IAddressRepository;
import com.betacom.backend.repositories.customer.ICustomerRepository;
import com.betacom.backend.repositories.order.IOrderRepository;
import com.betacom.backend.repositories.products.IProductRepository;
import com.betacom.backend.request.order.OrderItemRequest;
import com.betacom.backend.request.order.OrderRequest;
import com.betacom.backend.services.interfaces.order.OrderItemsServices;
import com.betacom.backend.services.interfaces.order.OrderServices;
import org.springframework.stereotype.Service;

@Service
public class OrderItemimpl implements OrderItemsServices {
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

	@Override
	public List<OrderItemDTO> listByOrder(Long orderId) throws Exception {
		log.debug("OII.listByOrder: list req received");

		if(orderId == null){
			log.debug("OII.listByOrder: orderId is null");
			throw new Exception(msgS.getMessage("missing-id-get-orderiItem-by-orderId"));
		}

		List<OrderItem> orderItemss = orderItemRep.findByOrder_id(orderId);
		if(orderItemss.isEmpty()){
			throw new Exception(msgS.getMessage("does-not-exist-get-orderiItem-by-orderId"));
		}

		return orderItemss.stream().map( oi->
				new OrderItemDTO(oi)
		).collect(Collectors.toList());
	}

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

	@Override
	public void addItemToOrder(OrderItemRequest itemReq) throws Exception {
		//assumo che se in questa request viene indicato "x numero di item" sono "x numro di item da aggiungere",
		//quindi se gia presenti "3 cpu ryzen 200" e arriva addItemToOrder con quantity 2 diventano 5,

		log.debug("OII.addItemToOrder: add req received");

		if(itemReq == null || itemReq.getOrderId() == null || itemReq.getProductId() == null  )
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

		//controllo sullo stock se ce ne sono abbastanza, lo faccio qua perche in teoria dal salvataggio del ordine sono gia stati tolti i stock
		log.debug("OII.addItemToOrder: checking stock");
		if(!product.removeStock(itemReq.getQuantity()))
			throw new Exception(msgS.getMessage("quantity-exceeds-stock-order-orderItems-add"));

		List<OrderItem> orderItems = order.getOrderItems();



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
		order.setTotalPrice(order.getTotalPrice() + (itemReq.getQuantity()*itemReq.getUnitPrice()));
		orderRep.save(order);
		prodRep.save(product);
		log.debug("OII.addItemToOrder: saved order");

	}

	 @Override
	public void removeItemFromOrder(OrderItemRequest itemReq) throws Exception{log.debug("OII.removeItemFromOrder: remove req received");

// Validazione input
		 Objects.requireNonNull(itemReq, "Request cannot be null");
		 Objects.requireNonNull(itemReq.getOrderId(), "Order ID cannot be null");
		 Objects.requireNonNull(itemReq.getProductId(), "Product ID cannot be null");

// Recupero ordine e prodotto
		 Order order = orderRep.findById(itemReq.getOrderId())
				 .orElseThrow(() -> new Exception(msgS.getMessage("order-not-found-for-orderItem-remove")));

		 Product product = prodRep.findById(itemReq.getProductId())
				 .orElseThrow(() -> new Exception(msgS.getMessage("product-not-found-for-orderItem-remove")));

		 List<OrderItem> orderItems = order.getOrderItems();

// Imposto la quantità di default a 1 se nulla
		 int quantityToRemove = (itemReq.getQuantity() == null) ? 1 : itemReq.getQuantity();

// Aggiorno stock del prodotto
		 product.addStock(quantityToRemove);

		 log.debug("OII.removeItemFromOrder: updating order and stock");

// Trovo l'OrderItem corrispondente al prodotto nell'ordine
		 OrderItem updateMe = orderItems.stream()
				 .filter(o -> Objects.equals(o.getProduct().getId(), product.getId()))
				 .findFirst()
				 .orElseThrow(() -> new Exception(msgS.getMessage("product-not-found-in-orderItem-remove")));

// Rimuovo o aggiorno la quantità
		 if (updateMe.getQuantity() > quantityToRemove) {
			 updateMe.setQuantity(updateMe.getQuantity() - quantityToRemove);
		 } else {
			 orderItems.remove(updateMe);
		 }

// Salvataggio ordine e prodotto
		 orderRep.save(order);
		 prodRep.save(product);
		 log.debug("OII.removeItemFromOrder: saved order");

// Se l'ordine non ha più elementi, lo elimino
		 if (order.getOrderItems().isEmpty()) {
			 log.debug("OII.delete: deleting order because no orderitems");
			 orderRep.delete(order);
		 }

	 }

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

		//se un ordine rimane senza prodotti lo cancello del tutto
		Order order = orderRep.findById(orderItem.get().getOrder().getId()).get();

		if(order.getOrderItems().isEmpty()){
			log.debug("OII.delete: deleting order because no orderitems");
			orderRep.delete(order);
		}

	}

}
