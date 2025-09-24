package com.example.aulajpa.config;

import java.time.Instant;
import java.util.ArrayList;
import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

import com.example.aulajpa.entities.Order;
import com.example.aulajpa.entities.User;
import com.example.aulajpa.entities.enums.OrderStatus;
import com.example.aulajpa.repositories.OrderRepository;
import com.example.aulajpa.repositories.UserRepository;

@Configuration
@Profile("test")
public class TestConfig implements CommandLineRunner{
	
	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private OrderRepository orderRepository;

	@Override
	public void run(String... args) throws Exception {
		User u1 = new User(null, "Willian Brendo", "willian@gmail.com", "99999", "12345");
		User u2 = new User(null, "Brendo", "brendo@gmail.com", "88888", "12345");
		
		Order o1 = new Order(null, Instant.parse("2025-09-24T13:45:30Z"), OrderStatus.WAITING_PAYMENT, u2);
		Order o2 = new Order(null, Instant.parse("2023-12-31T23:59:59Z"), OrderStatus.SHIPPED, u1);
		Order o3 = new Order(null, Instant.parse("2024-07-15T08:20:10Z"), OrderStatus.PAID, u2);
		
		userRepository.saveAll(Arrays.asList(u1, u2));
		orderRepository.saveAll(Arrays.asList(o1, o2, o3));
	}
	
	
	
	
}
