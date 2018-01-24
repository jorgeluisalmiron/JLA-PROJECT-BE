package jla.project.com.utils;

public class ResponseMessage {
	String message;
	int code;
	

	public ResponseMessage(int code,String message) {
		super();
		this.message = message;
		this.code= code;
			}
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	public int getCode() {
		return code;
	}
	public void setCode(int code) {
		this.code = code;
	}
	
	
	

}
