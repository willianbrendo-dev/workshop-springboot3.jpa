package com.example.aulajpa.resources;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.aulajpa.entities.OrderItem;
import com.example.aulajpa.services.OrderItemService;

@RestController
@RequestMapping(value = "/OrderItems")
public class OrderItemResources {
	
	@Autowired
	private OrderItemService service;

	@GetMapping
	public ResponseEntity<List<OrderItem>> findAll() {
		List<OrderItem> list = service.findAll();
		return ResponseEntity.ok().body(list);
	}
	
	@GetMapping(value = "/{orderId}/{productId}")
    public ResponseEntity<OrderItem> findById(
            @PathVariable Long orderId,
            @PathVariable Long productId) {
        OrderItem obj = service.findById(orderId, productId);
        return ResponseEntity.ok().body(obj);
    }
}
