package entities;

import java.util.ArrayList;
import java.util.HashMap;

import model.ScannerServer;


public class Survey {

	private static boolean SURVEY_ON = true;
	
	private static final int productsToCheck = 5;
	private int correctlyScanned = 0;
	private ArrayList<String> scannedProducts;
	private Cart cart;
	private ScannerServer server;
	private boolean surveyOK;

	public Survey(Cart cart) {
		this.cart = cart;
		scannedProducts = new ArrayList<String>();

		createScanner();
	}

	public int productsLeftToCheck() {
		if(cart.getProductList().size() < productsToCheck){
			return (cart.getProductList().size() - correctlyScanned);
		}
		return (productsToCheck - correctlyScanned);
	}
	
	public ArrayList<String> getScannedProducts(){
		return scannedProducts;
	}

	public boolean productIsScannedByCustomer(String productParam) {
		HashMap<Product, Integer> productList = cart.getProductList();

		boolean scanned = false;

		for (Product product : productList.keySet()) {
			if (product.getEAN().equals(productParam)) {
				scanned = true;
				correctlyScanned++;
				break;
			}
		}
		
		return scanned;
	}
	
	public void addProductToScannedList(String product){
		scannedProducts.add(product);
	}

	private void createScanner() {
		server = new ScannerServer(this);

		(new Thread(server)).start();
	}
	
	public int getScanned(){
		return correctlyScanned;
	}
	
	public int getProductsToCheck(){
		if(cart.getProductList().size() < productsToCheck){
			return cart.getProductList().size();
		}
		return productsToCheck; 
	}
	
	public void setSurveyOK(boolean surveyOK){
		this.surveyOK = surveyOK;
	}
	
	public boolean getSurveyOK(){
		return surveyOK;
	}
	
	public static void turnSurveyOn(){
		SURVEY_ON = true;
	}
	
	public static void turnSurveyOff(){
		SURVEY_ON = false;
	}
}
