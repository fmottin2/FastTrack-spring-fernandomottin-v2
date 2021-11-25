package br.com.fasttrack.desafio.config.validation;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class ExceptionResponse {

	@JsonProperty("status_code")
	Integer statusCode;
	
	String message;
}
