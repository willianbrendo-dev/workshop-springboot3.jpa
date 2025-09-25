package com.example.aulajpa.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.aulajpa.entities.Order;
import com.example.aulajpa.entities.OrderItem;
import com.example.aulajpa.entities.Product;
import com.example.aulajpa.entities.pk.OrderItemPK;
import com.example.aulajpa.repositories.OrderItemRepository;
import com.example.aulajpa.repositories.OrderRepository;
import com.example.aulajpa.repositories.ProductRepository;

@Service
public class OrderItemService {

	@Autowired
	private OrderItemRepository OrderItemRepository;
	
	@Autowired
    private OrderRepository orderRepository;
	
	@Autowired
    private ProductRepository productRepository;
	
	
	
	public List<OrderItem> findAll() {
		return OrderItemRepository.findAll();
	}
	
	public OrderItem findById(Long orderId, Long productId) {
        // Buscar Order
        Order order = orderRepository.findById(orderId)
                .orElseThrow(() -> new RuntimeException("Order not found"));

        // Buscar Product
        Product product = productRepository.findById(productId)
                .orElseThrow(() -> new RuntimeException("Product not found"));

        // Criar chave composta
        OrderItemPK idPK = new OrderItemPK();
        idPK.setOrder(order);
        idPK.setProduct(product);

        // Buscar OrderItem
        return OrderItemRepository.findById(idPK)
                .orElseThrow(() -> new RuntimeException("OrderItem not found"));
    }
}
