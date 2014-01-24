package view;

import javax.swing.JPanel;

public class PaymentView extends JPanel{
	
	private static PaymentView instance;
	public static final String NAME = "PaymentView";
	
	private PaymentView() {
		setLayout(null);
	}

	public static PaymentView getInstance() {
		if (instance == null) {
			instance = new PaymentView();
			instance.setName(NAME);
		}

		return instance;
	}	
}
