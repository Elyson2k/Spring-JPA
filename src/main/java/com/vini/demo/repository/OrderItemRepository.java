package com.vini.demo.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.vini.demo.entities.OrderItem;

// Interface que tem as operações para trabalhar com o usuario, no JPARepository, passa como primeiro
// paramentro a classe na qual deseja, depois o tipo da primary key.

public interface OrderItemRepository extends JpaRepository<OrderItem, Long>{
	
	//Essa classe tem metodos que servem para fazer servicos, porem eles ficam todos ocultos.
}
