package com.example.kinesiscommercesystemsample.common.messaging.purchase.entity;

import com.example.kinesiscommercesystemsample.common.messaging.Message;

public interface PurchaseMessage extends Message {

	String getPurchaseId();

	void setPurchaseId(String purchaseId);
}
