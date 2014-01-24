package controller;

import java.awt.image.BufferedImage;
import java.util.HashMap;

import model.BarcodeModel;

public class BarcodeController {

	private static HashMap<Integer,BarcodeController> instances = new HashMap<Integer,BarcodeController>();
	private int counterNumber;

	private BarcodeController(int counterNumber) {
		this.counterNumber = counterNumber;
	}

	public static BarcodeController getInstance(int counterNumber) {
		BarcodeController instance = instances.get(counterNumber);
		
		if (instance == null) {
			instance = new BarcodeController(counterNumber);
			instances.put(counterNumber,instance);
		}

		return instance;
	}

	public String getScanMessage() {
		return BarcodeModel.getInstance().getScanMessage();
	}
	
	public BufferedImage getScanImage(){
		return BarcodeModel.getInstance().getQR(counterNumber);
	}
}
