package com.vini.demo.config;
import java.time.Instant;
import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Configuration;

import com.vini.demo.entities.Order;
import com.vini.demo.entities.User;
import com.vini.demo.entities.enums.OrderStatus;
import com.vini.demo.repository.OrderRepository;
import com.vini.demo.repository.UseRepository;

@Configuration // Para dizer que a classe é de configuração.
public class TestConfig implements CommandLineRunner{
	
	@Autowired // O Spring na hora que tiver rodando a aplicação, vai resolver o problema de dependencia.
	private UseRepository useRepository;
	
	@Autowired // O Spring na hora que tiver rodando a aplicação, vai resolver o problema de dependencia.
	private OrderRepository orderRepository;

	@Override
	public void run(String... args) throws Exception {
		
		User u1 = new User(null, "Maria Brown", "maria@gmail.com", "988888888", "123456"); 
		User u2 = new User(null, "Alex Green", "alex@gmail.com", "977777777", "123456");
		
		Order o1 = new Order(null, Instant.parse("2019-06-20T19:53:07Z"),OrderStatus.PAID, u1); 
		Order o2 = new Order(null, Instant.parse("2019-07-21T03:42:10Z"),OrderStatus.WAITING, u2); 
		Order o3 = new Order(null, Instant.parse("2019-07-22T15:21:22Z"),OrderStatus.WAITING,u1);
		
		// Comando para salvar no bando de dados, nesse caso estamos passando uma lista.
		useRepository.saveAll(Arrays.asList(u1,u2));
		orderRepository.saveAll(Arrays.asList(o1,o2,o3));
	}
	
}
