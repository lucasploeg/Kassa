package view;

import java.util.HashMap;

import javax.swing.JPanel;

public class PaymentView extends JPanel{
	
	private static HashMap<Integer,PaymentView> instances = new HashMap<Integer,PaymentView>();
	public static final String NAME = "PaymentView";
	private int counterNumber;
	
	private PaymentView(int counterNumber) {
		setLayout(null);
		this.counterNumber = counterNumber;
	}

	public static PaymentView getInstance(int counterNumber) {
		PaymentView instance = instances.get(counterNumber);
		
		if (instance == null) {
			instance = new PaymentView(counterNumber);
			instance.setName(NAME);
			instances.put(counterNumber,instance);
		}

		return instance;
	}	
}
