package com.cdac.E_Learning.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cdac.E_Learning.pojo.Category;
import com.cdac.E_Learning.repo.CategoryRepository;

import java.util.List;
import java.util.Optional;

@Service
public class CategoryService implements ICategoryService{

    private final CategoryRepository categoryRepository;

    @Autowired
    public CategoryService(CategoryRepository categoryRepository) {
        this.categoryRepository = categoryRepository;
    }

    public List<Category> getAllCategories() {
        return categoryRepository.findByIsActive(1);
    }

    public Category createCategory(Category category) {
    	category.setIsActive(1);
        return categoryRepository.save(category);
    }
    
    public Category updateCategory(Long id, Category updatedCategory) {
        Optional<Category> categoryOpt = categoryRepository.findById(id);
        if (!categoryOpt.isPresent()) {
            throw new IllegalArgumentException("Category with ID: " + id + " not found");
        }
        Category existingCategory = categoryOpt.get();
        existingCategory.setCategoryName(updatedCategory.getCategoryName());
        return categoryRepository.save(existingCategory);
    }
    
    public void deleteCategory(Long id) {
        Optional<Category> categoryOpt = categoryRepository.findById(id);
        if (!categoryOpt.isPresent()) {
            throw new IllegalArgumentException("Category with ID: " + id + " not found");
        }
        Category category = categoryOpt.get();
        
        // Soft delete: set isActive to 0
        category.setIsActive(0);
        
        categoryRepository.save(category);
    }
}
