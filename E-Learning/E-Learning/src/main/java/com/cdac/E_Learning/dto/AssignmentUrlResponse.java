package com.cdac.E_Learning.dto;

//dto for tracking asgmnt
public class AssignmentUrlResponse {
	 private String url;

		public AssignmentUrlResponse(String url) {
		
			this.url = url;
		}

		public String getUrl() {
			return url;
		}

		public void setUrl(String url) {
			this.url = url;
		}
}
