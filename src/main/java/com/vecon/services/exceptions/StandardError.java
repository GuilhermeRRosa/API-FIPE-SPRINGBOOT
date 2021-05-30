package com.vecon.services.exceptions;

import java.util.Date;

public class StandardError {

	private Integer status;
	private String msg;
	private Date data;
	
	public StandardError(Integer status, String msg, Date data) {
		super();
		this.status = status;
		this.msg = msg;
		this.data = data;
	}

	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

	public String getMsg() {
		return msg;
	}

	public void setMsg(String msg) {
		this.msg = msg;
	}

	public Date getData() {
		return data;
	}

	public void setData(Date data) {
		this.data = data;
	}

}
