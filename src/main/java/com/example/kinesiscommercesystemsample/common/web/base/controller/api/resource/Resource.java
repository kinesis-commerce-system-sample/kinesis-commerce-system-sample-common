package com.example.kinesiscommercesystemsample.common.web.base.controller.api.resource;

import com.example.kinesiscommercesystemsample.common.web.base.dto.Dto;

import java.util.List;

public interface Resource {

	String getRequestId();

	void setRequestId(String requestId);

	List<? extends Dto> getData();

	void setData(List<? extends Dto> data);

	String getMessage();

	void setMessage(String message);
}
