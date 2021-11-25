package br.com.fasttrack.desafio.processorder.dto;

import br.com.fasttrack.desafio.processorder.entity.StatusOrder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class OrderDTO {

	private Integer id;
	private String name;
	private String description;
	private Double total;
	private StatusOrder status;
}
