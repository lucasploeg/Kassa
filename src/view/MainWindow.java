package view;

import java.awt.EventQueue;

import javax.swing.JFrame;

public class MainWindow {

	private static MainWindow instance;
	private JFrame frame;

	public static MainWindow getInstance() {
		if (instance == null) {
			instance = new MainWindow();
			EventQueue.invokeLater(new Runnable() {
				public void run() {
					try {
						instance.initialize();
						instance.frame.setVisible(true);
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
	private MainWindow() {
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 450, 300);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}

}
