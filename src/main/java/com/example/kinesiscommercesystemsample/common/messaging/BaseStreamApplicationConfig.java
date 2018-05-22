package com.example.kinesiscommercesystemsample.common.messaging;

import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import lombok.val;
import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Bean;

@Slf4j
public abstract class BaseStreamApplicationConfig {

	@Bean
	public ModelMapper modelMapper() {
		val modelMapper = new ModelMapper();

		return modelMapper;
	}

	@Bean
	public ObjectMapper objectMapper() {
		val objectMapper = new ObjectMapper();

		return objectMapper;
	}

}
