package com.cdac.E_Learning.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.cdac.E_Learning.dto.APIResponse;
import com.cdac.E_Learning.pojo.Category;
import com.cdac.E_Learning.service.CategoryService;
import com.cdac.E_Learning.service.ICategoryService;

@RestController
@RequestMapping("/api/categories")
@CrossOrigin(origins = "http://localhost:4200")
public class CategoryController {

    @Autowired
    private ICategoryService categoryService;

    @GetMapping("/getAllCategory")
    public APIResponse<List<Category>> getAllCategories() {
        List<Category> categories = categoryService.getAllCategories();
        if (!categories.isEmpty()) {
            return new APIResponse<>("success", categories, "no error");
        } else {
            return new APIResponse<>("error", null, "No categories found");
        }
    }
    
    @PostMapping("/create")
    public ResponseEntity<APIResponse<String>> createCategory(@RequestBody Category category) {
        Category createdCategory = categoryService.createCategory(category);
        String message = "Category '" + createdCategory.getCategoryName() + "' created successfully with ID: " + createdCategory.getId();
        return ResponseEntity.ok(new APIResponse<>("success", message, "Category created successfully"));
    }
    
    @PutMapping("/update/{id}")
    public ResponseEntity<APIResponse<String>> updateCategory(@PathVariable Long id, @RequestBody Category updatedCategory) {
        Category category = categoryService.updateCategory(id, updatedCategory);
        String message = "Category with ID: " + category.getId() + " updated successfully to '" + category.getCategoryName() + "'";
        return ResponseEntity.ok(new APIResponse<>("success", message, "Category updated successfully"));
    }
    
    @PutMapping("/delete/{id}")
    public ResponseEntity<APIResponse<String>> deleteCategory(@PathVariable Long id) {
        categoryService.deleteCategory(id);
        return ResponseEntity.ok(new APIResponse<>("success", "Category deleted successfully", ""));
    }
}
