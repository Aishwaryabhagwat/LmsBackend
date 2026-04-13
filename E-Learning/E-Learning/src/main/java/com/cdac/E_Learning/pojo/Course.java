package com.cdac.E_Learning.pojo;

import java.util.List;
import java.util.Objects;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.fasterxml.jackson.annotation.JsonBackReference;

import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class Course extends BaseEntity {

    private String name;
    private String description;
    private String author;

    @ManyToOne
    @JoinColumn(name = "category_id", nullable = false)  
    private Category category;
    
    @Column(nullable = false)
    private int isActive;
    
    @Column
    private String imageUrl;


//    @OneToMany(mappedBy = "course", cascade = CascadeType.ALL, orphanRemoval = true)
//    private List<Chapter> chapters;


//    @OneToMany(mappedBy = "course", cascade = CascadeType.ALL, orphanRemoval = true)
//    private List<Assignment> assignments;

    @JsonIgnore
    @ManyToMany(mappedBy = "enrolledCourses")
    private List<User> enrolledUsers;
    
    @Override
    public int hashCode() {
        return Objects.hash(getId()); // Hash based on unique ID
    }
}
