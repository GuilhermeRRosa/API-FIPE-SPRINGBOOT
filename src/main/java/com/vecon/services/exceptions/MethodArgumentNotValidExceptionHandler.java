package com.vecon.services.exceptions;

import java.util.Date;

public class MethodArgumentNotValidExceptionHandler extends StandardError {

	private String fields;
	private String causes;

	public MethodArgumentNotValidExceptionHandler(Integer status, String msg, Date data, String fields, String causes) {
		super(status, msg, data);
		this.fields = fields;
		this.causes = causes;
	}

	public String getFields() {
		return fields;
	}

	public void setFields(String fields) {
		this.fields = fields;
	}

	public String getCauses() {
		return causes;
	}

	public void setCauses(String causes) {
		this.causes = causes;
	}

}
