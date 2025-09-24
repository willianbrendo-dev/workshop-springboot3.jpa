package com.example.aulajpa.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.aulajpa.entities.Category;
import com.example.aulajpa.repositories.CategoryRepository;

@Service
public class CategoryService {

	@Autowired
	private CategoryRepository categoryRepository;
	
	public List<Category> findAll() {
		return categoryRepository.findAll();
	}
	
	public Category findById(Long id) {
		Optional<Category> obj = categoryRepository.findById(id);
		return obj.orElse(null); // Retorna null se o objeto não for encontrado
	}
}
