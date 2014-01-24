package controller;

import model.BarcodeModel;

public class BarcodeController {

private static BarcodeController instance;
	
	private BarcodeController(){
		//empty
	}
	
	public static BarcodeController getInstance(){
		if(instance == null){
			instance = new BarcodeController();
		}
		
		return instance;
	}
	
	public String getScanMessage(){
		return BarcodeModel.getInstance().getScanMessage();
	}
	
}
