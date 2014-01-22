package model;

public class Counter {
	
	private int counterNumber;
	
	private Counter(int counterNumber){
		this.counterNumber = counterNumber;
	}
	
	public int getCounterNumber(){
		return counterNumber;
	}
	
	public BufferedImage getCounterQR(){
		return BarcodeModel.getInstance().getQR(counterNumber);
	}
}
