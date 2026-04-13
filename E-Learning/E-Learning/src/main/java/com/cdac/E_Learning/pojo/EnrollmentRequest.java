package com.cdac.E_Learning.pojo;

import java.time.LocalDateTime;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

@Entity
public class EnrollmentRequest extends BaseEntity{


	 @ManyToOne
	    private User user;

	    @ManyToOne
	    private Course course;

	    private String status; // PENDING, ACCEPTED, REJECTED

	    private LocalDateTime requestDate;

	    // Getters & Setters
	    public Long getId() {
	        return id;
	    }

	    public void setId(Long id) {
	        this.id = id;
	    }

	    public User getUser() {
	        return user;
	    }

	    public void setUser(User user) {
	        this.user = user;
	    }

	    public Course getCourse() {
	        return course;
	    }

	    public void setCourse(Course course) {
	        this.course = course;
	    }

	    public String getStatus() {
	        return status;
	    }

	    public void setStatus(String status) {
	        this.status = status;
	    }

	    public LocalDateTime getRequestDate() {
	        return requestDate;
	    }

	    public void setRequestDate(LocalDateTime requestDate) {
	        this.requestDate = requestDate;
	    }
}

