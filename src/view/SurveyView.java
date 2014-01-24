package view;

import javax.swing.JPanel;
import javax.swing.JTextPane;

public class SurveyView extends JPanel{
	
	private static SurveyView instance;
	public static final String NAME = "SurveyView";
	
	private SurveyView() {
		setLayout(null);
		
		JTextPane textPane = new JTextPane();
		textPane.setBounds(144, 67, 122, 80);
		add(textPane);
	}

	public static SurveyView getInstance() {
		if (instance == null) {
			instance = new SurveyView();
			instance.setName(NAME);
		}

		return instance;
	}
}
