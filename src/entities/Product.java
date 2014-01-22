package entities;

public class Product {

	private String EAN;
	private String name;
	private Double price;
	
	public Product(String EAN, String name, Double price){
		this.EAN = EAN;
		this.name = name;
		this.price = price;
	}
	
	public String getEAN(){
		return EAN;
	}
	
	public String getName(){
		return name;
	}
	
	public Double getPrice(){
		return price;
	}
	
}
