package com.example.aulajpa.config;

import java.time.Instant;
import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

import com.example.aulajpa.entities.Category;
import com.example.aulajpa.entities.Order;
import com.example.aulajpa.entities.Product;
import com.example.aulajpa.entities.User;
import com.example.aulajpa.entities.enums.OrderStatus;
import com.example.aulajpa.repositories.CategoryRepository;
import com.example.aulajpa.repositories.OrderRepository;
import com.example.aulajpa.repositories.ProductRepository;
import com.example.aulajpa.repositories.UserRepository;

@Configuration
@Profile("test")
public class TestConfig implements CommandLineRunner{
	
	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private OrderRepository orderRepository;
	
	@Autowired
	private CategoryRepository categoryRepository;
	
	@Autowired
	private ProductRepository productRepository;

	@Override
	public void run(String... args) throws Exception {
		User u1 = new User(null, "Willian Brendo", "willian@gmail.com", "99999", "12345");
		User u2 = new User(null, "Brendo", "brendo@gmail.com", "88888", "12345");
		
		Order o1 = new Order(null, Instant.parse("2025-09-24T13:45:30Z"), OrderStatus.WAITING_PAYMENT, u2);
		Order o2 = new Order(null, Instant.parse("2023-12-31T23:59:59Z"), OrderStatus.SHIPPED, u1);
		Order o3 = new Order(null, Instant.parse("2024-07-15T08:20:10Z"), OrderStatus.PAID, u2);
		
		Category cat1 = new Category(null, "Electronics");
		Category cat2 = new Category(null, "Books");
		Category cat3 = new Category(null, "Computers");
		
		Product p1 = new Product(null, "The Lord of the Rings", "Lorem ipsum dolor sit amet, consectetur.", 90.5, "");
		Product p2 = new Product(null, "Smart TV", "Nulla eu imperdiet purus. Maecenas ante.", 2190.0, "");
		Product p3 = new Product(null, "Macbook Pro", "Nam eleifend maximus tortor, at mollis.", 1250.0, "");
		Product p4 = new Product(null, "PC Gamer", "Donec aliquet odio ac rhoncus cursus.", 1200.0, "");
		Product p5 = new Product(null, "Rails for Dummies", "Cras fringilla convallis sem vel faucibus.", 100.99, "");

		
		
		
		userRepository.saveAll(Arrays.asList(u1, u2));
		orderRepository.saveAll(Arrays.asList(o1, o2, o3));
		categoryRepository.saveAll(Arrays.asList(cat1, cat2, cat3));
		productRepository.saveAll(Arrays.asList(p1, p2, p3, p4, p5));
		
		p1.getCategories().add(cat2);
		p2.getCategories().add(cat1);
		p2.getCategories().add(cat3);
		p3.getCategories().add(cat1);
		p3.getCategories().add(cat3);
		p4.getCategories().add(cat1);
		p4.getCategories().add(cat3);
		p5.getCategories().add(cat2);
		
		productRepository.saveAll(Arrays.asList(p1, p2, p3, p4, p5));
	}
	
	
	
	
}
