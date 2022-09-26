/* 
 * 
 * 
 * Essa classe vai disponibilizar um recurso WEB correspondendo a classe Product-
 * 
 * 
 * 
 * */

package com.vini.demo.resources;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.vini.demo.entities.Product;
import com.vini.demo.services.ProductService;

@RestController // Para dizer que essa classe é um recurso WEB que é implementado por um cotrolador Rest.
@RequestMapping(value = "/products") // Serve para dizer o caminho do recurso.
public class ProductResources {
	
	/* O ProductService vai ser injetato automaticamente porque tem o @Autwired, so que para isso funcionar
	 * a classe ProductService precisa ta resgristada como um componente do Spring, se registra com anotação */
	
	@Autowired 
	private ProductService productService;
	
	// Para indicar que o metodo aqui, vai ser um metodo que responde a uma requisição do método get do http
	// A anotação @GetMapping faz com que o método findAll possa ser acessada pelo método GET através da url configurada na anotação @RequestMapping definida para a classe.
	
	@GetMapping
	public ResponseEntity<List<Product>> findAll(){ // ResponseEntity é um tipo especifico do Spring para retornar resposta de uma requisição WEB.
		List<Product> list = productService.findAll();
		return ResponseEntity.ok().body(list); // Retornando o objeto "list".
	}
	
	/* Nesse caso, eu vou passar na URL o valor do ID do usuario, para falar que minha URL vai ter um 
	 * parametro tbm, vou ter que abrir um parentese com o (value = "/{id}"), isso vai indicar que minha
	 * requisição vai aceitar um ID dentro da minha URL. */
	
	@GetMapping(value = "/{id}") // A anotação @GetMapping faz com que o método findById possa ser acessada pelo método GET através da url .../NumeroDoID configurada na anotação @RequestMapping definida para a classe.
	public ResponseEntity<Product> findById(@PathVariable Long id){ // Para o spring aceitar o id, e consideralo como parametro que vai chegar na url, vai ter que colocar uma anotação.
		Product obj = productService.findById(id);
		return ResponseEntity.ok().body(obj);
	}
	
	
}
