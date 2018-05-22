package com.example.kinesiscommercesystemsample.common.web.base.controller.api.resource;

import com.example.kinesiscommercesystemsample.common.web.base.dto.Dto;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.util.List;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class ResourceImpl implements Resource {

	@JsonProperty("request_id")
	String requestId;

	@JsonProperty("data")
	List<? extends Dto> data;

	@JsonProperty("message")
	String message;
}
