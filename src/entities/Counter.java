package entities;

import java.awt.image.BufferedImage;
import java.util.ArrayList;

import model.BarcodeModel;


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
	
	public int getCounterNumber(){
		return counterNumber;
	}
	
	public BufferedImage getCounterQR(){
		return BarcodeModel.getInstance().getQR(counterNumber);
	}
	
	public void retrieveCurrentCustomerCart(){
		Cart cart = new Cart(counterNumber);
		carts.add(cart);
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
