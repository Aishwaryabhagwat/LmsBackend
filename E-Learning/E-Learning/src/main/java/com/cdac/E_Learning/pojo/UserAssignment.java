package com.cdac.E_Learning.pojo;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class UserAssignment extends BaseEntity {
	
	
	 public UserAssignment() {}

	    public UserAssignment(User user, Assignment assignment) {
	        this.user = user;
	        this.assignment = assignment;
	        this.isComplete = false;
	    }

    private boolean isComplete;

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    @ManyToOne
    @JoinColumn(name = "assignment_id", nullable = false)
    private Assignment assignment;
    
    
    @Column(nullable = true)
    private String answerFileUrl;
}