package com.vini.demo.services;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.vini.demo.entities.Order;
import com.vini.demo.repository.OrderRepository;

@Component // Registrando classe como um componente do Spring. Registradores: @Service, @Component, @Repository
public class OrderService {
	
	@Autowired // Dependencia da classe UseRepository, mas o Spring com o Autowired arruma essa dependencia.
	private OrderRepository repository;
	
	// Método usado para retornar uma lista de usuario, como se fosse um SELECT * FROM orders.
	public List<Order> findAll(){
		return repository.findAll();		
	}
	
	// Método usado para retornar um Order pelo ID.
	public Order findById(Long id) {
		Optional<Order> obj = repository.findById(id);
		return obj.get();
	}
}
