package controller;

import java.util.ArrayList;

import javax.swing.JPanel;

import view.BarcodeView;
import view.MainWindow;
import view.PaymentView;
import view.ProductView;
import view.QuestionView;
import view.SurveyView;

public class ViewController {

	private static ViewController instance;
	
	private MainWindow mainWindow;
	private ArrayList<JPanel> views;

	private ViewController() {
		initiateViews();
		
		mainWindow = MainWindow.getInstance(views);
		setView(SurveyView.NAME);
	}

	public static ViewController getInstance() {
		if (instance == null) {
			instance = new ViewController();
		}

		return instance;
	}
	
	private void initiateViews(){
		views = new ArrayList<JPanel>();
		
		views.add(BarcodeView.getInstance());
		views.add(PaymentView.getInstance());
		views.add(ProductView.getInstance());
		views.add(QuestionView.getInstance());
		views.add(SurveyView.getInstance());
	}
	
	public void setView(String name){
		for(JPanel view : views){
			if(view.getName().equals(name)){
				mainWindow.show(view);
			}
		}
	}
}
