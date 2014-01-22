package entities;

import java.util.HashMap;
import java.util.Map;

public class Cart {

	private String customerFirstName;
	private String customerLastName;
	private int counterNumber;
	private Survey survey;

	private HashMap<Product, Integer> productList;

	public Cart(int counterNumber) {
		this.counterNumber = counterNumber;
		productList = new HashMap<Product, Integer>();

		getData(counterNumber);
	}

	public String getCustomerFirstName() {
		return customerFirstName;
	}

	public String getCustomerLastname() {
		return customerLastName;
	}

	public int getCounterNumber() {
		return counterNumber;
	}

	public double getCartPrice() {
		double price = 0.0;

		for (Map.Entry<Product, Integer> entry : productList.entrySet()) {
			price = price + (entry.getKey().getPrice() * entry.getValue());
		}

		return price;
	}
	
	public void initiateSurvey(){
		survey = new Survey(this);
	}
	
	public Survey getSurvey(){
		return survey;
	}
	
	public HashMap<Product,Integer> getProductList(){
		return productList;
	}

	private String getData(int counterNumber) {
		return "Data";
	}
}
