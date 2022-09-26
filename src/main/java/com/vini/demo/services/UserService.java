package com.vini.demo.services;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.vini.demo.entities.User;
import com.vini.demo.repository.UseRepository;

@Component // Registrando classe como um componente do Spring. Registradores: @Service, @Component, @Repository
public class UserService {
	
	@Autowired // Dependencia da classe UseRepository, mas o Spring com o Autowired arruma essa dependencia.
	private UseRepository repository;
	
	// Método usado para retornar uma lista de usuario, como se fosse um SELECT * FROM users.
	public List<User> findAll(){
		return repository.findAll();		
	}
	
	// Método usado para retornar um User pelo ID.
	public User findById(Long id) {
		Optional<User> obj = repository.findById(id);
		return obj.get();
	}
	
	public User insert(User obj) {
		return repository.save(obj);
	}
	
	public void delete(Long obj) {
		repository.deleteById(obj);
		
	}
}
