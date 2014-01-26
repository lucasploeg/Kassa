package controller;

import java.util.ArrayList;
import java.util.HashMap;

import javax.swing.JPanel;

import view.BarcodeView;
import view.MainWindow;
import view.PaymentView;
import view.ProductView;
import view.QuestionView;

public class ViewController {

	private static HashMap<Integer, ViewController> instances = new HashMap<Integer, ViewController>();

	private int counterNumber;
	private MainWindow mainWindow;
	private ArrayList<JPanel> views;

	private ViewController(int counterNumber) {
		this.counterNumber = counterNumber;
		initiateViews();		
		mainWindow = MainWindow.getInstance(counterNumber);
	}

	public static ViewController getInstance(int counterNumber) {
		ViewController instance = instances.get(counterNumber);
		
		if (instance == null) {
			instance = new ViewController(counterNumber);
			instances.put(counterNumber, instance);
		}

		return instance;
	}

	private void initiateViews() {
		views = new ArrayList<JPanel>();

		views.add(BarcodeView.getInstance(counterNumber));
		views.add(PaymentView.getInstance(counterNumber));
		views.add(ProductView.getInstance(counterNumber));
		views.add(QuestionView.getInstance(counterNumber));
	}

	public void showView(String name) {
		for (JPanel view : views) {
			if (view.getName().equals(name)) {
				mainWindow.show(view);
				break;
			}
		}
	}

	public JPanel getView(String name) {
		for (JPanel view : views) {
			if (view.getName().equals(name)) {
				return view;
			}
		}
		return null;
	}
	
	public ArrayList<JPanel> getViews(){
		return views;
	}
}
