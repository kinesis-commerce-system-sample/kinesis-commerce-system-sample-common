package com.example.kinesiscommercesystemsample.common.messaging.purchase.entity.v1;

import com.example.kinesiscommercesystemsample.common.messaging.purchase.entity.PurchaseMessage;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class PurchaseChangeStatusMessage implements PurchaseMessage {


	@JsonProperty("message_id")
	private String messageId;

	@JsonProperty("purchase_id")
	private String purchaseId;

	@JsonProperty("status")
	private String status;
}
