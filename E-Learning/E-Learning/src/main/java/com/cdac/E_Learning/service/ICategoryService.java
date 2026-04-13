package com.cdac.E_Learning.service;

import java.util.List;

import com.cdac.E_Learning.pojo.Category;



public interface ICategoryService {
	public List<Category> getAllCategories();
	public Category createCategory(Category category);
	public Category updateCategory(Long id, Category updatedCategory);
	public void deleteCategory(Long id);
}
