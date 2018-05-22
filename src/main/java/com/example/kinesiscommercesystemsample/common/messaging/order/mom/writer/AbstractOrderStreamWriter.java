package com.example.kinesiscommercesystemsample.common.messaging.order.mom.writer;

import com.amazonaws.services.kinesis.AmazonKinesis;
import com.amazonaws.services.kinesis.AmazonKinesisClientBuilder;
import com.amazonaws.services.kinesis.model.PutRecordRequest;
import com.amazonaws.services.kinesis.model.PutRecordsRequest;
import com.amazonaws.services.kinesis.model.PutRecordsRequestEntry;
import com.example.kinesiscommercesystemsample.common.messaging.KinesisWriter;
import com.example.kinesiscommercesystemsample.common.messaging.order.entity.OrderMessage;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import lombok.val;
import org.springframework.beans.factory.annotation.Autowired;

import java.nio.ByteBuffer;

@Slf4j
public abstract class AbstractOrderStreamWriter implements KinesisWriter<OrderMessage> {

	@Autowired
	private ObjectMapper objectMapper;

	private AmazonKinesis kinesisClient = AmazonKinesisClientBuilder.standard().build();

	@Override
	public void write(OrderMessage message) {

		try {
			val streamName = getStreamName();
			val partitionKey = message.getOrderId();
			val json = objectMapper.writeValueAsString(message);

			val putRecordRequest = new PutRecordRequest();
			putRecordRequest.setStreamName(streamName);
			putRecordRequest.setPartitionKey(partitionKey);
			putRecordRequest.setData(ByteBuffer.wrap(json.getBytes()));

			if (log.isDebugEnabled()) {
				log.debug("streamName={}, partitionKey={}, json={}, jsonSize={}", streamName, partitionKey, json, json.getBytes().length);
			}

			val putRecordResult = kinesisClient.putRecord(putRecordRequest);

			if (log.isDebugEnabled()) {
				log.debug("putRecordResult={}", putRecordResult);
			}

		} catch (Exception e) {
			throw new RuntimeException(e);
		}

	}

}
