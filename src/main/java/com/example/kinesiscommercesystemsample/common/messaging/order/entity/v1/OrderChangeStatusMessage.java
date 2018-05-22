package com.example.kinesiscommercesystemsample.common.messaging.order.entity.v1;

import com.example.kinesiscommercesystemsample.common.messaging.order.entity.OrderMessage;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class OrderChangeStatusMessage implements OrderMessage {

	@JsonProperty("message_id")
	private String messageId;

	@JsonProperty("order_id")
	private String orderId;

	@JsonProperty("status")
	private String status;
}
