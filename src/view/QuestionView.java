package view;

import javax.swing.JPanel;

public class QuestionView extends JPanel{
	
	private static QuestionView instance;
	public static final String NAME = "QuestionView";
	
	private QuestionView() {
		setLayout(null);
	}

	public static QuestionView getInstance() {
		if (instance == null) {
			instance = new QuestionView();
			instance.setName(NAME);
		}

		return instance;
	}
}
