package com.cdac.E_Learning.dto;

public class EnrollRequestDTO {
	private Long requestId;
    private Long userId;
    private String username;
    private Long courseId;
    private String courseName;
    private String status;
    
    
    public EnrollRequestDTO(Long requestId, Long userId, String username, Long courseId, String courseName, String status) {
        this.requestId = requestId;
        this.userId = userId;
        this.username = username;
        this.courseId = courseId;
        this.courseName = courseName;
        this.status = status;
    }


	public Long getRequestId() {
		return requestId;
	}


	public void setRequestId(Long requestId) {
		this.requestId = requestId;
	}


	public Long getUserId() {
		return userId;
	}


	public void setUserId(Long userId) {
		this.userId = userId;
	}


	public String getUsername() {
		return username;
	}


	public void setUsername(String username) {
		this.username = username;
	}


	public Long getCourseId() {
		return courseId;
	}


	public void setCourseId(Long courseId) {
		this.courseId = courseId;
	}


	public String getCourseName() {
		return courseName;
	}


	public void setCourseName(String courseName) {
		this.courseName = courseName;
	}


	public String getStatus() {
		return status;
	}


	public void setStatus(String status) {
		this.status = status;
	}
    
    
    
}
