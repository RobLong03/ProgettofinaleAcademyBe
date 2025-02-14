package com.betacom.backend.dto.order;

import com.betacom.backend.dto.customer.AddressDTO;
import com.betacom.backend.dto.customer.CustomerDTO;
import com.betacom.backend.model.order.Order;

import java.util.Date;
import java.util.List;

public class OrderDTO {
    private Long id;
    private CustomerDTO customer;

    private AddressDTO address;
    private Date orderDate;

    private Double totalPrice;

    private List<OrderItemDTO> orderItemsList;

    public OrderDTO() {
    }

    public OrderDTO(Long id, CustomerDTO customer, AddressDTO address, Date orderDate, Double totalPrice, List<OrderItemDTO> orderItemsList) {
        this.id = id;
        this.customer = customer;
        this.address = address;
        this.orderDate = orderDate;
        this.totalPrice = totalPrice;
        this.orderItemsList = orderItemsList;
    }

    public OrderDTO(Order order) {
        this.id = order.getId();

		this.address = new AddressDTO(order.getAddress());
		this.customer = new CustomerDTO(order.getCustomer());
        this.orderDate = order.getOrderDate();
        this.totalPrice = order.getTotalPrice();
        this.orderItemsList = order.getOrderItems().stream()
                .map(OrderItemDTO::new)
                .toList();
    }

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public CustomerDTO getCustomer() {
		return customer;
	}

	public void setCustomer(CustomerDTO customer) {
		this.customer = customer;
	}

	public AddressDTO getAddress() {
		return address;
	}

	public void setAddress(AddressDTO address) {
		this.address = address;
	}

	public Date getOrderDate() {
		return orderDate;
	}

	public void setOrderDate(Date orderDate) {
		this.orderDate = orderDate;
	}

	public Double getTotalPrice() {
		return totalPrice;
	}

	public void setTotalPrice(Double totalPrice) {
		this.totalPrice = totalPrice;
	}

	public List<OrderItemDTO> getOrderItemsList() {
		return orderItemsList;
	}

	public void setOrderItemsList(List<OrderItemDTO> orderItemsList) {
		this.orderItemsList = orderItemsList;
	}

	@Override
	public String toString() {
		return "OrderDTO{" +
				"id=" + id +
				", customer=" + customer +
				", address=" + address +
				", orderDate=" + orderDate +
				", totalPrice=" + totalPrice +
				", orderItemsList=" + orderItemsList +
				'}';
	}
}
