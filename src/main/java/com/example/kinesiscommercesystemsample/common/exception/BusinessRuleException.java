package com.example.kinesiscommercesystemsample.common.exception;

public class BusinessRuleException extends RuntimeException {

	public BusinessRuleException() {
	}

	public BusinessRuleException(String message) {
		super(message);
	}

	public BusinessRuleException(String message, Throwable cause) {
		super(message, cause);
	}
}
