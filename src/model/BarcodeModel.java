package model;

import java.awt.image.BufferedImage;
import java.util.HashMap;

public class BarcodeModel {

	private static BarcodeModel instance;
	
	private HashMap<String,BufferedImage> qrCodes;

	private BarcodeModel() {
		qrCodes = new HashMap<String,BufferedImage>();
		
		
	}

	public static BarcodeModel getInstance() {
		if (instance == null) {
			instance = new BarcodeModel();
		}

		return instance;
	}

	public BufferedImage getQR(int counterNumber) {
		// TODO Auto-generated method stub
		return null;
	}

}
