package com.example.aulajpa.resources;

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

import com.example.aulajpa.entities.User;
import com.example.aulajpa.services.UserService;

@RestController
@RequestMapping(value = "/users")
public class UserResources {
	
	@Autowired
	private UserService service;
	
	@PostMapping // Mapeia requisições HTTP POST
    public ResponseEntity<User> insert(@RequestBody User obj) {
		// 1. Chama o Service para salvar o objeto no banco.
        // O Service retorna o objeto 'obj' com o ID preenchido.
        obj = service.insert(obj);
        
        // 2. Cria o URI (Localização) do novo recurso (Ex: /users/5)
        // Isso é o padrão REST para o status 201 Created.
        URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}")
                .buildAndExpand(obj.getId()).toUri();
        
        // 3. Retorna a resposta: 
        //    - Status 201 (Created)
        //    - Header Location: <URI do novo recurso>
        //    - Body: O objeto User salvo (com o ID)
        return ResponseEntity.created(uri).body(obj);
	}
	
	/**
     * Endpoint para atualizar os dados de um usuário existente.
     * Mapeado para requisições PUT em /users/{id}.
     * * @param id O ID do usuário a ser atualizado.
     * @param obj O objeto User com os novos dados, recebido no corpo da requisição (JSON).
     * @return ResponseEntity com status 200 OK e o objeto User atualizado.
     */
    @PutMapping(value = "/{id}") // Mapeia requisições HTTP PUT
    public ResponseEntity<User> update(@PathVariable Long id, @RequestBody User obj) {
        // 1. Chama o Service para realizar a atualização.
        // O Service retorna o objeto 'obj' atualizado e persistido.
        obj = service.update(id, obj);
        
        // 2. Retorna a resposta: 
        //    - Status 200 (OK) - Padrão para sucesso na atualização (PUT).
        //    - Body: O objeto User atualizado.
        return ResponseEntity.ok().body(obj);
    }
	
	@DeleteMapping(value = "/{id}") 
    public ResponseEntity<Void> delete(@PathVariable Long id) {
		service.delete(id);
		return ResponseEntity.noContent().build();
	}

	@GetMapping
	public ResponseEntity<List<User>> findAll() {
		List<User> list = service.findAll();
		return ResponseEntity.ok().body(list);
	}
	
	@GetMapping(value = "/{id}")
	public ResponseEntity<User> findById(@PathVariable Long id) {
		User obj = service.findById(id);
		return ResponseEntity.ok().body(obj);
	}
}
