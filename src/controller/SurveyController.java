package controller;

public class SurveyController {

	private static SurveyController instance;

	private SurveyController() {
		// empty
	}

	public static SurveyController getInstance() {
		if (instance == null) {
			instance = new SurveyController();
		}

		return instance;
	}
	
	
}
