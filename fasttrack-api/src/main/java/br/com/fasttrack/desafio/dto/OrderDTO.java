package br.com.fasttrack.desafio.dto;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;

import br.com.fasttrack.desafio.entity.StatusOrder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter

public class OrderDTO {

	private Integer id;
	
	@NotNull @NotEmpty
	private String name;
	
	@NotNull @NotEmpty
	private String description;
	
	@NotNull @Positive
	private Double total;

	private StatusOrder status;
	
	public OrderDTO() {
		this.status = StatusOrder.NOT_PROCESSED;
	}
	
	
}
