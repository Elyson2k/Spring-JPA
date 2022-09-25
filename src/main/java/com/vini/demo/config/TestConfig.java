package com.vini.demo.config;

import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

import com.vini.demo.entities.User;
import com.vini.demo.repository.UseRepository;

@Configuration // Para dizer que a classe é de configuração.
public class TestConfig implements CommandLineRunner{
	
	@Autowired // O Spring na hora que tiver rodando a aplicação, vai resolver o problema de dependencia.
	private UseRepository useRepository;

	@Override
	public void run(String... args) throws Exception {
		
		User u1 = new User(null, "Maria Brown", "maria@gmail.com", "988888888", "123456"); 
		User u2 = new User(null, "Alex Green", "alex@gmail.com", "977777777", "123456");
		
		// Comando para salvar no bando de dados, nesse caso estamos passando uma lista.
		useRepository.saveAllAndFlush(Arrays.asList(u1,u2));
	}
	
}
