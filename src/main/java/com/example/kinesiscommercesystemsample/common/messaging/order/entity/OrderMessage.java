package com.example.kinesiscommercesystemsample.common.messaging.order.entity;

import com.example.kinesiscommercesystemsample.common.messaging.Message;

public interface OrderMessage extends Message {

	String getOrderId();

	void setOrderId(String orderId);
}
