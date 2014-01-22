package model;

public class CartRetrieveModel {

	private static CartRetrieveModel instance;
	
	private static final String scanMessage = "Scan onderstaande barcode a.u.b.";
	
	private CartRetrieveModel(){
		//empty
	}
	
	public static CartRetrieveModel getInstance(){
		if(instance == null){
			instance = new CartRetrieveModel();
		}
		
		return instance;
	}
	
	public String getScanMessage(){
		return scanMessage;
	}
	
	public String getCartContents(Counter counter){
		return CartModel.getInstance().getData(counter.getCounterNumber());
	}
	
}
