package com.betacom.backend.services.implementations.order;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import com.betacom.backend.services.interfaces.messages.MessageServices;
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
    MessageServices msgS;

    @Autowired
    IOrderRepository orderRep;

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
        log.debug("OI.list: list request received");
        List<Order> orderList = orderRep.findAll();
        return orderList.stream().map(o -> new OrderDTO(o)).collect(Collectors.toList());
    }

    @Override
    public OrderDTO get(Long id) throws Exception {
        log.debug("OI.get: get request received");

        if (id == null) {
            throw new Exception(msgS.getMessage("missing-id-get"));
        }
        Optional<Order> order = orderRep.findById(id);
        if (order.isEmpty()) {
            log.debug("OI.get: Order not found");
            throw new Exception(msgS.getMessage("does-not-exist-get"));
        }

        OrderDTO orderDTO = new OrderDTO(order.get());
        log.debug("OI.get: OrderDTO received");
        return orderDTO;
    }

    @Override
    public List<OrderDTO> listByCustomer(Long customerId) {
        log.debug("OI.listByCustomer: listByCustomer request received");
        List<Order> orders = orderRep.findByCustomer_Id(customerId);
        return orders.stream().map(o -> new OrderDTO(o)).collect(Collectors.toList());
    }

    @Override
    public void create(OrderRequest req) throws Exception {
        log.debug("OI.create: Order request:" + req);
        if (missingparams(req)) {
            log.debug("OI.create: missing params");
            throw new Exception(msgS.getMessage("missing-attributes-create"));
        }

        Optional<Customer> customerOptional = customerRep.findById(req.getCustomerId());
        if (customerOptional.isEmpty()) {
            log.debug("OI.create: customer not found");
            throw new Exception(msgS.getMessage("customer-not-found-order-create"));
        }

        Customer customer = customerOptional.get();

        Optional<Address> addressOptional = addresRep.findById(req.getAddressId());
        if (addressOptional.isEmpty()) {
            log.debug("OI.create: address not found");
            throw new Exception(msgS.getMessage("address-not-found-order-create"));
        }
        Address address = addressOptional.get();

        Optional<Cart> cartOptional = cartRep.findById(req.getCartId());
        if (cartOptional.isEmpty()) {
            log.debug("OI.create: cart not found");
            throw new Exception(msgS.getMessage("cart-not-found-order-create"));
        }

        List<CartItem> cartItems = cartOptional.get().getItems();
        if (cartItems.isEmpty()) {
            log.debug("OI.create: cartItems not found");
            throw new Exception(msgS.getMessage("no-items-in-cart-order-create"));
        }

        Order order = new Order();
        order.setTotalPrice(0.0);
        order.setCustomer(customer);
        order.setAddress(address);
        order.setOrderDate(new Date());

        order = orderRep.save(order);

        List<OrderItem> orderItems = new ArrayList<>();


        //prendo prodotto, controllo stock, avviso se non abbastanza, rimmovo se abbastanza
        Optional<Product> productOpt;
        Product prod;
        List<Product> toSaveAfterOrder = new ArrayList<Product>();
        for (CartItem cartItem : cartItems) {
            log.debug("oi.create: fetching products");
            productOpt = prodRep.findById(cartItem.getProduct().getId());

            if (productOpt.isEmpty())
                throw new Exception(msgS.getMessage("product-not-found-for-order"));

            prod = productOpt.get();

            if (cartItem.getQuantity() > prod.getStock())
                throw new Exception(msgS.getMessage("quantity-exceeds-stock-order"));

            if (!prod.removeStock(cartItem.getQuantity()))
                throw new Exception(msgS.getMessage("quantity-exceeds-stock-order"));


            toSaveAfterOrder.add(prod);
            order.setTotalPrice(order.getTotalPrice() + cartItem.getPrice() * cartItem.getQuantity());
            orderItems.add(new OrderItem(cartItem, order));


        }
        log.debug("OI.create: fetched products, finishing order");
        order.setOrderItems(orderItems);

        orderRep.save(order);

        prodRep.saveAll(toSaveAfterOrder);


    }

    private boolean missingparams(OrderRequest req) {
        return req.getAddressId() == null
                || req.getCartId() == null
                || req.getCustomerId() == null
                ;
    }

    @Override
    public void update(OrderRequest req) throws Exception {
        log.debug("OI.update: Order request:" + req);
        //per un update puo aver senso togliere un oggetto o aggiungerlo al ordine...[meglio altro metodo]
        //o cambiare indirizzo spedizione
        if (req.getId() == null) {
            throw new Exception(msgS.getMessage("missing-id-update"));
        }
        log.debug("OI.update: fetching order address");
        if (req.getAddressId() == null) {
            throw new Exception(msgS.getMessage("missing-addressId-in-update-order"));
        }
        log.debug("OI.update: fetching order details");
        Optional<Order> orderOptional = orderRep.findById(req.getId());
        if (orderOptional.isEmpty()) {
            throw new Exception(msgS.getMessage("does-not-exist-update"));
        }
        Order order = orderOptional.get();

        Optional<Address> addressOptional = addresRep.findById(req.getAddressId());
        if (addressOptional.isEmpty()) {
            throw new Exception(msgS.getMessage("address-not-found-update-order"));
        }
        Address address = addressOptional.get();
        log.debug("OI.update: setting request address");
        order.setAddress(address);
        orderRep.save(order);

    }

    @Override
    public void delete(Long id) throws Exception {
        if (id == null) {
            throw new Exception(msgS.getMessage("missing-id-delete"));
        }

        log.debug("OI.delete: searching order");
        Optional<Order> orderOpt = orderRep.findById(id);
        if (orderOpt.isEmpty())
            throw new Exception(msgS.getMessage("does-not-exist-delete"));
        Order order = orderOpt.get();
        log.debug("OI.delete: found order.....updating product stock");
        List<Product> lp = new ArrayList<Product>();

        Product product;
        Optional<Product> productOptional;
        for (OrderItem orderItem : order.getOrderItems()) {
            log.debug("OI.delete: found order.....updating product stock");
            product = prodRep.findById(orderItem.getProduct().getId()).get();
            product.addStock(orderItem.getQuantity());
        }

        orderRep.delete(order);
        prodRep.saveAll(lp);

    }
}
