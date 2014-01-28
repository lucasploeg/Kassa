package view;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.HashMap;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;

import model.CounterModel;
import controller.BarcodeController;
import controller.ViewController;
import javax.swing.JCheckBox;

public class BarcodeView extends JPanel {

	private static HashMap<Integer, BarcodeView> instances = new HashMap<Integer, BarcodeView>();
	public static final String NAME = "BarcodeView";
	private int counterNumber;

	JLabel lblQuestion;
	private JCheckBox checkSurvey;

	private BarcodeView(int counterNumber) {
		setLayout(null);
		this.counterNumber = counterNumber;
		lblQuestion = new JLabel("lblQuestion");
		lblQuestion.setHorizontalAlignment(SwingConstants.CENTER);
		lblQuestion.setFont(new Font("Tahoma", Font.BOLD, 50));
		lblQuestion.setBounds(45, 105, 927, 128);
		add(lblQuestion);

		setLblQuestionText();
		setLblQR();

	}

	public static BarcodeView getInstance(int counterNumber) {
		BarcodeView instance = instances.get(counterNumber);

		if (instance == null) {
			instance = new BarcodeView(counterNumber);
			instance.setName(NAME);
			instances.put(counterNumber, instance);
		}

		return instance;
	}

	private void setLblQuestionText() {
		lblQuestion.setText(BarcodeController.getInstance(counterNumber)
				.getScanMessage());
	}

	private void setLblQR() {
		ImageIcon image = new ImageIcon(BarcodeController.getInstance(
				counterNumber).getScanImage());

		JLabel lblQR = new JLabel("", image, JLabel.CENTER);
		lblQR.setBounds(383, 258, 225, 239);
		add(lblQR);

		JButton btnGaVerdertemp = new JButton("Continue_Temp");
		btnGaVerdertemp.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				ViewController.getInstance(counterNumber).showView(QuestionView.NAME);
			}
		});
		btnGaVerdertemp.setBounds(10, 37, 148, 23);
		add(btnGaVerdertemp);
		btnGaVerdertemp.setVisible(true);

		checkSurvey = new JCheckBox("Survey on (PoC purposes)");
		checkSurvey.setSelected(true);
		CounterModel.getInstance().getCounter(counterNumber).SURVEY_TURNED_ON = true;
		checkSurvey.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if (checkSurvey.isSelected()) {
					CounterModel.getInstance().getCounter(counterNumber).SURVEY_TURNED_ON = true;
				} else {
					CounterModel.getInstance().getCounter(counterNumber).SURVEY_TURNED_ON = false;
				}
			}
		});
		checkSurvey.setFont(new Font("Tahoma", Font.BOLD, 14));
		checkSurvey.setBounds(10, 7, 212, 23);
		add(checkSurvey);
	}

	public boolean surveyIsEnabled() {
		if (checkSurvey.isSelected()) {
			return true;
		} else {
			return false;
		}
	}
}
