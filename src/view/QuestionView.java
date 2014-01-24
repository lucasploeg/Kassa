package view;

import java.util.HashMap;

import javax.swing.JPanel;

public class QuestionView extends JPanel{
	
	private static HashMap<Integer,QuestionView> instances = new HashMap<Integer,QuestionView>();
	public static final String NAME = "QuestionView";
	private int counterNumber;
	
	private QuestionView(int counterNumber) {
		setLayout(null);
		this.counterNumber = counterNumber;
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
}
