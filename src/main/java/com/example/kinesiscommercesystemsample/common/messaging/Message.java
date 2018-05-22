package com.example.kinesiscommercesystemsample.common.messaging;

import com.fasterxml.jackson.annotation.JsonTypeInfo;

@JsonTypeInfo(use=JsonTypeInfo.Id.CLASS)
public interface Message {

	String getMessageId();

	void setMessageId(String messageId);
}
