package view;

import java.awt.Font;
import java.util.HashMap;

import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;

import controller.BarcodeController;

public class BarcodeView extends JPanel{
	
	private static HashMap<Integer, BarcodeView> instances = new HashMap<Integer, BarcodeView>();
	public static final String NAME = "BarcodeView";
	private int counterNumber;
	
	JLabel lblQuestion;
	
	private BarcodeView(int counterNumber) {
		setLayout(null);
		this.counterNumber = counterNumber;
		lblQuestion = new JLabel("lblQuestion");
		lblQuestion.setHorizontalAlignment(SwingConstants.CENTER);
		lblQuestion.setFont(new Font("Tahoma", Font.BOLD, 50));
		lblQuestion.setBounds(45, 105, 927, 128);
		add(lblQuestion);
		
		setLblQuestionText();
		setLblQR();
		
	}

	public static BarcodeView getInstance(int counterNumber) {
		BarcodeView instance = instances.get(counterNumber);
		
		if (instance == null) {
			instance = new BarcodeView(counterNumber);
			instance.setName(NAME);
			instances.put(counterNumber, instance);
		}

		return instance;
	}
	
	private void setLblQuestionText(){
		lblQuestion.setText(BarcodeController.getInstance(counterNumber).getScanMessage());
	}
	
	private void setLblQR(){
		ImageIcon image = new ImageIcon(BarcodeController.getInstance(counterNumber).getScanImage());
		
		JLabel lblQR = new JLabel("", image, JLabel.CENTER);
		lblQR.setBounds(383, 258, 225, 239);
		add(lblQR);
	}
}
