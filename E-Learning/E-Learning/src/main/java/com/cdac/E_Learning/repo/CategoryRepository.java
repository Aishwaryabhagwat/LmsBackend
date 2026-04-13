package com.cdac.E_Learning.repo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.cdac.E_Learning.pojo.Category;

public interface CategoryRepository extends JpaRepository<Category, Long> {
    
	
	List<Category> findByIsActive(int isActive);
}
