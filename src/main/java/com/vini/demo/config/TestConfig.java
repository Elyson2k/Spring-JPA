package com.vini.demo.config;
import java.time.Instant;
import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Configuration;

import com.vini.demo.entities.Category;
import com.vini.demo.entities.Order;
import com.vini.demo.entities.OrderItem;
import com.vini.demo.entities.Product;
import com.vini.demo.entities.User;
import com.vini.demo.entities.enums.OrderStatus;
import com.vini.demo.repository.CategoryRepository;
import com.vini.demo.repository.OrderItemRepository;
import com.vini.demo.repository.OrderRepository;
import com.vini.demo.repository.ProductRepository;
import com.vini.demo.repository.UseRepository;

@Configuration // Para dizer que a classe é de configuração.
public class TestConfig implements CommandLineRunner{
	
	@Autowired // O Spring na hora que tiver rodando a aplicação, vai resolver o problema de dependencia.
	private UseRepository useRepository;
	
	@Autowired // O Spring na hora que tiver rodando a aplicação, vai resolver o problema de dependencia.
	private OrderRepository orderRepository;
	
	@Autowired // O Spring na hora que tiver rodando a aplicação, vai resolver o problema de dependencia.
	private CategoryRepository categoryRepository;
	
	@Autowired // O Spring na hora que tiver rodando a aplicação, vai resolver o problema de dependencia.
	private ProductRepository productRepository;
	
	@Autowired // O Spring na hora que tiver rodando a aplicação, vai resolver o problema de dependencia.
	private OrderItemRepository orderItemRepository;

	@Override // O Spring na hora que tiver rodando a aplicação, vai resolver o problema de dependencia.
	public void run(String... args) throws Exception {
		
		User u1 = new User(null, "Maria Brown", "maria@gmail.com", "988888888", "123456"); 
		User u2 = new User(null, "Alex Green", "alex@gmail.com", "977777777", "123456");
		
		Order o1 = new Order(null, Instant.parse("2019-06-20T19:53:07Z"),OrderStatus.PAID, u1); 
		Order o2 = new Order(null, Instant.parse("2019-07-21T03:42:10Z"),OrderStatus.WAITING, u2); 
		Order o3 = new Order(null, Instant.parse("2019-07-22T15:21:22Z"),OrderStatus.WAITING,u1);
		
		
		useRepository.saveAll(Arrays.asList(u1,u2));
		orderRepository.saveAll(Arrays.asList(o1,o2,o3));
		
		
		Category cat1 = new Category(null, "Electronics"); 
		Category cat2 = new Category(null, "Books"); 
		Category cat3 = new Category(null, "Computers");
		
		Product p1 = new Product(null, "The Lord of the Rings", "Lorem ipsum dolor sit amet, consectetur.", 90.5, ""); 
		Product p2 = new Product(null, "Smart TV", "Nulla eu imperdiet purus. Maecenas ante.", 2190.0, ""); 
		Product p3 = new Product(null, "Macbook Pro", "Nam eleifend maximus tortor, at mollis.", 1250.0, ""); 
		Product p4 = new Product(null, "PC Gamer", "Donec aliquet odio ac rhoncus cursus.", 1200.0, ""); 
		Product p5 = new Product(null, "Rails for Dummies", "Cras fringilla convallis sem vel faucibus.", 100.99, ""); 
		
		// Comando para salvar no bando de dados, nesse caso estamos passando uma lista.
		
		categoryRepository.saveAll(Arrays.asList(cat1,cat2,cat3));
		productRepository.saveAll(Arrays.asList(p1,p2,p2,p4));
		
		// Relacionando categorias a produtos.
		p1.getCategories().add(cat2);
		p2.getCategories().add(cat1);
		p2.getCategories().add(cat3);
		p3.getCategories().add(cat3);
		p4.getCategories().add(cat3);
		p5.getCategories().add(cat2);
		
		productRepository.saveAll(Arrays.asList(p1,p2,p3,p4,p5));
		
		OrderItem oi1 = new OrderItem(o1, p1, 2, p1.getPrice()); 
		OrderItem oi2 = new OrderItem(o1, p3, 1, p3.getPrice()); 
		OrderItem oi3 = new OrderItem(o2, p3, 2, p3.getPrice()); 
		OrderItem oi4 = new OrderItem(o3, p5, 2, p5.getPrice()); 
		
		orderItemRepository.saveAll(Arrays.asList(oi1,oi2,oi3,oi4));
		
	}
	
}
