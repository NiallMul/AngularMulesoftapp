package com.qa.messageReturn;

import java.util.Date;

public class Transaction {
	private Date date;
	private String name;
	private int id;
	public Transaction() {
	}
	public Transaction(Date date, String name, int id) {
		this.date = date;
		this.name = name;
		this.id = id;
	}
	public Date getDate() {
		return date;
	}
	public void setDate(Date date) {
		this.date = date;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	
	
}
