package com.betacom.backend.services.implementations;

import com.betacom.backend.dto.OrderDTO;
import com.betacom.backend.model.*;
import com.betacom.backend.repositories.*;
import com.betacom.backend.request.OrderItemRequest;
import com.betacom.backend.request.OrderRequest;
import com.betacom.backend.services.interfaces.OrderServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class OrderImpl implements OrderServices {

    @Autowired
    IOrderRepository  orderRep;

    @Autowired
    IAddressRepository addresRep;

    @Autowired
    ICustomerRepository customerRep;

    @Autowired
    ICartRepository cartRep;

    @Autowired
    IProductRepository prodRep;

    @Override
    public List<OrderDTO> list() {
        List<Order> orderList = orderRep.findAll();
        return orderList.stream().map(o-> new OrderDTO(o)).collect(Collectors.toList());
    }

    @Override
    public OrderDTO get(Long id) throws Exception {
        if(id == null){
            throw new Exception("missing-id");
        }
        Optional<Order> order = orderRep.findById(id);
        if(order.isEmpty())
            throw new Exception("not-found");

        OrderDTO orderDTO = new OrderDTO(order.get());

        return orderDTO;
    }

    @Override
    public List<OrderDTO> listByCustomer(Long customerId) {
        List<Order> orders = orderRep.findByCustomer_Id(customerId);
        return orders.stream().map(o-> new OrderDTO(o)).collect(Collectors.toList());
    }

    @Override
    public void create(OrderRequest req) throws Exception {
        if(missingparams(req))
            throw new Exception("missing-parameters");

        Optional<Customer> customerOptional = customerRep.findById(req.getCustomerId());
        if(customerOptional.isEmpty())
            throw new Exception("customer-not-found");

        Customer customer = customerOptional.get();

        Optional<Address> addressOptional = addresRep.findById(req.getAddressId());
        if(addressOptional.isEmpty())
            throw new Exception("address-not-found");
        Address address = addressOptional.get();

        Optional<Cart> cartOptional = cartRep.findById(req.getCartId());
        if(cartOptional.isEmpty())
            throw new Exception("cart-not-found");

        List<CartItem> cartItems = cartOptional.get().getItems();
        if(cartItems.isEmpty())
            throw new Exception("empty cart, cant proccess order");

       Order order = new Order();
       order.setCustomer(customer);
       order.setAddress(address);
       order.setOrderDate(new Date());

       order = orderRep.save(order);

       List<OrderItem> orderItems = new ArrayList<>();

       for(CartItem cartItem : cartItems){
            orderItems.add( new OrderItem(cartItem,order) );
       }

       order.setOrderItems(orderItems);

       orderRep.save(order);
    }

    private boolean missingparams(OrderRequest req) {
        return req.getAddressId() == null
                || req.getCartId() == null
                || req.getCustomerId() == null
                ;
    }

    @Override
    public void update(OrderRequest req) throws Exception {
        //per un update puo aver senso togliere un oggetto o aggiungerlo al ordine...[meglio altro metodo]
        //o cambiare indirizzo spedizione
        if(req.getId() == null){
            throw new Exception("missing-id");
        }
        if(req.getAddressId() == null){
            throw new Exception("missing-address");
        }
        Optional<Order> orderOptional = orderRep.findById(req.getId());
        if(orderOptional.isEmpty()){
            throw new Exception("not-found");
        }
        Order order = orderOptional.get();

        Optional<Address> addressOptional = addresRep.findById(req.getAddressId());
        if(addressOptional.isEmpty()){
            throw new Exception("address-not-found");
        }
        Address address = addressOptional.get();

        order.setAddress(address);
        orderRep.save(order);

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
    }


    @Override
    public void delete(Long id) throws Exception {
        if(id == null){
            throw new Exception("missing-id");
        }
        orderRep.deleteById(id);
    }
}
