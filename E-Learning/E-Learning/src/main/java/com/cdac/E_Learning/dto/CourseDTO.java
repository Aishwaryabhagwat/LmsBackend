package com.cdac.E_Learning.dto;

public class CourseDTO {
	private Long id;
    private String name;
    private String description;
    private String author;
    private Long categoryId;
    private String imageUrl;

    public CourseDTO(Long id, String name, String description, String author, Long categoryId, String imageUrl) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.author = author;
        this.categoryId = categoryId;
        this.imageUrl = imageUrl;
    }

    // Getters and setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public Long getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(Long categoryId) {
        this.categoryId = categoryId;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }
}
