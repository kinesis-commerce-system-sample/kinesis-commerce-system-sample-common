package com.example.kinesiscommercesystemsample.common.web.base;

import com.example.kinesiscommercesystemsample.common.web.base.aop.RequestTrackingHandlerInterceptor;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.val;
import org.modelmapper.ModelMapper;
import org.springframework.boot.context.embedded.ConfigurableEmbeddedServletContainer;
import org.springframework.boot.context.embedded.EmbeddedServletContainerCustomizer;
import org.springframework.boot.web.servlet.ErrorPage;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.MessageSource;
import org.springframework.context.annotation.Bean;
import org.springframework.core.Ordered;
import org.springframework.http.HttpStatus;
import org.springframework.validation.beanvalidation.LocalValidatorFactoryBean;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.filter.CorsFilter;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

import java.util.List;

public abstract class BaseApplicationConfig  extends WebMvcConfigurerAdapter implements EmbeddedServletContainerCustomizer {

	@Override
	public void customize(ConfigurableEmbeddedServletContainer container) {
		container.addErrorPages(new ErrorPage(HttpStatus.NOT_FOUND, "/notfound"));
		container.addErrorPages(new ErrorPage(HttpStatus.INTERNAL_SERVER_ERROR, "/error"));
	}

	@Bean
	public FilterRegistrationBean corsFilter() {
		val config = new CorsConfiguration();
		config.setAllowCredentials(true);
		config.setAllowedOrigins(getCorsAllowedOrigins());
		config.setMaxAge(86400L);

		val allowedHeaders = getCorsAllowedHeaders();
		config.setAllowedHeaders(allowedHeaders);

		val allowedMethods = getCorsAllowedMethods();
		config.setAllowedMethods(allowedMethods);

		val source = new UrlBasedCorsConfigurationSource();
		source.registerCorsConfiguration("/**", config);

		val bean = new FilterRegistrationBean(new CorsFilter(source));
		bean.setOrder(Ordered.HIGHEST_PRECEDENCE);
		return bean;
	}

	protected abstract List<String> getCorsAllowedOrigins();

	protected abstract List<String> getCorsAllowedHeaders();

	protected abstract List<String > getCorsAllowedMethods();

	@Bean
	public LocalValidatorFactoryBean beanValidator(MessageSource messageSource) {
		val bean = new LocalValidatorFactoryBean();
		bean.setValidationMessageSource(messageSource);
		return bean;
	}

	private RequestTrackingHandlerInterceptor requestTrackingHandlerInterceptor() {
		return new RequestTrackingHandlerInterceptor();
	}

	@Override
	public void addInterceptors(InterceptorRegistry registry) {
		// 順序に意味がある
		registry.addInterceptor(requestTrackingHandlerInterceptor());
	}

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
