package entities;

import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.HashMap;

import view.ProductView;
import view.QuestionView;

import model.APIServer;
import model.BarcodeModel;
import controller.ProductController;
import controller.ViewController;

public class Counter {

	public boolean SURVEY_TURNED_ON = false;

	private static final String helloMsg = "Dag";
	private static final String question = "Wilt u afrekenen?";
	private static final String answerYes = "Ja";
	private static final String answerNo = "Nee";
	private static final String notAllProductsScanned = "Niet alle producten zijn door de klant gescand.";

	private int counterNumber;
	private ArrayList<Cart> carts;
	private boolean active = false;

	public Counter(int counterNumber) {
		this.counterNumber = counterNumber;
		carts = new ArrayList<Cart>();
	}

	public void initateViewController() {
		ViewController.getInstance(getCounterNumber());
	}

	public int getCounterNumber() {
		return counterNumber;
	}

	public BufferedImage getCounterQR() {
		return BarcodeModel.getInstance().getQR(counterNumber);
	}

	public void scanForIncomingCarts() {
		APIServer apis = new APIServer(counterNumber);

		new Thread(apis).start();
	}

	public void initiateNewCart(int counterNumber, HashMap<Product, Integer> productList, int customerID, boolean customerIsMale, String customerLastName) {
		Cart cart = new Cart(counterNumber, productList, customerID, customerIsMale, customerLastName);
		carts.add(cart);
		ProductController.getInstance(counterNumber).prepareCartForView();
		QuestionView.getInstance(counterNumber).updateGUI();
		ViewController.getInstance(counterNumber).showView(QuestionView.NAME);
	}

	public void initiateDummyCart() {
		// Cart with dummy data.
		Cart cart = new Cart(counterNumber);
		cart.fillWithDummyData();
		carts.add(cart);
		ProductController.getInstance(counterNumber).prepareCartForView();
	}

	public Cart getCurrentCart() {
		if (carts.size() > 0) {
			return carts.get(carts.size() - 1);
		}
		return null;
	}

	public String getControlQuestion(String firstName, String lastName) {
		return (helloMsg + " " + firstName + " " + lastName);
	}

	public String getQuestion() {
		return question;
	}

	public String getAnswerYes() {
		return answerYes;
	}

	public String getAnswerNo() {
		return answerNo;
	}

	public String getNotAllProductsScanned() {
		return notAllProductsScanned;
	}

	public void setActive() {
		active = true;
	}

	public boolean isActive() {
		return active;
	}
}
