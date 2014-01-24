package view;

import java.awt.CardLayout;
import java.awt.EventQueue;
import java.util.ArrayList;

import javax.swing.JFrame;
import javax.swing.JPanel;

public class MainWindow {

	private static MainWindow instance;
	private JFrame mainFrame;
	private JPanel mainPanel;
	private CardLayout layout;
	private ArrayList<JPanel> views;

	/**
	 * @wbp.parser.entryPoint
	 */
	public static MainWindow getInstance(ArrayList<JPanel> views) {
		if (instance == null) {
			instance = new MainWindow(views);
			EventQueue.invokeLater(new Runnable() {
				public void run() {
					try {
						instance.initialize();
						instance.mainFrame.setVisible(true);
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
	private MainWindow(ArrayList<JPanel> views) {
		this.views = views;
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		mainFrame = new JFrame();
		mainFrame.setTitle("Kassa");
		mainFrame.setBounds(100, 100, 1300, 803);
		mainFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		mainFrame.getContentPane().setLayout(null);
		
		mainPanel = new JPanel();
		mainPanel.setBounds(0, 0, 1284, 765);
		mainFrame.getContentPane().add(mainPanel);
		mainPanel.setLayout(new CardLayout(0, 0));
		
		layout = (CardLayout)(mainPanel.getLayout());
		
		for(JPanel view : views){
			mainPanel.add(view, view.getName());
		}
	}
	
	public void show(JPanel panel){
		System.out.println(panel.getName());
		layout.show(mainPanel, panel.getName());
	}
}
