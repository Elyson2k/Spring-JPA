/* 
 * 
 * 
 * Essa classe vai disponibilizar um recurso WEB correspondendo a classe User-
 * 
 * 
 * 
 * */

package com.vini.demo.resources;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.vini.demo.entities.User;

@RestController // Para dizer que essa classe é um recurso WEB que é implementado por um cotrolador Rest.
@RequestMapping(value = "/users") // Serve para dizer o caminho do recurso.
public class UserResource {
	
	// Para indicar que o metodo aqui vai ser um metodo que responde a uma requisição do método get do http
	@GetMapping
	public ResponseEntity<User> findAll(){ // ResponseEntity é um tipo especifico do Spring para retornar resposta de uma requisição WEB.
		User u = new User(1L,"M","M@Gmail.com","992139", "123241");
		return ResponseEntity.ok().body(u); // Retornando o objeto "u".
	}
}
