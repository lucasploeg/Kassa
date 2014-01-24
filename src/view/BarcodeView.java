package view;

import java.awt.BorderLayout;
import java.awt.Font;

import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;

import controller.BarcodeController;

public class BarcodeView extends JPanel{
	
	private static BarcodeView instance;
	public static final String NAME = "BarcodeView";
	
	JLabel lblQuestion;
	
	private BarcodeView() {
		setLayout(null);
		
		lblQuestion = new JLabel("lblQuestion");
		lblQuestion.setHorizontalAlignment(SwingConstants.CENTER);
		lblQuestion.setFont(new Font("Tahoma", Font.BOLD, 50));
		lblQuestion.setBounds(45, 105, 927, 128);
		add(lblQuestion);
		
		setLblQuestionText();
		setLblQR();
		
	}

	public static BarcodeView getInstance() {
		if (instance == null) {
			instance = new BarcodeView();
			instance.setName(NAME);
		}

		return instance;
	}
	
	private void setLblQuestionText(){
		lblQuestion.setText(BarcodeController.getInstance().getScanMessage());
	}
	
	private void setLblQR(){
		ImageIcon image = new ImageIcon("qrcodes/Kassa1.jpg");
		
		JLabel lblQR = new JLabel("", image, JLabel.CENTER);
		lblQR.setBounds(383, 258, 225, 239);
		add(lblQR);
	}
}
