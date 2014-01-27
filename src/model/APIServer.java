package model;

import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;

import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

public class APIServer implements Runnable {

	private int counterNumber;
	private int port;
	
	public APIServer(int counterNumber){
		this.counterNumber = counterNumber;
		String portStr = "88" + Integer.toString(counterNumber) + "8";
		port = Integer.parseInt(portStr);
		
		//temp dummy cart
		CounterModel.getInstance().getCounter(counterNumber).initiateNewCart();
	}
	
	@Override
	public void run() {
		ServerSocket serverSocket = null;
		Socket socket = null;
		DataInputStream dataInputStream = null;
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
			    
				/*InetAddress IP = socket.getInetAddress();
				String line = dataInputStream.readUTF();
				System.out.println("-- API Server");
				System.out.println("ip: " + IP);
				System.out.println("message: " + line);*/
				
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
					socket.close();
					dataInputStream.close();
					dataOutputStream.close();
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

				if (dataInputStream != null) {
					try {
						dataInputStream.close();
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
}
