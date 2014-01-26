package controller;

import java.text.DecimalFormat;
import java.util.HashMap;
import java.util.Map;

import model.CounterModel;
import view.ProductView;
import entities.Cart;
import entities.Product;

public class ProductController {

	private static HashMap<Integer,ProductController> instances = new HashMap<Integer,ProductController>();
	private int counterNumber;
	

	private ProductController(int counterNumber) {
		this.counterNumber = counterNumber;
	}

	public static ProductController getInstance(int counterNumber) {
		ProductController instance = instances.get(counterNumber);
		
		if (instance == null) {
			instance = new ProductController(counterNumber);
			instances.put(counterNumber,instance);
		}

		return instance;
	}
	
	public void prepareCartForView(){
		Cart cart = CounterModel.getInstance().getCounter(counterNumber).getCurrentCart();
		HashMap<Product, Integer> productList = cart.getProductList();
		
		String data[][] = new String[productList.size()][];
		
		int length = 0;
		
		for (Map.Entry<Product, Integer> entry : productList.entrySet()) {
		    Product key = entry.getKey();
		    int value = entry.getValue();
		    
		    String quantityLbl = (value > 1 ? "x" : "");
		    String quantity = (value == 1 ? "" : Integer.toString(value));
		    String pricePerOneLbl = (value == 1 ? "" : "à");    
		    
		    String[] subArray = {key.getName(), quantityLbl, quantity, pricePerOneLbl, String.format( "%.2f",key.getPrice())};
		    
		    data[length] = subArray;
		    length++;
		}
		
		ProductView.getInstance(counterNumber).updateProductsTable(data);
		ProductView.getInstance(counterNumber).setLblPrice();
	}
	
}
