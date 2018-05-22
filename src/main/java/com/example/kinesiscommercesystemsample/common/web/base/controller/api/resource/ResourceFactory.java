package com.example.kinesiscommercesystemsample.common.web.base.controller.api.resource;

import com.example.kinesiscommercesystemsample.common.web.base.aop.RequestTrackingHandlerInterceptor.RequestTrackinginfoHolder;
import lombok.val;

public class ResourceFactory {

	public static Resource create() {
		val resource = new ResourceImpl();
		resource.setRequestId(RequestTrackinginfoHolder.getRequestId());
		return resource;
	}
}
