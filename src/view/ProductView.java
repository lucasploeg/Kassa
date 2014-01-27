package view;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.HashMap;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.ListSelectionModel;
import javax.swing.SwingConstants;
import javax.swing.table.DefaultTableModel;

import model.CounterModel;
import controller.ProductController;
import entities.Survey;

public class ProductView extends JPanel {

	private static HashMap<Integer, ProductView> instances = new HashMap<Integer, ProductView>();
	public static final String NAME = "ProductView";
	private int counterNumber;
	private JTable table;
	private JLabel lblPriceEuros;
	private JLabel lblPrice;
	private JButton btnPay;
	private DefaultTableModel tableModel;
	private ListSelectionModel listSelectionModel;
	private JLabel lblSurveyFailure;
	private JLabel lblSurveyFailureEAN;
	private JLabel lblOtherCounter;

	private ProductView(int counterNumber) {
		setLayout(null);
		this.counterNumber = counterNumber;

		// Create columns names
		String[] columnNames = { "Product", "x", "Aantal", "à", "Prijs", "EAN"};

		// Create a new table instance
		table = new JTable();
		table.setModel(new DefaultTableModel());
		tableModel = (DefaultTableModel) table.getModel();
		tableModel.setColumnIdentifiers(columnNames);
		listSelectionModel = table.getSelectionModel();	
		
		table.setShowGrid(false);
		table.setEnabled(false);
		table.setFont(new Font("Tahoma", Font.BOLD, 20));
		table.setTableHeader(null);		
		table.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
		table.getColumnModel().getColumn(0).setPreferredWidth(209);
		table.getColumnModel().getColumn(1).setPreferredWidth(40);
		table.getColumnModel().getColumn(2).setPreferredWidth(75);
		table.getColumnModel().getColumn(3).setPreferredWidth(40);
		table.getColumnModel().getColumn(4).setPreferredWidth(90);
		table.removeColumn(table.getColumn("EAN"));
		

		// Add the table to a scrolling pane
		JScrollPane scrollPane = new JScrollPane(table);
		scrollPane.setBounds(47, 103, 473, 462);
		scrollPane.getViewport().setBackground(Color.WHITE);
		
		lblSurveyFailure = new JLabel("Product niet door klant gescand:");
		lblSurveyFailure.setFont(new Font("Tahoma", Font.BOLD, 25));
		lblSurveyFailure.setBounds(544, 103, 437, 71);
		add(lblSurveyFailure);
		
		lblSurveyFailureEAN = new JLabel("1234567891011");
		lblSurveyFailureEAN.setFont(new Font("Tahoma", Font.BOLD, 25));
		lblSurveyFailureEAN.setBounds(544, 173, 437, 71);
		add(lblSurveyFailureEAN);
		add(scrollPane);
		
		lblOtherCounter = new JLabel("Doorverwijzing andere kassa.");
		lblOtherCounter.setFont(new Font("Tahoma", Font.BOLD, 25));
		lblOtherCounter.setBounds(544, 240, 437, 71);
		add(lblOtherCounter);
		
		JLabel lblProducts = new JLabel("Producten");
		lblProducts.setHorizontalAlignment(SwingConstants.CENTER);
		lblProducts.setFont(new Font("Tahoma", Font.BOLD, 50));
		lblProducts.setBounds(47, 25, 473, 49);
		add(lblProducts);
		
		lblPrice = new JLabel("Totaalbedrag");
		lblPrice.setHorizontalAlignment(SwingConstants.CENTER);
		lblPrice.setFont(new Font("Tahoma", Font.BOLD, 50));
		lblPrice.setBounds(524, 14, 473, 71);
		add(lblPrice);
		
		lblPriceEuros = new JLabel("");
		lblPriceEuros.setFont(new Font("Tahoma", Font.BOLD, 75));
		lblPriceEuros.setHorizontalAlignment(SwingConstants.CENTER);
		lblPriceEuros.setBounds(544, 107, 437, 204);
		add(lblPriceEuros);
		
		btnPay = new JButton("Betalen");
		btnPay.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				payButtonPressed();
			}
		});
		btnPay.setFont(new Font("Tahoma", Font.BOLD, 50));
		btnPay.setBounds(544, 322, 422, 243);
		add(btnPay);

	}

	public static ProductView getInstance(int counterNumber) {
		ProductView instance = instances.get(counterNumber);

		if (instance == null) {
			instance = new ProductView(counterNumber);
			instance.setName(NAME);
			instances.put(counterNumber, instance);
		}

		return instance;
	}
	
	public void updateProductsTable(String[][] data){
		tableModel = (DefaultTableModel) table.getModel();
		tableModel.setRowCount(0);
		
		for(String[] arr : data){
			tableModel.addRow(arr);
		}

		tableModel.fireTableDataChanged();
		
		lblSurveyFailure.setVisible(false);
		lblSurveyFailureEAN.setVisible(false);
		lblOtherCounter.setVisible(false);
	}
	
	public void setLblPrice(){
		Double price = Math.round(CounterModel.getInstance().getCounter(counterNumber).getCurrentCart().getCartPrice() * 100.0) / 100.0;		
		
		lblPriceEuros.setText("€ " + Double.toString(price));
	}
	
	public void payButtonPressed(){
		ProductController.getInstance(counterNumber).payButtonPressed();
	}
	
	public void initiateSurvey(){
		lblPrice.setText("Steekproef");
		btnPay.setEnabled(false);
		lblPriceEuros.setText("0 / " + CounterModel.getInstance().getCounter(counterNumber).getCurrentCart().getSurvey().getProductsToCheck());
	}
	
	public void productScanned(boolean scanned, String EAN){
		Survey survey = CounterModel.getInstance().getCounter(counterNumber).getCurrentCart().getSurvey();
		System.out.println("Scanned: " + scanned + ". EAN: " + EAN + ". Left: " + survey.productsLeftToCheck());
		
		if(scanned){
			int rows = tableModel.getRowCount();
			int productRow = -1;
			
			for(int i=0; i<rows; i++){
				if(EAN.equals(tableModel.getValueAt(i, 5))){
					productRow = i;
					break;
				}
			}
			
			listSelectionModel.addSelectionInterval(productRow, productRow);
			
			lblPriceEuros.setText(survey.getScanned() + " / " + survey.getProductsToCheck());
			
			if(survey.getScanned() == survey.getProductsToCheck()){
				survey.setSurveyOK(true);
				btnPay.setEnabled(true);
			}
		} else {
			survey.setSurveyOK(false);
			
			lblSurveyFailureEAN.setText(EAN);
			btnPay.setText("OK");
			
			btnPay.setEnabled(true);
			lblSurveyFailure.setVisible(true);
			lblSurveyFailureEAN.setVisible(true);
			lblOtherCounter.setVisible(true);
			lblPriceEuros.setVisible(false);
		}
	}
}
