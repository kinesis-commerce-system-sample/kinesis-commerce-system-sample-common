package com.example.kinesiscommercesystemsample.common.messaging.inventory.entity.v1;

import com.example.kinesiscommercesystemsample.common.messaging.inventory.entity.InventoryMessage;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class InventoryOutboundMessage implements InventoryMessage {

	@JsonProperty("message_id")
	private String messageId;

	@JsonProperty("item_id")
	private String itemId;

	@JsonProperty("quantity")
	private Integer quantity;

	@JsonProperty("order_id")
	private String orderId;
}
