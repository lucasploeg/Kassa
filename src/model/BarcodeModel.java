package model;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.HashMap;

import javax.imageio.ImageIO;

public class BarcodeModel {

	private static BarcodeModel instance;

	private static final String scanMessage = "Scan onderstaande barcode a.u.b.";
	private HashMap<String, BufferedImage> qrCodes;

	private BarcodeModel() {
		qrCodes = new HashMap<String, BufferedImage>();

		for (int i = 1; i < 6; i++) {
			String name = "Kassa" + i;

			BufferedImage img = null;
			try {
				img = ImageIO.read(new File("qrcodes/"+name+".jpg"));
				qrCodes.put(name, img);
			} catch (IOException e) {
				System.out.println(e);
			}
		}
	}

	public static BarcodeModel getInstance() {
		if (instance == null) {
			instance = new BarcodeModel();
		}

		return instance;
	}

	public BufferedImage getQR(int counterNumber) {
		return qrCodes.get("Kassa"+counterNumber);
	}
	
	public String getScanMessage(){
		return scanMessage;
	}

}
