package entities;

import java.awt.image.BufferedImage;
import java.util.ArrayList;

import model.APIServer;
import model.BarcodeModel;
import controller.ProductController;
import controller.ViewController;


public class Counter {
	
	private static final String helloMsg = "Dag";
	private static final String question = "Wilt u afrekenen?";
	private static final String answerYes = "Ja";
	private static final String answerNo = "Nee";
	private static final String notAllProductsScanned = "Niet alle producten zijn door de klant gescand.";
	
	private int counterNumber;
	private ArrayList<Cart> carts;
	private boolean active = false;
	
	public Counter(int counterNumber){
		this.counterNumber = counterNumber;
		carts = new ArrayList<Cart>();	
	}
	
	public void initateViewController(){
		ViewController.getInstance(getCounterNumber());
	}
	
	public int getCounterNumber(){
		return counterNumber;
	}
	
	public BufferedImage getCounterQR(){
		return BarcodeModel.getInstance().getQR(counterNumber);
	}
	
	public void scanForIncomingCarts(){
		APIServer apis = new APIServer(counterNumber);
	}
	
	public void initiateNewCart(){
		Cart cart = new Cart(counterNumber);
		carts.add(cart);
		ProductController.getInstance(counterNumber).prepareCartForView();
	}
	
	public Cart getCurrentCart(){
		return carts.get(carts.size()-1);
	}
	
	public String getControlQuestion(String firstName, String lastName){
		return (helloMsg + " " +  firstName + " " + lastName);
	}
	
	public String getQuestion(){
		return question;
	}
	
	public String getAnswerYes(){
		return answerYes;
	}
	
	public String getAnswerNo(){
		return answerNo;
	}
	
	public String getNotAllProductsScanned(){
		return notAllProductsScanned;
	}
	
	public void setActive(){
		active = true;
	}
	
	public boolean isActive(){
		return active;
	}
}
