package view;

import java.util.HashMap;

import javax.swing.JPanel;
import javax.swing.JTextPane;

public class SurveyView extends JPanel{
	
	private static HashMap<Integer,SurveyView> instances = new HashMap<Integer,SurveyView>();
	public static final String NAME = "SurveyView";
	private int counterNumber;
	
	private SurveyView(int counterNumber) {
		setLayout(null);
		this.counterNumber = counterNumber;
		JTextPane textPane = new JTextPane();
		textPane.setBounds(144, 67, 122, 80);
		add(textPane);
	}

	public static SurveyView getInstance(int counterNumber) {
		SurveyView instance = instances.get(counterNumber);
		
		if (instance == null) {
			instance = new SurveyView(counterNumber);
			instance.setName(NAME);
			instances.put(counterNumber,instance);
		}

		return instance;
	}
}
