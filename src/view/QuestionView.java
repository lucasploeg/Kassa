package view;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.HashMap;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;

import model.CounterModel;
import controller.ViewController;
import entities.Cart;

public class QuestionView extends JPanel{
	
	private static HashMap<Integer,QuestionView> instances = new HashMap<Integer,QuestionView>();
	public static final String NAME = "QuestionView";
	private int counterNumber;
	private JLabel lblGreeting;
	
	private QuestionView(final int counterNumber) {
		setLayout(null);
		this.counterNumber = counterNumber;
		
		lblGreeting = new JLabel("Dag meneer van der Ploeg,");
		lblGreeting.setHorizontalAlignment(SwingConstants.CENTER);
		lblGreeting.setFont(new Font("Tahoma", Font.BOLD, 50));
		lblGreeting.setBounds(25, 39, 927, 128);
		add(lblGreeting);
		
		JLabel lblQuestion = new JLabel("wilt u afrekenen?");
		lblQuestion.setHorizontalAlignment(SwingConstants.CENTER);
		lblQuestion.setFont(new Font("Tahoma", Font.BOLD, 50));
		lblQuestion.setBounds(25, 130, 927, 128);
		add(lblQuestion);
		
		JButton btnYes = new JButton("Ja");
		btnYes.addActionListener(new ActionListener() {			
			public void actionPerformed(ActionEvent arg0) {
				ViewController.getInstance(counterNumber).showView(ProductView.NAME);
			}
		});
		btnYes.setFont(new Font("Tahoma", Font.BOLD, 50));
		btnYes.setBounds(44, 314, 422, 243);
		add(btnYes);
		
		JButton btnNo = new JButton("Nee");
		btnNo.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				ViewController.getInstance(counterNumber).showView(BarcodeView.NAME);
			}
		});
		btnNo.setFont(new Font("Tahoma", Font.BOLD, 50));
		btnNo.setBounds(519, 314, 422, 243);
		add(btnNo);
	}

	public static QuestionView getInstance(int counterNumber) {
		QuestionView instance = instances.get(counterNumber);
		
		if (instance == null) {
			instance = new QuestionView(counterNumber);
			instance.setName(NAME);
			instances.put(counterNumber, instance);
		}

		return instance;
	}
	
	public void updateGUI(){
		Cart cart = CounterModel.getInstance().getCounter(counterNumber).getCurrentCart();
		String customerLastName = cart.getCustomerLastname();	
		String gender = (cart.isCustomerMale() ? "meneer" : "mevrouw");
		
		lblGreeting.setText("Dag " + gender + " " + customerLastName + ",");
		
	}
	
	
	
	
}
