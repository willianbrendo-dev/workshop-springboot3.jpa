package com.example.aulajpa.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.aulajpa.entities.Product;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {

}
