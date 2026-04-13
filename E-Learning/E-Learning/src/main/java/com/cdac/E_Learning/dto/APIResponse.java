package com.cdac.E_Learning.dto;

public class APIResponse<T>  {
	 private String status;
	    private T data;
	    private String errorMessage;

	    public APIResponse() {
	    }

	    public APIResponse(String status, T data, String errorMessage) {
	        
	        this.data = data;
	        this.status = status;
	        this.errorMessage = errorMessage;
	    }

	    public String getStatus() {
	        return status;
	    }

	    public void setStatus(String status) {
	        this.status = status;
	    }

	    public T getData() {
	        return data;
	    }

	    public void setData(T data) {
	        this.data = data;
	    }

	    public String getErrorMessage() {
	        return errorMessage;
	    }

	    public void setErrorMessage(String errorMessage) {
	        this.errorMessage = errorMessage;
	    }

	    @Override
	    public String toString() {
	        return "APIResponse{" +
	                "status='" + status + '\'' +
	                ", data=" + data +
	                ", errorMessage='" + errorMessage + '\'' +
	                '}';
	    }
}
