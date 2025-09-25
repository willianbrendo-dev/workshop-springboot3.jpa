package com.example.aulajpa.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.aulajpa.entities.User;
import com.example.aulajpa.repositories.UserRepository;

@Service
public class UserService {

	@Autowired
	private UserRepository repository;
	
	public User insert(User obj) {
		return repository.save(obj);
	}
	
	public User update(Long id, User obj) {
		// 1. Usa getReferenceById(id) para pegar uma referência do objeto sem tocar no banco
        // até que seja realmente necessário (boa prática de performance).
        // Se o ID não existir, ele pode lançar uma exceção de Entidade Não Encontrada (a ser tratada em um Exception Handler)
        User entity = repository.getReferenceById(id);
        
     // 2. Copia os dados do objeto recebido (obj) para a entidade monitorada (entity)
        updateData(entity, obj);
        
        return repository.save(entity);
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
		repository.deleteById(id);
	}
	
	public List<User> findAll() {
		return repository.findAll();
	}
	
	public User findById(Long id) {
		Optional<User> obj = repository.findById(id);
		return obj.orElse(null); // Retorna null se o objeto não for encontrado
	}
}
