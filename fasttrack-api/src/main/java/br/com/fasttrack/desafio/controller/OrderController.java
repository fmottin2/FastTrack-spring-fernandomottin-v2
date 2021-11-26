package br.com.fasttrack.desafio.controller;

import java.net.URI;
import java.util.List;

import javax.validation.Valid;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import br.com.fasttrack.desafio.controller.request.OrderRequest;
import br.com.fasttrack.desafio.dto.OrderDTO;
import br.com.fasttrack.desafio.entity.Order;
import br.com.fasttrack.desafio.exception.OrderNotExistsException;
import br.com.fasttrack.desafio.repository.filter.OrderFilter;
import br.com.fasttrack.desafio.service.OrderService;

@RestController
@RequestMapping("/orders")
public class OrderController {

	@Autowired
	private OrderService service;

	@Autowired
	private ModelMapper modelMapper;

	@GetMapping
	public List<OrderDTO> findAll() {
		return service.findAll();
	}
	
	@GetMapping(path = "/search")
	public List<OrderDTO> search(@Valid OrderFilter filter) {
		return service.filter(filter);
	}

	@PostMapping
	public ResponseEntity<OrderDTO> insert(@RequestBody @Valid OrderRequest orderRequest) throws Exception {
		OrderDTO orderDTO = service.insert(orderRequest);
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(orderDTO.getId())
				.toUri();
		return ResponseEntity.created(uri).body(orderDTO);
	}

	@GetMapping(value = "/{id}")
	public OrderDTO findById(@PathVariable Integer id) throws Exception {
		Order order = service.findById(id).orElseThrow(() -> new OrderNotExistsException("Order not exists"));
		return modelMapper.map(order, OrderDTO.class);
	}

	@PutMapping(value = "/{id}")
	public ResponseEntity<Object> update(@PathVariable Integer id, @RequestBody  @Valid OrderRequest orderRequest) throws Exception {
		OrderDTO orderDTO = service.update(id, orderRequest);
		return ResponseEntity.accepted().body(orderDTO);
	}

	@DeleteMapping(value = "/{id}")
	public ResponseEntity<Object> delete(@PathVariable("id") Integer id) throws Exception {
		service.findById(id).orElseThrow(() -> new OrderNotExistsException("Order not exists"));
		service.delete(id);
		return ResponseEntity.ok("Excluido com sucesso");
	}
}
