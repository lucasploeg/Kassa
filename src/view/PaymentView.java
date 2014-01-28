package view;

import java.util.HashMap;

import javax.swing.JPanel;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.SwingConstants;

import model.BankModel;
import model.CounterModel;

import controller.ViewController;

public class PaymentView extends JPanel {

	private static HashMap<Integer, PaymentView> instances = new HashMap<Integer, PaymentView>();
	public static final String NAME = "PaymentView";
	private int counterNumber;
	private JLabel lblPaying;
	private JLabel lblPaymentOK;
	private JLabel lblBye;
	private JLabel lblShutdown;

	private PaymentView(int counterNumber) {
		setLayout(null);
		this.counterNumber = counterNumber;

		lblPaying = new JLabel("Betaling accorderen...");
		lblPaying.setHorizontalAlignment(SwingConstants.CENTER);
		lblPaying.setFont(new Font("Tahoma", Font.BOLD, 50));
		lblPaying.setBounds(28, 38, 927, 128);
		add(lblPaying);

		lblPaymentOK = new JLabel("Betaling ok.");
		lblPaymentOK.setHorizontalAlignment(SwingConstants.CENTER);
		lblPaymentOK.setFont(new Font("Tahoma", Font.BOLD, 50));
		lblPaymentOK.setBounds(28, 129, 927, 128);
		add(lblPaymentOK);

		lblBye = new JLabel("Tot ziens!");
		lblBye.setHorizontalAlignment(SwingConstants.CENTER);
		lblBye.setFont(new Font("Tahoma", Font.BOLD, 50));
		lblBye.setBounds(28, 282, 927, 128);
		add(lblBye);

		lblShutdown = new JLabel(". . . ");
		lblShutdown.setHorizontalAlignment(SwingConstants.CENTER);
		lblShutdown.setFont(new Font("Tahoma", Font.BOLD, 50));
		lblShutdown.setBounds(28, 421, 927, 128);
		add(lblShutdown);
	}

	public static PaymentView getInstance(int counterNumber) {
		PaymentView instance = instances.get(counterNumber);

		if (instance == null) {
			instance = new PaymentView(counterNumber);
			instance.setName(NAME);
			instances.put(counterNumber, instance);
		}

		return instance;
	}

	public void updateGUI() {
		(new Thread(new UpdatePaymentGUI())).start();
	}

	private class UpdatePaymentGUI implements Runnable {

		@Override
		public void run() {
			lblPaymentOK.setVisible(false);
			lblBye.setVisible(false);
			lblShutdown.setVisible(false);

			String paying = "Betaling accorderen";
			String paymentOK = "Betaling ok.";

			lblPaying.setText(paying);
			int pin = 1234;
			if (BankModel.getInstance().pinNumberIsOk(pin)) {
				if (BankModel.getInstance().transactionIsOk(CounterModel.getInstance().getCounter(counterNumber).getCurrentCart().getCartPrice())) {

					for (int i = 0; i < 3; i++) {
						sleep(1000);
						lblPaying.setText(lblPaying.getText() + ".");
					}

					sleep(1000);
					lblPaymentOK.setVisible(true);
					sleep(1000);
					lblBye.setVisible(true);

					lblShutdown.setText("");
					lblShutdown.setVisible(true);
					for (int i = 0; i < 5; i++) {
						sleep(1300);
						lblShutdown.setText(lblShutdown.getText() + ". ");
					}
					sleep(1300);
					CounterModel.getInstance().getCounter(counterNumber).scanForIncomingCarts();
					ViewController.getInstance(counterNumber).showView(BarcodeView.NAME);
				} else if (!BankModel.getInstance().transactionIsOk(CounterModel.getInstance().getCounter(counterNumber).getCurrentCart().getCartPrice())) {
					// Not enough money. For PoC not needed.
				}
			} else if (!BankModel.getInstance().pinNumberIsOk(pin)) {
				// Pincode not ok. For PoC not needed.
			}
		}

		private void sleep(int ms) {
			try {
				Thread.sleep(ms);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}

	}
}
