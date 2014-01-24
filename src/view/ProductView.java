package view;

import java.util.HashMap;

import javax.swing.JPanel;

public class ProductView extends JPanel {

	private static HashMap<Integer, ProductView> instances = new HashMap<Integer, ProductView>();
	public static final String NAME = "ProductView";
	private int counterNumber;

	private ProductView(int counterNumber) {
		setLayout(null);
		this.counterNumber = counterNumber;
	}

	public static ProductView getInstance(int counterNumber) {
		ProductView instance = instances.get(counterNumber);

		if (instance == null) {
			instance = new ProductView(counterNumber);
			instance.setName(NAME);
			instances.put(counterNumber, instance);
		}

		return instance;
	}
}
