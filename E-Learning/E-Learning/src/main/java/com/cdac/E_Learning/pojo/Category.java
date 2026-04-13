package com.cdac.E_Learning.pojo;

import javax.persistence.Column;
import javax.persistence.Entity;

import lombok.Getter;
import lombok.Setter;


@Getter
@Setter
@Entity
public class Category extends BaseEntity {

    private String categoryName;
    
    @Column(nullable = false)
    private int isActive;

//    @OneToMany(mappedBy = "category", cascade = CascadeType.ALL,fetch = FetchType.LAZY, orphanRemoval = true)
//    @JsonManagedReference
//    private List<Course> courses;
}