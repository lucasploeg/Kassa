package entities;

import java.util.HashMap;
import java.util.Map;

public class Cart {

	private int customerID;
	private boolean customerIsMale;
	private String customerLastName;
	private int counterNumber;
	private Survey survey;
	private HashMap<Product, Integer> productList;

	public Cart(int counterNumber, HashMap<Product, Integer> productList, int customerID, boolean customerIsMale, String customerLastName) {
		this.counterNumber = counterNumber;
		this.productList = productList;
		this.customerID = customerID;
		this.customerIsMale = customerIsMale;
		this.customerLastName = customerLastName;
	}

	public Cart(int counterNumber) {
		this.counterNumber = counterNumber;
		// empty. Only for creating dummy data class.
	}

	public void fillWithDummyData() {
		productList = new HashMap<Product, Integer>();
		// Product product = new Product("12344223323222","Duo Penotti",1.32);
		// productList.put(product, 1);
		Product product2 = new Product("8710458011818", "Pindakaas", 1.20);
		productList.put(product2, 2);
		Product product3 = new Product("8711000306161", "Thee", 1.44);
		productList.put(product3, 1);

		customerID = -1;
		customerIsMale = true;
		customerLastName = "van Dummy";

	}

	public int getCustomerID() {
		return customerID;
	}

	public boolean isCustomerMale() {
		return customerIsMale;
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

	public void initiateSurvey() {
		survey = new Survey(this);
	}

	public Survey getSurvey() {
		return survey;
	}

	public HashMap<Product, Integer> getProductList() {
		return productList;
	}
}
