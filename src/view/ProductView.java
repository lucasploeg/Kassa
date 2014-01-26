package view;

import java.awt.Color;
import java.awt.Font;
import java.util.Arrays;
import java.util.HashMap;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.SwingConstants;
import javax.swing.table.DefaultTableModel;

import model.CounterModel;

public class ProductView extends JPanel {

	private static HashMap<Integer, ProductView> instances = new HashMap<Integer, ProductView>();
	public static final String NAME = "ProductView";
	private int counterNumber;
	private JTable table;
	private JLabel lblPriceEuros;

	private ProductView(int counterNumber) {
		setLayout(null);
		this.counterNumber = counterNumber;

		// Create columns names
		String[] columnNames = { "Product", "x", "Aantal", "à", "Prijs"};

		// Create some data
		String dataValues[][] = { { "Aardappelen", "x", "1", "à", "4.56" }};

		// Create a new table instance
		table = new JTable();
		table.setModel(new DefaultTableModel());
		DefaultTableModel dtm = (DefaultTableModel) table.getModel();
		dtm.setColumnIdentifiers(columnNames);
		
		table.setShowGrid(false);
		table.setEnabled(false);
		table.setFont(new Font("Tahoma", Font.BOLD, 18));
		table.setTableHeader(null);		
		table.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
		table.getColumnModel().getColumn(0).setPreferredWidth(209);
		table.getColumnModel().getColumn(1).setPreferredWidth(40);
		table.getColumnModel().getColumn(2).setPreferredWidth(75);
		table.getColumnModel().getColumn(3).setPreferredWidth(40);
		table.getColumnModel().getColumn(4).setPreferredWidth(90);
		

		// Add the table to a scrolling pane
		JScrollPane scrollPane = new JScrollPane(table);
		scrollPane.setBounds(47, 103, 473, 462);
		scrollPane.getViewport().setBackground(Color.WHITE);
		add(scrollPane);
		
		JLabel lblProducts = new JLabel("Producten");
		lblProducts.setHorizontalAlignment(SwingConstants.CENTER);
		lblProducts.setFont(new Font("Tahoma", Font.BOLD, 50));
		lblProducts.setBounds(47, 25, 473, 49);
		add(lblProducts);
		
		JLabel lblPrice = new JLabel("Totaalbedrag");
		lblPrice.setHorizontalAlignment(SwingConstants.CENTER);
		lblPrice.setFont(new Font("Tahoma", Font.BOLD, 50));
		lblPrice.setBounds(524, 14, 473, 71);
		add(lblPrice);
		
		lblPriceEuros = new JLabel("\u20AC 88,88");
		lblPriceEuros.setFont(new Font("Tahoma", Font.BOLD, 75));
		lblPriceEuros.setHorizontalAlignment(SwingConstants.CENTER);
		lblPriceEuros.setBounds(544, 107, 437, 204);
		add(lblPriceEuros);
		
		JButton btnPay = new JButton("Betalen");
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
		DefaultTableModel tableModel = (DefaultTableModel) table.getModel();
		tableModel.setRowCount(0);
		
		for(String[] arr : data){
			tableModel.addRow(arr);
		}

		tableModel.fireTableDataChanged();
	}
	
	public void setLblPrice(){
		Double price = Math.round(CounterModel.getInstance().getCounter(counterNumber).getCurrentCart().getCartPrice() * 100.0) / 100.0;		
		
		lblPriceEuros.setText("€ " + Double.toString(price));
	}
}
