package com.example.kinesiscommercesystemsample.common.exception;

public class ProceededMessageException extends RuntimeException {

	public ProceededMessageException() {
	}

	public ProceededMessageException(String message) {
		super(message);
	}

	public ProceededMessageException(String message, Throwable cause) {
		super(message, cause);
	}
}
