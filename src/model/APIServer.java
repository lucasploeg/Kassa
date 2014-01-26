package model;

public class APIServer {

	private int counterNumber;
	
	public APIServer(int counterNumber){
		this.counterNumber = counterNumber;
		
		CounterModel.getInstance().getCounter(counterNumber).initiateNewCart();
	}
	
}
