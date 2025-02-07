package com.betacom.backend.services.implementations.order;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.betacom.backend.dto.order.OrderDTO;
import com.betacom.backend.model.cart.Cart;
import com.betacom.backend.model.cart.CartItem;
import com.betacom.backend.model.customer.Address;
import com.betacom.backend.model.customer.Customer;
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
import com.betacom.backend.services.interfaces.order.OrderServices;



@Service
public class OrderImpl implements OrderServices {

    @Autowired
    Logger log;

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
        log.debug("OI: Order request:"+req);
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


        //prendo prodotto, controllo stock, avviso se non abbastanza, rimmovo se abbastanza
        Optional<Product> productOpt;
        Product prod;
        List<Product> toSaveAfterOrder = new ArrayList<Product>();
       for(CartItem cartItem : cartItems){
           productOpt = prodRep.findById(cartItem.getProduct().getId());
           if(productOpt.isEmpty())
               throw new Exception("product-not-found");

           prod = productOpt.get();

           if(cartItem.getQuantity()>prod.getStock())
               throw new Exception("not enough stock");

           if(!prod.removeStock(cartItem.getQuantity()))
               throw new Exception("not enough stock");

           toSaveAfterOrder.add(prod);

            orderItems.add( new OrderItem(cartItem,order) );


       }

       order.setOrderItems(orderItems);

       orderRep.save(order);

       prodRep.saveAll(toSaveAfterOrder);



    }

    private boolean missingparams(OrderRequest req) {
        return     req.getAddressId() == null
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
    public void delete(Long id) throws Exception {
        if(id == null){
            throw new Exception("missing-id");
        }

        Optional<Order> orderOpt = orderRep.findById(id);
        if(orderOpt.isEmpty())
            throw new Exception("not-found");
        Order order = orderOpt.get();
        List<Product> lp = new ArrayList<Product>();

        Product product;
        Optional<Product> productOptional;
        for(OrderItem orderItem : order.getOrderItems()){
            product = prodRep.findById(orderItem.getProduct().getId()).get();
            product.addStock(orderItem.getQuantity());
        }

        orderRep.delete(order);
        prodRep.saveAll(lp);


    }
}
