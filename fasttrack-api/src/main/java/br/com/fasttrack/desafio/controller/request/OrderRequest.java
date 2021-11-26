package br.com.fasttrack.desafio.controller.request;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;

import org.hibernate.annotations.NotFound;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class OrderRequest {

	@NotNull(message = "can not be null") @NotEmpty(message = "can not be empty") 
	private String name;
	
	@NotNull(message = "can not be null") @NotEmpty(message = "can not be empty") @NotFound
	private String description;
	
	@NotNull(message = "can not be null") @Positive(message = "must be positive")
	private Double total;
	
}
