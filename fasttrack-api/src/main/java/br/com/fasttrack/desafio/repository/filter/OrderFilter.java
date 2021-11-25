package br.com.fasttrack.desafio.repository.filter;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class OrderFilter {

	private Double min_total;
	private Double max_total;
	private String status;
	private String q;
	
}
