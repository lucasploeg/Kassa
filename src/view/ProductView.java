package view;

import javax.swing.JPanel;

public class ProductView extends JPanel{
	
	private static ProductView instance;
	public static final String NAME = "ProductView";
	
	private ProductView() {
		setLayout(null);
	}

	public static ProductView getInstance() {
		if (instance == null) {
			instance = new ProductView();
			instance.setName(NAME);
		}

		return instance;
	}
}
