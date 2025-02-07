package com.betacom.backend.services.implementations.order;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import com.betacom.backend.repositories.order.IOrderItemRepository;
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
	IOrderItemRepository orderItemRep;

    @Autowired
    IProductRepository prodRep;

	@Autowired
	Logger log;
	
	@Override
	public List<OrderItemDTO> listByOrder(Long orderId) throws Exception {
		if(orderId == null){
			throw new Exception("missing-id");
		}

		List<OrderItem> orderItemss = orderItemRep.findByOrder_id(orderId);
		if(orderItemss.isEmpty()){
			throw new Exception("not-found");
		}

		return orderItemss.stream().map( oi->
				new OrderItemDTO(oi)
		).collect(Collectors.toList());
	}

	@Override
	public OrderItemDTO get(Long id) throws Exception {
		if(id == null){
			throw new Exception("missing-id");
		}
		Optional<OrderItem> orderItem = orderItemRep.findById(id);
		if(orderItem.isEmpty())
			throw new Exception("not-found");

        return new OrderItemDTO(orderItem.get());
	}

	@Override
	public void addItemToOrder(OrderItemRequest itemReq) throws Exception {
		//assumo che se in questa request viene indicato "x numero di item" sono "x numro di item da aggiungere",
		//quindi se gia presenti "3 cpu ryzen 200" e arriva addItemToOrder con quantity 2 diventano 5,

		if(itemReq == null || itemReq.getOrderId() == null || itemReq.getProductId() == null  )
			throw new Exception("request problem");

		Optional<Order> orderOptional = orderRep.findById(itemReq.getOrderId());
		if(orderOptional.isEmpty()){
			throw new Exception("not-found");
		}
		Order order = orderOptional.get();

		Optional<Product> productOptional = prodRep.findById(itemReq.getProductId());
		if(productOptional.isEmpty()){
			throw new Exception("product-not-found");
		}
		Product product = productOptional.get();

		//controllo sullo stock se ce ne sono abbastanza, lo faccio qua perche in teoria dal salvataggio del ordine sono gia stati tolti i stock
		if(!product.removeStock(itemReq.getQuantity()))
			throw new Exception("not enough products in stock");


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

		orderRep.save(order);
		prodRep.save(product);

	}
	
	 @Override
	public void removeItemFromOrder(OrderItemRequest itemReq) throws Exception{
	    //se viene chiesto di togliere piu di quanti sono presenti defaulto a togliere tutto
	
	    if(itemReq == null || itemReq.getOrderId() == null || itemReq.getProductId() == null  )
	        throw new Exception("request problem");
	
	    Optional<Order> orderOptional = orderRep.findById(itemReq.getOrderId());
	    if(orderOptional.isEmpty()){
	        throw new Exception("not-found");
	    }
	    Order order = orderOptional.get();
	
	    Optional<Product> productOptional = prodRep.findById(itemReq.getProductId());
	    if(productOptional.isEmpty()){
	        throw new Exception("product-not-found");
	    }
	    Product product = productOptional.get();
	
	    List<OrderItem> orderItems = order.getOrderItems();
	
	    Boolean foundItem = false;

		 product.addStock(itemReq.getQuantity());
	
	    for(OrderItem item : orderItems){
	
	        if(item.getProduct().getId().equals(product.getId())){
	            if(item.getQuantity() > itemReq.getQuantity()){
	                item.setQuantity(item.getQuantity() - itemReq.getQuantity());
	            }else{
	                orderItems.remove(item);
	            }
	            foundItem = true;
	            break;
	        }
	    }
	    if(!foundItem)
	        throw new Exception("item-not-found");
	
	    orderRep.save(order);
		prodRep.save(product);
	}

	@Override
	public void delete(Long id) throws Exception {
		if(id == null){
			throw new Exception("missing-id");
		}
		Optional<OrderItem> orderItem = orderItemRep.findById(id);

		if(orderItem.isEmpty())
			throw new Exception("not-found");

		Product prod = prodRep.findById(orderItem.get().getProduct().getId()).get();
		prod.addStock(orderItem.get().getQuantity());

		orderItemRep.deleteById(id);
		prodRep.save(prod);
		
	}

}
