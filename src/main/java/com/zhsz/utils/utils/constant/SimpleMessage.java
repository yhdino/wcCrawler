package com.zhsz.utils.utils.constant;

public class SimpleMessage {

	private boolean success = true;
	private String message ;
	 
	public SimpleMessage() {
	}

 
	public SimpleMessage(boolean success, String message) {
		this.success = success;
		this.message = message;
	}




	public boolean isSuccess() {
		return success;
	}



	public void setSuccess(boolean success) {
		this.success = success;
	}



	public String getMessage() {
		return message;
	}



	public void setMessage(String message) {
		this.message = message;
	}

 

 
	
	
	
	
	
	
}
