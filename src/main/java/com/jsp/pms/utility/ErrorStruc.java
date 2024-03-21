package com.jsp.pms.utility;

import org.springframework.stereotype.Component;

@Component
public class ErrorStruc {
	
	private int statuscode;
	private String message;
	private Object rootCause;
	
	public int getStatuscode() {
		return statuscode;
	}
	public ErrorStruc setStatuscode(int statuscode) {
		this.statuscode = statuscode;
		return this;
	}
	public String getMessage() {
		return message;
	}
	public ErrorStruc setMessage(String message) {
		this.message = message;
		return this;
	}
	public Object getRootCause() {
		return rootCause;
	}
	public ErrorStruc setRootCause(Object rootCause) {
		this.rootCause = rootCause;
		return this;
	}

}
