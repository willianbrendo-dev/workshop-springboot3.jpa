package com.example.aulajpa.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.aulajpa.entities.Order;

@Repository
public interface OrderRepository extends JpaRepository<Order, Long>{

}
