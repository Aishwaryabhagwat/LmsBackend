package com.cdac.E_Learning.pojo;



import java.util.Collection;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.ElementCollection;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Getter
@Setter
public class User extends BaseEntity {
	   public User() {}

	    public User(Long id) {
	        this.setId(id); // Assuming the ID is being set in the BaseEntity class
	    }

    private String name;
    
    @Column(unique = true, nullable = false)
    private String username;
    
    private String email;
    private String password;
    
    @Column(nullable = false)
    private int isActive;

    @ElementCollection(fetch = FetchType.EAGER)
    @CollectionTable(name = "roles", joinColumns = @JoinColumn(name = "user_id"))
    @Column(name = "role")
    private Collection<String> roles;

    
    @ManyToMany
    @JoinTable(
        name = "UserCourses",
        joinColumns = @JoinColumn(name = "user_id"),
        inverseJoinColumns = @JoinColumn(name = "course_id")
    )
    private List<Course> enrolledCourses;

//    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL, orphanRemoval = true)
//    private List<UserVideoProgress> videoProgresses;
//
//    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL, orphanRemoval = true)
//    private List<UserAssignment> userAssignments;
}
