package com.cdac.E_Learning.dto;

public class UserAssignmentResponse {
	
	private Long id;
    private String title;
    private String url;
    private boolean isComplete;
    private int isActive;

    // Constructors
    public UserAssignmentResponse(Long id, String title, String url, boolean isComplete, int isActive) {
        this.id = id;
        this.title = title;
        this.url = url;
        this.isComplete = isComplete;
        this.isActive = isActive;
    }

	@Override
	public String toString() {
		return "UserAssignmentResponse [id=" + id + ", title=" + title + ", url=" + url + ", isComplete=" + isComplete
				+ ", isActive=" + isActive + "]";
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public boolean isComplete() {
		return isComplete;
	}

	public void setComplete(boolean isComplete) {
		this.isComplete = isComplete;
	}

	public int getIsActive() {
		return isActive;
	}

	public void setIsActive(int isActive) {
		this.isActive = isActive;
	}
    
    

}
