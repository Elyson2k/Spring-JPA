package com.vini.demo.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.vini.demo.entities.Product;
import com.vini.demo.repository.ProductRepository;

@Component // Registrando classe como um componente do Spring. Registradores: @Service, @Component, @Repository
public class ProductService {

	@Autowired // Dependencia da classe UseRepository, mas o Spring com o Autowired arruma essa
				// dependencia.
	private ProductRepository repository;

	// Método usado para retornar uma lista de usuario, como se fosse um SELECT *
	// FROM users.
	public List<Product> findAll() {
		return repository.findAll();
	}

	// Método usado para retornar um Product pelo ID.
	public Product findById(Long id) {
		Optional<Product> obj = repository.findById(id);
		return obj.get();
	}
}
