package model;

public class ControlQuestionModel {

	private static ControlQuestionModel instance;
	
	private static final String helloMsg = "Dag";
	private static final String question = "Wilt u afrekenen?";
	private static final String answerYes = "Ja";
	private static final String answerNo = "Nee";
	
	private ControlQuestionModel(){
		//empty
	}
	
	public static ControlQuestionModel getInstance(){
		if(instance == null){
			instance = new ControlQuestionModel();
		}
		
		return instance;
	}
	
	public String getControleQuestion(String firstName, String lastName){
		return (helloMsg + " " +  firstName + " " + lastName);
	}
	
	public String getQuestion(){
		return question;
	}
	
	public String getAnswerYes(){
		return answerYes;
	}
	
	public String getAnswerNo(){
		return answerNo;
	}
	
}
