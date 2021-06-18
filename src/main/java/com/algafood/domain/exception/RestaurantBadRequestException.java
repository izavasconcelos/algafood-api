package com.algafood.domain.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.BAD_REQUEST)
public class RestaurantBadRequestException extends RuntimeException {
	public RestaurantBadRequestException(String message) {
		super(message);
	}
}
