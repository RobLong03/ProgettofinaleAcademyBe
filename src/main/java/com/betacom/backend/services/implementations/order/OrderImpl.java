package com.betacom.backend.services.implementations.order;

import java.util.*;
import java.util.stream.Collectors;

import com.betacom.backend.services.interfaces.messages.MessageServices;
import com.betacom.backend.utils.OrderStatusEnum;
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

    /**
     * Recupera la lista di tutti gli ordini.
     * @return Una lista di OrderDTO.
     */
    @Override
    public List<OrderDTO> list() {
        log.debug("OI.list: list request received");
        List<Order> orderList = orderRep.findAll();
        return orderList.stream().map(o -> new OrderDTO(o)).collect(Collectors.toList());
    }

    /**
     * Recupera un ordine per ID.
     * @param id L'ID dell'ordine.
     * @return Un OrderDTO.
     * @throws Exception Se l'ID è mancante o l'ordine non esiste.
     */
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

    /**
     * Recupera la lista degli ordini per un cliente specifico.
     * @param customerId L'ID del cliente.
     * @return Una lista di OrderDTO.
     * @throws Exception Se l'ID del cliente è mancante.
     */
    @Override
    public List<OrderDTO> listByCustomer(Long customerId) throws Exception {
        log.debug("OI.listByCustomer: listByCustomer request received");
        if(customerId == null) {
            throw new Exception(msgS.getMessage("missing-id-get"));
        }
        List<Order> orders = orderRep.findByCustomer_Id(customerId);
        return orders.stream().map(o -> new OrderDTO(o)).collect(Collectors.toList());
    }

    /**
     * Crea un nuovo ordine.
     * @param req La richiesta contenente i dettagli dell'ordine.
     * @throws Exception Se mancano attributi o ci sono errori nella richiesta.
     */
    @Override
    public void create(OrderRequest req) throws Exception {
        log.debug("OI.create: Order request:" + req);
        if (missingparams(req)) {
            log.debug("OI.create: missing params");
            throw new Exception(msgS.getMessage("missing-attributes-create"));
        }

        Customer customer = customerRep.findById(req.getCustomerId())
                .orElseThrow(() -> new Exception(msgS.getMessage("customer-not-found-order-create")));

        Address address = addresRep.findById(req.getAddressId())
                .orElseThrow(() -> new Exception(msgS.getMessage("address-not-found-order-create")));

        Cart cart = cartRep.findByCustomer_id(req.getCustomerId())
                .orElseThrow(() -> new Exception(msgS.getMessage("cart-not-found-order-create")));

        List<CartItem> cartItems = cart.getItems();
        if (cartItems.isEmpty()) {
            throw new Exception(msgS.getMessage("no-items-in-cart-order-create"));
        }

        // Creazione ordine senza salvarlo subito
        Order order = new Order();
        order.setStatus(OrderStatusEnum.PENDING.toString());
        order.setCustomer(customer);
        order.setAddress(address);
        order.setOrderDate(new Date());

        // Elaborazione degli elementi dell'ordine
        List<OrderItem> orderItems = new ArrayList<>();
        List<Product> productsToUpdate = new ArrayList<>();
        double totalPrice = 0.0;

        for (CartItem cartItem : cartItems) {
            Product product = prodRep.findById(cartItem.getProduct().getId())
                    .orElseThrow(() -> new Exception(msgS.getMessage("product-not-found-for-order")));

            if (cartItem.getQuantity() > product.getStock()) {
                throw new Exception(msgS.getMessage("quantity-exceeds-stock-order"));
            }

            product.removeStock(cartItem.getQuantity());
            productsToUpdate.add(product);

            totalPrice += product.getPrice() * cartItem.getQuantity();
            orderItems.add(new OrderItem(cartItem, order));
        }

        // Salvataggio ordine completo
        order.setTotalPrice(totalPrice);
        order.setOrderItems(orderItems);
        orderRep.save(order);

        // Aggiornamento stock prodotti
        prodRep.saveAll(productsToUpdate);

        // Svuotamento carrello
        cart.getItems().clear();
        cartRep.save(cart);

        log.debug("OI.create: Order saved successfully");
    }

    /**
     * Controlla se mancano parametri nella richiesta.
     * @param req La richiesta dell'ordine.
     * @return true se mancano parametri, altrimenti false.
     */
    private boolean missingparams(OrderRequest req) {
        return req.getAddressId() == null
                || req.getCustomerId() == null;
    }

    /**
     * Aggiorna i dettagli di un ordine.
     * @param req La richiesta contenente i nuovi dettagli.
     * @throws Exception Se l'ID è mancante o l'ordine non esiste.
     */
    @Override
    public void update(OrderRequest req) throws Exception {
        log.debug("OI.update: Order request:" + req);

        if (req.getId() == null) {
            throw new Exception(msgS.getMessage("missing-id-update"));
        }
        log.debug("OI.update: fetching order address");
        if (req.getAddressId() == null) {
            throw new Exception(msgS.getMessage("missing-addressId-in-update-order"));
        }

        log.debug("OI.update: fetching order details");
        Order order = orderRep.findById(req.getId()).orElseThrow(()-> new Exception(msgS.getMessage("does-not-exist-update")));

        if(orderShipped(order)){
            throw new Exception(msgS.getMessage("order-already-shipped"));
        }

        Optional<Address> addressOptional = addresRep.findById(req.getAddressId());
        if (addressOptional.isEmpty()) {
            throw new Exception(msgS.getMessage("address-not-found-update-order"));
        }
        Address address = addressOptional.get();

        if(order.getAddress().getCustomer() != address.getCustomer()){
            throw new Exception(msgS.getMessage("order-update-different-customer-address"));
        }

        log.debug("OI.update: setting request address");
        order.setAddress(address);
        orderRep.save(order);
    }

    /**
     * Controlla se l'ordine è stato spedito.
     * @param order L'ordine.
     * @return true se l'ordine è stato spedito, altrimenti false.
     */
    private boolean orderShipped(Order order) {
        return !(Objects.equals(order.getStatus(), OrderStatusEnum.PENDING.toString())
                || Objects.equals(order.getStatus(), OrderStatusEnum.PREPARING.toString()));
    }

    /**
     * Elimina un ordine per ID.
     * @param id L'ID dell'ordine da eliminare.
     * @throws Exception Se l'ID è mancante o l'ordine non esiste.
     */
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

    /**
     * Aggiorna lo stato di un ordine.
     * @param req La richiesta contenente il nuovo stato.
     * @throws Exception Se mancano dati o l'ordine non esiste.
     */
    @Override
    public void updateStatus(OrderRequest req) throws Exception {
        if (req.getId() == null) {
            throw new Exception(msgS.getMessage("missing-id-update"));
        }
        if(req.getStatus() == null || req.getStatus().isBlank()){
            throw new Exception(msgS.getMessage("not-enough-data-update"));
        }

        Order order = orderRep.findById(req.getId()).orElseThrow(()-> new Exception(msgS.getMessage("does-not-exist-update")));
        order.setStatus(OrderStatusEnum.valueOf(req.getStatus()).toString());
        orderRep.save(order);
    }
}