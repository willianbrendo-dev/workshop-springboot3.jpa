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
	private UserRepository service;
	
	public User insert(User obj) {
		return service.save(obj);
	}
	
	public List<User> findAll() {
		return service.findAll();
	}
	
	public User findById(Long id) {
		Optional<User> obj = service.findById(id);
		return obj.orElse(null); // Retorna null se o objeto não for encontrado
	}
}
