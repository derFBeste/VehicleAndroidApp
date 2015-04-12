package com.example.vehiclefredbesteman;

import java.text.SimpleDateFormat;
import java.util.Calendar;

public class Mileage {
	long id;
	String date;
	Double price;
	Double gallons;
	Double miles;
	
	public Mileage(){
		
	}
	
	public Mileage(long id, String date, Double price, Double gallons, Double miles){
		this.id = id;
		this.date = date;
		this.price = price;
		this.gallons = gallons;
		this.miles = miles;
	}

	@Override
	public String toString(){
		return this.id + " " + this.date + " " + this.price + " " + this.gallons + " " + this.miles;
	}
	
	public long generateId(){
		String dateId = new SimpleDateFormat("Dms").format(Calendar.getInstance().getTime());
		Long id = Long.parseLong(dateId);
		
		return id;
	}
	
	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}
	
	public String getDate() {
		return date;
	}

	public void setDate(String date) {
		this.date = date;
	}

	public Double getPrice() {
		return price;
	}

	public void setPrice(Double price) {
		this.price = price;
	}

	public Double getGallons() {
		return gallons;
	}

	public void setGallons(Double gallons) {
		this.gallons = gallons;
	}

	public Double getMiles() {
		return miles;
	}

	public void setMiles(Double miles) {
		this.miles = miles;
	}
		
}
