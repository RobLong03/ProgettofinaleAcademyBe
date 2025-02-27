package com.betacom.backend.utils;

public enum OrderStatusEnum {
	PENDING,          // Order has been received but not yet confirmed
	PREPARING,        // Items or services are being prepared
	SHIPPED,          // Order has been shipped to the customer
	DELIVERED,        // Customer has received the order
	CANCELED,         // Order was canceled before shipping
	RETURNED,         // Customer has returned the order
	REFUNDED          // Payment has been refunded
}