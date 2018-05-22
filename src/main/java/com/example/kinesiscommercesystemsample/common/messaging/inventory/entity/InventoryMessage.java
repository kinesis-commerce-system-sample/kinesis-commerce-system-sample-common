package com.example.kinesiscommercesystemsample.common.messaging.inventory.entity;

import com.example.kinesiscommercesystemsample.common.messaging.Message;

public interface InventoryMessage extends Message {

	String getItemId();

	void setItemId(String itemId);

	Integer getQuantity();

	void setQuantity(Integer quantity);
}
