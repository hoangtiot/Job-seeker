package com.hoangdh.Jobseeker.controller;

import java.util.List;

import org.springframework.core.MethodParameter;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.server.ServerHttpRequest;
import org.springframework.http.server.ServerHttpResponse;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.ResponseBodyAdvice;

@ControllerAdvice
public class ResourceSizeAdvice implements ResponseBodyAdvice<List<?>>{

	@Override
	public boolean supports(MethodParameter returnType, Class<? extends HttpMessageConverter<?>> converterType) {
		// TODO Auto-generated method stub
		return ResponseEntity.class.isAssignableFrom(returnType.getParameterType());
	}

	@Override
	public List<?> beforeBodyWrite(List<?> body, MethodParameter returnType,
			MediaType selectedContentType, Class<? extends HttpMessageConverter<?>> selectedConverterType,
			ServerHttpRequest request, ServerHttpResponse response) {
		// TODO Auto-generated method stub
		response.getHeaders().add("X-Total-Count", String.valueOf(body.size()));
        return body;
	}

	

}
