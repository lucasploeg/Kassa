package model;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.HashMap;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.google.gson.internal.LinkedTreeMap;

import entities.Product;

public class APIServer implements Runnable {

	private int counterNumber;
	private int port;
	
	public APIServer(int counterNumber){
		this.counterNumber = counterNumber;
		String portStr = "88" + Integer.toString(counterNumber) + "8";
		port = Integer.parseInt(portStr);
		
		//temp dummy cart
		//CounterModel.getInstance().getCounter(counterNumber).initiateNewCart();
	}
	
	@Override
	public void run() {
		ServerSocket serverSocket = null;
		Socket socket = null;
		DataOutputStream dataOutputStream = null;

		try {
			serverSocket = new ServerSocket(port);
			System.out.println("Listening for shopping lists.");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		while (true) {
			try {
				socket = serverSocket.accept();
				dataOutputStream = new DataOutputStream(
						socket.getOutputStream());
				
				BufferedReader streamReader = new BufferedReader(new InputStreamReader(socket.getInputStream())); 
			    StringBuilder responseStrBuilder = new StringBuilder();

			    String inputStr;
			    while ((inputStr = streamReader.readLine()) != null)
			        responseStrBuilder.append(inputStr);
			    JsonParser parser = new JsonParser();
			    JsonObject json = (JsonObject) parser.parse(responseStrBuilder.toString());
				
			    initiateCart(json);
			    
				boolean success = true;
				boolean end = true;
				if(end){
					String message;
					
					if(success){
						message = "OK";
					} else {
						message = "NotOK";
					}
					
					dataOutputStream.writeUTF(message);
					streamReader.close();
					dataOutputStream.close();
					socket.close();				
				}
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} finally {
				if (socket != null) {
					try {
						socket.close();
					} catch (IOException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
				if (dataOutputStream != null) {
					try {
						dataOutputStream.close();
					} catch (IOException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
			}
		}
	}
	
	public void initiateCart(JsonObject json){
		HashMap<Product, Integer> productList = new HashMap<Product, Integer>();
		
		JsonArray resultCustomer = json.get("userInformation").getAsJsonArray();
		ArrayList userList = new Gson().fromJson(resultCustomer, ArrayList.class);	
		
		int userID = -1;
		String lastName = "";
		String gender = "";
		
		for (int i = 0; i < userList.size(); i++) {
			LinkedTreeMap ltm = (LinkedTreeMap) userList.get(i);
			
			userID = Integer.parseInt((String)ltm.get("userid"));
			lastName = (String) ltm.get("surname");
			gender = (String) ltm.get("gender");
			
			//System.out.println(userID + "/" + lastName + "/" + gender);
		}
		
		JsonArray resultProducts = json.get("products").getAsJsonArray();
		ArrayList productListAL = new Gson().fromJson(resultProducts, ArrayList.class);	
		
		for (int i = 0; i < productList.size(); i++) {
			LinkedTreeMap ltm = (LinkedTreeMap) productListAL.get(i);
			
			String ean = (String) ltm.get("ean_code");
			String name = (String) ltm.get("name");
			int amount = Integer.parseInt((String) ltm.get("amount"));
			Double price = (Double.parseDouble((String) ltm.get("price"))/100);
			
			Product product = new Product(ean,name,price);
			productList.put(product, amount);
			//System.out.println(ean + "/" + name + "/" + amount + "/" + price);
		}
		
		boolean customerIsMale = (gender.equals("m") ? true : false);
		CounterModel.getInstance().getCounter(counterNumber).initiateNewCart(counterNumber,productList,userID,customerIsMale,lastName);
	}
}
