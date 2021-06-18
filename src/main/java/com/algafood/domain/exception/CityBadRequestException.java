package com.algafood.domain.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.BAD_REQUEST)
public class CityBadRequestException extends RuntimeException {
	public CityBadRequestException(String message) {
		super(message);
	}
}
