package entities;

import java.util.ArrayList;
import java.util.HashMap;

import model.ScannerServer;


public class Survey {

	private static final int productsToCheck = 5;
	private ArrayList<String> scannedProducts;
	private Cart cart;
	private ScannerServer server;

	public Survey(Cart cart) {
		this.cart = cart;
		scannedProducts = new ArrayList<String>();

		createScanner();
	}

	public int productsLeftToCheck() {
		return (productsToCheck - scannedProducts.size());
	}

	public boolean productIsScannedByCustomer(String EAN) {
		HashMap<Product, Integer> productList = cart.getProductList();

		boolean scanned = false;

		for (Product product : productList.keySet()) {
			if (product.getEAN().equals(EAN)) {
				scanned = true;
				break;
			}
		}
		return scanned;
	}
	
	public void addProductToScannedList(String EAN){
		scannedProducts.add(EAN);
	}

	private void createScanner() {
		server = new ScannerServer(this);

		(new Thread(server)).start();
	}
}
