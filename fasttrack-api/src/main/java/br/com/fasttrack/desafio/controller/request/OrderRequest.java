package br.com.fasttrack.desafio.controller.request;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import org.hibernate.annotations.NotFound;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class OrderRequest {

	@NotNull @NotEmpty @NotFound
	private String name;
	
	@NotNull @NotEmpty @NotFound
	private String description;
	
	@NotNull
	private Double total;
	
}
