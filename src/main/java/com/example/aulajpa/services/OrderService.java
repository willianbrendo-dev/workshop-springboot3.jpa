package com.example.aulajpa.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.aulajpa.entities.Order;
import com.example.aulajpa.entities.User;
import com.example.aulajpa.repositories.OrderRepository;

@Service
public class OrderService {

	@Autowired
	private OrderRepository OrderRepository;
	
	public List<Order> findAll() {
		return OrderRepository.findAll();
	}
	
	public Order findById(Long id) {
		Optional<Order> obj = OrderRepository.findById(id);
		return obj.orElse(null); // Retorna null se o objeto não for encontrado
	}
}
