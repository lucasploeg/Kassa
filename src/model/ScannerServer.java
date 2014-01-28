package model;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;

import entities.Survey;

public class ScannerServer implements Runnable {

	private Survey survey;
	private int port;

	private ServerSocket serverSocket;
	private Socket socket;
	private DataInputStream dataInputStream;
	private DataOutputStream dataOutputStream;
	private boolean running = true;

	public ScannerServer(Survey survey) {
		this.survey = survey;

		String portStr = "888" + Integer.toString(survey.getCart().getCounterNumber());
		port = Integer.parseInt(portStr);
	}

	@Override
	public void run() {
		serverSocket = null;
		socket = null;
		dataInputStream = null;
		dataOutputStream = null;

		try {
			serverSocket = new ServerSocket(port);
			System.out.println("Listening for products.");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		while (running) {
			try {
				if (!serverSocket.isClosed()) {
					socket = serverSocket.accept();
					dataInputStream = new DataInputStream(socket.getInputStream());
					dataOutputStream = new DataOutputStream(socket.getOutputStream());

					InetAddress IP = socket.getInetAddress();
					String EAN = dataInputStream.readUTF();

					// System.out.println("ip: " + IP);
					// System.out.println("message: " + EAN);

					survey.addProductToScannedList(EAN);
					// System.out.println("From server: " + survey);

					System.out.println("Left: " + survey.productsLeftToCheck());

					if (survey.productsLeftToCheck() == 1 || !survey.getSurveyActive()) {
						closeSocket();
					} else {
						dataOutputStream.writeUTF("continue");
					}
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

	public void closeSocket() {
		try {
			System.out.println("Closing socket for scanner.");
			dataOutputStream.writeUTF("quit");
			socket.close();
			dataInputStream.close();
			dataOutputStream.close();
			serverSocket.close();
			running = false;
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
