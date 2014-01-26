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

		//temp with dummy data
		setData();
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

	private void setData() {
		customerFirstName = "Lucas";
		customerLastName = "Ploeg";
		
		Product product = new Product("EAN123","Cola",1.32);
		productList.put(product, 1);
		Product product2 = new Product("EAN124","Brood",1.20);
		productList.put(product2, 2);
	}
}
