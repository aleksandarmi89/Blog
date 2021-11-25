package com.cubes.main.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.cubes.main.entity.Category;



public interface CategoryRepository extends JpaRepository<Category, Integer> {
	
	 public List<Category> findFirst4ByOrderByPriorityAsc();
	 
	 public List<Category> findFirst5ByOrderByPriorityAsc();
}
