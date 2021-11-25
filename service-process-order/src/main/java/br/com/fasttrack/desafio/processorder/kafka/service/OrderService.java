package br.com.fasttrack.desafio.processorder.kafka.service;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.fasttrack.desafio.processorder.dto.OrderDTO;
import br.com.fasttrack.desafio.processorder.entity.Order;
import br.com.fasttrack.desafio.processorder.entity.StatusOrder;
import br.com.fasttrack.desafio.processorder.repository.Orders;

@Service
public class OrderService {

	@Autowired
	Orders repository;
	
	@Autowired
	ModelMapper modelMapper;
	
	public void process(OrderDTO orderDto) {
		Order order = modelMapper.map(orderDto, Order.class);
		order.setStatus(StatusOrder.PROCESSED);
		repository.save(order);
	}
}
