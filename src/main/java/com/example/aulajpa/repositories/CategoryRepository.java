package com.example.aulajpa.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.aulajpa.entities.Category;

@Repository
public interface CategoryRepository extends JpaRepository<Category, Long>{

}
