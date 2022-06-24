package com.example.entities;

public class DateSelected {
	int month;
	int year;

	public int getMonth() {
		return month;
	}

	public void setMonth(int month) {
		this.month = month;
	}

	public int getYear() {
		return year;
	}

	public void setYear(int year) {
		this.year = year;
	}

	public DateSelected(int month, int year) {
		super();
		this.month = month;
		this.year = year;
	}

}
