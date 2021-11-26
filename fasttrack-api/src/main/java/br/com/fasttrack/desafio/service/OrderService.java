package br.com.fasttrack.desafio.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.fasttrack.desafio.controller.request.OrderRequest;
import br.com.fasttrack.desafio.dto.OrderDTO;
import br.com.fasttrack.desafio.entity.Order;
import br.com.fasttrack.desafio.entity.StatusOrder;
import br.com.fasttrack.desafio.exception.OrderNotExistsException;
import br.com.fasttrack.desafio.kafka.OrderProducer;
import br.com.fasttrack.desafio.repository.OrdersRepository;
import br.com.fasttrack.desafio.repository.filter.OrderFilter;

@Service
public class OrderService {

	@Autowired
	private OrdersRepository repository;
	
	@Autowired OrderProducer producer;
	
	@Autowired
	private ModelMapper modelMapper;
	
	public List<OrderDTO> findAll(){
		List<Order> orders =  repository.findAll();
		List<OrderDTO> list = new ArrayList<OrderDTO>();
		orders.forEach(order -> list.add(modelMapper.map(order,OrderDTO.class)));
		return list;
	}

	public OrderDTO insert(OrderRequest orderRequest) {
		Order orderSave = modelMapper.map(orderRequest, Order.class);
		orderSave.setStatus(StatusOrder.NOT_PROCESSED);
		Order orderResponse = repository.save(orderSave);
		OrderDTO orderDTOResponse =  modelMapper.map(orderResponse, OrderDTO.class);
		producer.send(orderDTOResponse);
		return orderDTOResponse;
	}
	
	public OrderDTO update(Integer id,OrderRequest orderRequest) throws OrderNotExistsException {
		Order orderFind = findById(id).orElseThrow(() -> new OrderNotExistsException("Order not exists"));
		Order order = modelMapper.map(orderRequest, Order.class);
		order.setId(id);
		order.setStatus(orderFind.getStatus());
		Order orderResponse = repository.save(order);
		return  modelMapper.map(orderResponse, OrderDTO.class);
	}

	public Optional<Order> findById(Integer id) {
		return repository.findById(id);
	}

	public void delete(Integer id) {
		repository.deleteById(id);
	}
	
	public List<OrderDTO> filter(OrderFilter filter) {
		List<Order> orders = repository.filter(filter);
		List<OrderDTO> list = new ArrayList<OrderDTO>();
		orders.forEach(order -> list.add(modelMapper.map(order,OrderDTO.class)));
		return list;
	}
}
