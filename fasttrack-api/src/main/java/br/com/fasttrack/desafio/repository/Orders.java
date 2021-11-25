package br.com.fasttrack.desafio.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.fasttrack.desafio.entity.Order;
import br.com.fasttrack.desafio.repository.helper.order.OrdersQueries;

@Repository
public interface Orders extends JpaRepository<Order, Integer>, OrdersQueries{

	
}
