package com.cdac.E_Learning.pojo;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class Assignment extends BaseEntity {
	
	 public Assignment() {}

	    public Assignment(Long id) {
	        this.setId(id); // Assuming the ID is being set in the BaseEntity class
	    }

    private String title;
    private String url;

    
    
    @ManyToOne
    @JoinColumn(name = "course_id", nullable = false)
    private Course course;
    
    @Column(nullable = false)
    private int isActive;



//    @OneToMany(mappedBy = "assignment", cascade = CascadeType.ALL, orphanRemoval = true)
//    private List<UserAssignment> userAssignments;
}
