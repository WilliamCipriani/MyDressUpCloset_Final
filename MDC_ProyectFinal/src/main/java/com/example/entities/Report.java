package com.example.entities;

public class Report {
	private long id;
	private String name;
	private long quantity;
	
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public long getQuantity() {
		return quantity;
	}
	public void setQuantity(long quantity) {
		this.quantity = quantity;
	}
	public Report(long id, String name, long quantity) {
		super();
		this.id = id;
		this.name = name;
		this.quantity = quantity;
	}
	
	
	
}
