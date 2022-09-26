/* 
 * 
 * 
 * Essa classe vai disponibilizar um recurso WEB correspondendo a classe User-
 * 
 * 
 * 
 * */

package com.vini.demo.resources;
import java.net.URI;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.vini.demo.entities.User;
import com.vini.demo.services.UserService;

@RestController // Para dizer que essa classe é um recurso WEB que é implementado por um cotrolador Rest.
@RequestMapping(value = "/users") // Serve para dizer o caminho do recurso.
public class UserResource {
	
	/* O UserService vai ser injetato automaticamente porque tem o @Autwired, so que para isso funcionar
	 * a classe UserService precisa ta resgristada como um componente do Spring, se registra com anotação */
	
	@Autowired 
	private UserService useService;
	
	// Para indicar que o metodo aqui, vai ser um metodo que responde a uma requisição do método get do http
	// A anotação @GetMapping faz com que o método findAll possa ser acessada pelo método GET através da url configurada na anotação @RequestMapping definida para a classe.
	
	@GetMapping
	public ResponseEntity<List<User>> findAll(){ // ResponseEntity é um tipo especifico do Spring para retornar resposta de uma requisição WEB.
		List<User> list = useService.findAll();
		return ResponseEntity.ok().body(list); // Retornando o objeto "list".
	}
	
	/* Nesse caso, eu vou passar na URL o valor do ID do usuario, para falar que minha URL vai ter um 
	 * parametro tbm, vou ter que abrir um parentese com o (value = "/{id}"), isso vai indicar que minha
	 * requisição vai aceitar um ID dentro da minha URL. */
	
	@GetMapping(value = "/{id}") // A anotação @GetMapping faz com que o método findById possa ser acessada pelo método GET através da url .../NumeroDoID configurada na anotação @RequestMapping definida para a classe.
	public ResponseEntity<User> findById(@PathVariable Long id){ // Para o spring aceitar o id, e consideralo como parametro que vai chegar na url, vai ter que colocar uma anotação.
		User obj = useService.findById(id);
		return ResponseEntity.ok().body(obj);
	}
	
	@PostMapping
	public ResponseEntity<User> insert(@RequestBody User obj){ // Para dizer que esse objeto vai chegar no Json, e esse Json vai ser deserializado para o abjeto User do meu java, vai precisar dessa anotação @RequestBody.
		obj = useService.insert(obj);
		
		/* Porque o padrao HTTP, quando você vai retornar um 201, é esperado que a resposta que a resposta
		 * contenha um cabeçalho contendo o endereço do recurso que você inseriu, e o SpringBoot tem uma
		 * forma padrão de gerar esse endereço. */
		
		// o .patch vai receber um padrão para montar minha sql, ele vai receber o id, e o buildAndExpand vai pegar o id inserido.
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(obj.getId()).toUri();
		return ResponseEntity.created(uri).body(obj);		
	}
	
	@DeleteMapping(value = "/{id}") // Anotação usada para deletar usuarios.
	public ResponseEntity<User> delete(@PathVariable Long id){
		useService.delete(id);
		
		// O noContent vai retornar para mim uma resposta vazia, e o codigo HTTP de uma reposta
		// que n tem conteudo, é o 204, ele ja trata isso para eu tbm.
		
		return ResponseEntity.noContent().build();
	}
	
	@PutMapping(value = "/{id}")
	public ResponseEntity<User> update(@PathVariable Long id,@RequestBody User obj){
		obj = useService.update(id, obj);
		return ResponseEntity.ok().body(obj);
	}
	
	
}
