package com.example.aulajpa.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import com.example.aulajpa.entities.User;
import com.example.aulajpa.repositories.UserRepository;
import com.example.aulajpa.services.exceptions.ResourceNotFoundException;

@Service
public class UserService {

	@Autowired
	private UserRepository repository;
	
	public User insert(User obj) {
		return repository.save(obj);
	}
	
	public User update(Long id, User obj) {
		try {
			User entity = repository.findById(id)
	                .orElseThrow(() -> new ResourceNotFoundException(id));
	        
	     // 2. Copia os dados do objeto recebido (obj) para a entidade monitorada (entity)
	        updateData(entity, obj);
	        
	        return repository.save(entity);
		}
		catch (ResourceNotFoundException e) {
			throw e;
		}
	}
	
	/**
     * Método auxiliar para copiar os dados do objeto 'novo' para a entidade monitorada 'antiga'.
     * Ignoramos o ID e a Senha (geralmente tratada em um método separado de segurança).
     * @param entity A entidade sendo monitorada pelo JPA.
     * @param obj O objeto com os novos dados recebidos da requisição.
     */
    private void updateData(User entity, User obj) {
        // Não atualizamos o ID, nem o campo password (o ideal é ter um endpoint separado para senha).
        entity.setName(obj.getName());
        entity.setEmail(obj.getEmail());
        entity.setPhone(obj.getPhone());
        
        // Se a senha vier na requisição, você pode atualizar, mas é recomendado ter hash e endpoint separados.
        // entity.setPassword(obj.getPassword()); 
    }
	
	public void delete(Long id) {
		repository.findById(id).orElseThrow(() -> new ResourceNotFoundException(id));
		try {
			repository.deleteById(id);
		}
		catch (DataIntegrityViolationException e) {
			throw new DataIntegrityViolationException("Cannot delete user with associated orders.");
		}
	}
	
	public List<User> findAll() {
		return repository.findAll();
	}
	
	public User findById(Long id) {
		Optional<User> obj = repository.findById(id);
		return obj.orElseThrow(() -> new ResourceNotFoundException(id)); 
	}
}
