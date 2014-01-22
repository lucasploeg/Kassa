package entities;

import java.util.HashMap;

public class Survey {

	private static final int productsToCheck = 3;
	private int productsLeftToCheck = productsToCheck;
	private Cart cart;

	public Survey(Cart cart) {
		this.cart = cart;
	}

	public void productChecked() {
		productsLeftToCheck--;
	}

	public int productsLeftToCheck() {
		return productsLeftToCheck;
	}

	public boolean productIsScannedByCustomer(String EAN) {
		HashMap<Product, Integer> productList = cart.getProductList();

		boolean scanned = false;
		
		for (Product product : productList.keySet()) {
			if(product.getEAN().equals(EAN)){
				scanned = true;
				break;
			}
		}
		return scanned;
	}
}
