package com.example.kinesiscommercesystemsample.common.messaging;

public interface KinesisWriter<T extends Message> {

	String getStreamName();

	void setStreamName(String streamName);

	void write(T message);
}
