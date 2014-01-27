package controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import model.CounterModel;
import view.BarcodeView;
import view.PaymentView;
import view.ProductView;
import entities.Cart;
import entities.Product;
import entities.Survey;

public class ProductController {

	private static HashMap<Integer, ProductController> instances = new HashMap<Integer, ProductController>();
	private int counterNumber;

	private ProductController(int counterNumber) {
		this.counterNumber = counterNumber;
	}

	public static ProductController getInstance(int counterNumber) {
		ProductController instance = instances.get(counterNumber);

		if (instance == null) {
			instance = new ProductController(counterNumber);
			instances.put(counterNumber, instance);
		}

		return instance;
	}

	public void prepareCartForView() {
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

			String[] subArray = { key.getName(), quantityLbl, quantity, pricePerOneLbl, String.format("%.2f", key.getPrice()), key.getEAN() };

			data[length] = subArray;
			length++;
		}

		ProductView.getInstance(counterNumber).updateProductsTable(data);
		ProductView.getInstance(counterNumber).setLblPrice();
	}

	public void payButtonPressed() {
		if (CounterModel.getInstance().getCounter(counterNumber).SURVEY_TURNED_ON && CounterModel.getInstance().getCounter(counterNumber).getCurrentCart().getSurvey() == null) {
			CounterModel.getInstance().getCounter(counterNumber).getCurrentCart().initiateSurvey();
			ProductView.getInstance(counterNumber).initiateSurvey();
			(new Thread(new CheckProducts())).start();
		} else {
			boolean surveyOK = (CounterModel.getInstance().getCounter(counterNumber).getCurrentCart().getSurvey() == null ? true : CounterModel.getInstance().getCounter(counterNumber).getCurrentCart().getSurvey().getSurveyOK());
			
			if (surveyOK) {
				ViewController.getInstance(counterNumber).showView(PaymentView.NAME);
				PaymentView payView = (PaymentView) ViewController.getInstance(counterNumber).getView(PaymentView.NAME);
				payView.updateGUI();
			} else {
				ViewController.getInstance(counterNumber).showView(BarcodeView.NAME);
			}
		}
	}

	private class CheckProducts implements Runnable {
		@Override
		public void run() {
			ArrayList<String> scannedProducts = new ArrayList<String>();
			Survey survey = CounterModel.getInstance().getCounter(counterNumber).getCurrentCart().getSurvey();

			while (true) {
				if (survey.getScannedProducts().size() > 0) {
					String product = survey.getScannedProducts().get(survey.getScannedProducts().size() - 1);

					boolean exists = false;

					for (String scannedProduct : scannedProducts) {
						if (scannedProduct.equals(product)) {
							exists = true;
							break;
						}
					}

					if (exists == false) {
						scannedProducts.add(product);

						boolean scanned = (survey.productIsScannedByCustomer(product) ? true : false);

						if (scanned == true) {
							ProductView.getInstance(counterNumber).productScanned(true, product);

							if (survey.productsLeftToCheck() == 0) {
								break;
							}
						} else {
							ProductView.getInstance(counterNumber).productScanned(false, product);
							break;
						}
					}
				}
				try {
					Thread.sleep(500);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
		}
	}
}
