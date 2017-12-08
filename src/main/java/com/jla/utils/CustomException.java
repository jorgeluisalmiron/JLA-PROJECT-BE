package com.jla.utils;

public class CustomException extends Exception {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private int exceptionId;

	public CustomException(int exceptionId, String msg) {
		super(msg);
		this.setExceptionId(exceptionId);
	}

	public CustomException(String msg) {
		super(msg);
	}

	public int getExceptionId() {
		return exceptionId;
	}

	private void setExceptionId(int exceptionId) {
		this.exceptionId = exceptionId;
	}
}