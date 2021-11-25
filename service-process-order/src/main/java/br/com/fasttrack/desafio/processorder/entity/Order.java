package br.com.fasttrack.desafio.processorder.entity;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Data;

@Entity
@Table(name = "orders")
@Data
public class Order {

	@Id
	protected Integer id;
	private String name;
	private String description;
	private Double total;
	private StatusOrder status;
	
}
