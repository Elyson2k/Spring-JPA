package com.vini.demo.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.vini.demo.entities.Category;
import com.vini.demo.repository.CategoryRepository;

@Component // Registrando classe como um componente do Spring. Registradores: @Service, @Component, @Repository
public class CategoryService {

	@Autowired // Dependencia da classe UseRepository, mas o Spring com o Autowired arruma essa
				// dependencia.
	private CategoryRepository repository;

	// Método usado para retornar uma lista de usuario, como se fosse um SELECT *
	// FROM users.
	public List<Category> findAll() {
		return repository.findAll();
	}

	// Método usado para retornar um Category pelo ID.
	public Category findById(Long id) {
		Optional<Category> obj = repository.findById(id);
		return obj.get();
	}
}
