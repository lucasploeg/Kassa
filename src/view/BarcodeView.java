package view;

import javax.swing.JPanel;
import javax.swing.JComboBox;

public class BarcodeView extends JPanel{
	
	private static BarcodeView instance;
	public static final String NAME = "BarcodeView";
	
	private BarcodeView() {
		setLayout(null);
		
		JComboBox comboBox = new JComboBox();
		comboBox.setBounds(44, 53, 159, 20);
		add(comboBox);
	}

	public static BarcodeView getInstance() {
		if (instance == null) {
			instance = new BarcodeView();
			instance.setName(NAME);
		}

		return instance;
	}
}
