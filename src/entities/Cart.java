package entities;

import java.util.ArrayList;

public class Cart {
	
	private String customerFirstName;
	private String customerLastName;
	private int counterNumber;
	private ArrayList<Product> productList;
	
	public Cart(int counterNumber){
		this.counterNumber = counterNumber;
		getData(counterNumber);
		
		productList = new ArrayList<Product>();
	}
	
	public String getCustomerFirstName(){
		return customerFirstName;
	}
	
	public String getCustomerLastname(){
		return customerLastName;
	}
	
	public int getCounterNumber(){
		return counterNumber;
	}
	
	private String getData(int counterNumber){
		return "Data";
	}
}
