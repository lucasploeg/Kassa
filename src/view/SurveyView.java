package view;

import javax.swing.JPanel;

public class SurveyView extends JPanel{
	
	private static SurveyView instance;
	public static final String NAME = "SurveyView";
	
	private SurveyView() {
		setLayout(null);
	}

	public static SurveyView getInstance() {
		if (instance == null) {
			instance = new SurveyView();
			instance.setName(NAME);
		}

		return instance;
	}
}
