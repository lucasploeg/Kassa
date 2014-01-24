package view;

import java.awt.CardLayout;
import java.awt.EventQueue;
import java.util.HashMap;

import javax.swing.JFrame;
import javax.swing.JPanel;

import controller.ViewController;

public class MainWindow {

	private static HashMap<Integer, MainWindow> instances = new HashMap<Integer, MainWindow>();

	private JFrame mainFrame;
	private JPanel mainPanel;
	private CardLayout layout;
	private int counterNumber;

	/**
	 * @wbp.parser.entryPoint
	 */
	public static MainWindow getInstance(final int counterNumber) {
		MainWindow instance = instances.get(counterNumber);

		if (instance == null) {
			instance = new MainWindow(counterNumber);
			instances.put(counterNumber, instance);
			EventQueue.invokeLater(new Runnable() {
				public void run() {
					try {
						instances.get(counterNumber).initialize();
						instances.get(counterNumber).mainFrame.setVisible(true);
					} catch (Exception e) {
						e.printStackTrace();
					}
				}
			});		
		}
		return instance;
	}

	/**
	 * Create the application.
	 */
	private MainWindow(int counterNumber) {
		this.counterNumber = counterNumber;
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		mainFrame = new JFrame();
		mainFrame.setTitle("Kassa");
		mainFrame.setBounds(100, 100, 1009, 632);
		mainFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		mainFrame.getContentPane().setLayout(null);

		mainPanel = new JPanel();
		mainPanel.setBounds(0, 0, 1000, 600);
		mainFrame.getContentPane().add(mainPanel);
		mainPanel.setLayout(new CardLayout(0, 0));

		layout = (CardLayout) (mainPanel.getLayout());

		for (JPanel view : ViewController.getInstance(counterNumber).getViews()) {
			mainPanel.add(view, view.getName());
		}

		show(ViewController.getInstance(counterNumber).getView(BarcodeView.NAME));
	}

	public void show(JPanel view) {
		System.out.println("Showing: " + view.getName());
		layout.show(mainPanel, view.getName());
	}
}
