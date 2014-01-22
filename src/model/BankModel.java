package model;

public class BankModel {

	private static BankModel instance;
	
	private BankModel(){
		//empty
	}
	
	public static BankModel getInstance(){
		if(instance == null){
			instance = new BankModel();
		}
		
		return instance;
	}
	
	public boolean transactionIsOk(double money){
		return true;
	}
	
	public boolean pinNumberIsOk(int number){
		return true;
	}
}
