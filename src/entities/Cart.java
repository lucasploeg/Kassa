package entities;

import java.util.HashMap;
import java.util.Map;

import view.BarcodeView;
import controller.ViewController;

public class Cart {

	private boolean customerIsMale;
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
		customerIsMale = true;
		customerLastName = "Ploeg";
		
		Product product = new Product("12344223323222","Duo Penotti",1.32);
		productList.put(product, 1);
		Product product2 = new Product("8710458011818","Pindakaas",1.20);
		productList.put(product2, 2);
		Product product3 = new Product("8711000306161","Thee",1.44);
		productList.put(product3, 1);
	}
}
