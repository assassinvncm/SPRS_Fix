package com.exception;

public class ProcException {
	private String status;
	private String err_code;
	private String err_message;
	
	public ProcException() {
		super();
	}
	public ProcException(String native_rs) {
		super();
		String[] process_err = native_rs.split("-");
		this.status = process_err[0];
		this.err_code = process_err[1];
		this.err_message = process_err[2];
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public String getErr_code() {
		return err_code;
	}
	public void setErr_code(String err_code) {
		this.err_code = err_code;
	}
	public String getErr_message() {
		return err_message;
	}
	public void setErr_message(String err_message) {
		this.err_message = err_message;
	}
	
}
