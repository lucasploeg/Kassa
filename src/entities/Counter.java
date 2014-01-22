package entities;

import java.awt.image.BufferedImage;

import model.BarcodeModel;


public class Counter {
	
	private static final String scanMessage = "Scan onderstaande barcode a.u.b.";
	private int counterNumber;
	
	public Counter(int counterNumber){
		this.counterNumber = counterNumber;
	}
	
	public int getCounterNumber(){
		return counterNumber;
	}
	
	public BufferedImage getCounterQR(){
		return BarcodeModel.getInstance().getQR(counterNumber);
	}
	
	public String getScanMessage(){
		return scanMessage;
	}
	
	/*public String getCurrentCustomerCart(){
		
	}*/	
}
