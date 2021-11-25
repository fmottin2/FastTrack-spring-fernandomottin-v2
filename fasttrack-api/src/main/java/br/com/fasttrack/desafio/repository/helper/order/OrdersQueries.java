package br.com.fasttrack.desafio.repository.helper.order;

import java.util.List;

import br.com.fasttrack.desafio.entity.Order;
import br.com.fasttrack.desafio.repository.filter.OrderFilter;

public interface OrdersQueries {

	public List<Order> filter(OrderFilter filter);
}
