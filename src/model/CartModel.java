package model;

public class CartModel {

	private static CartModel instance;
	
	private CartModel(){
		//empty
	}
	
	public static CartModel getInstance(){
		if(instance == null){
			instance = new CartModel();
		}
		
		return instance;
	}
	
	public String getData(int counterNumber){
		return "Data";
	}
	
}
