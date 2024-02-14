package store;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.util.ArrayList;

public class Sale extends JPanel {
	// Variable declarations
	final JTable sale;
	private final JTextField date;
	private JComboBox<String> comp;
	private String com, dt;
	private static DefaultTableModel dtm;

	/**
	 * Constructor for the Sale class.
	 */
	public Sale() {
		// Initializing GUI components
		setLayout(null);

		// Creating table and table model
		String[] header = {"Date", "Product ID", "Company", "Sale"};
		dtm = new DefaultTableModel(header, 0);
		sale = new JTable(dtm);
		sale.setBounds(244, 69, 494, 379);
		add(sale);
		JScrollPane s = new JScrollPane(sale);
		s.setBounds(244, 69, 494, 379);
		add(s);

		// Adding components for daily or monthly selection
		JRadioButton ds = new JRadioButton("Daily Sale");
		ds.setFont(new Font("Tahoma", Font.PLAIN, 18));
		ds.setBounds(62, 69, 109, 23);
		add(ds);
		ds.setSelected(true);

		JRadioButton ms = new JRadioButton("Monthly Sale");
		ms.setFont(new Font("Tahoma", Font.PLAIN, 18));
		ms.setBounds(62, 112, 144, 23);
		add(ms);

		// Adding labels and text fields for date
		JLabel lblDate = new JLabel("Date");
		lblDate.setFont(new Font("Tahoma", Font.PLAIN, 18));
		lblDate.setBounds(62, 178, 75, 14);
		add(lblDate);

		date = new JTextField();
		date.setBounds(62, 199, 86, 20);
		add(date);
		date.setColumns(10);

		JLabel lblMm = new JLabel("yyyy/mm/dd");
		lblMm.setBounds(149, 202, 85, 14);
		add(lblMm);

		// Adding button for loading data
		JButton btnLoad = new JButton("LOAD");
		btnLoad.addActionListener(e -> {
			dt = date.getText().trim();
			Object selectedCompany = comp.getSelectedItem();
			if (selectedCompany != null) {
				com = selectedCompany.toString();
				if (ds.isSelected()) {
					updateTable(DBConnection.getSale(dt, com));
				} else {
					loadMonthlySale(dt, com);
				}
			}
		});
		btnLoad.setBounds(62, 328, 89, 23);
		add(btnLoad);

		// Adding labels and dropdown menu for company
		JLabel lblCompany = new JLabel("Company");
		lblCompany.setFont(new Font("Tahoma", Font.PLAIN, 18));
		lblCompany.setBounds(62, 230, 109, 27);
		add(lblCompany);

		comp = new JComboBox<>();
		comp.setBounds(62, 261, 86, 20);
		add(comp);
		comp.addItem("All");
		comp.addItem("General");
		comp.addItem("Mats & Rugs");
		comp.addItem("N/S & Electric");

		// Adding action listeners for daily or monthly selection
		ms.addActionListener(e -> ds.setSelected(false));
		ds.addActionListener(e -> ms.setSelected(false));
	}

	/**
	 * Method to update the table with daily sale data.
	 * @param sl ArrayList of strings representing sale data.
	 */
	private void updateTable(ArrayList<String> sl) {
		if (!sl.isEmpty()) {
			clearTableModel();
			addRowsToTableModel(sl);
		}
	}

	/**
	 * Method to load the table with monthly sale data.
	 * @param dt The selected date for loading the data.
	 * @param com The selected company for loading the data.
	 */
	private void loadMonthlySale(String dt, String com) {
		clearTableModel();
		String[] date = dt.split("/");
		String[] s = {"", "01", "02", "03", "04", "05", "06", "07", "08", "09", "10", "11", "12", "13", "14", "15", "16", "17", "18", "19", "20", "21", "22", "23", "24", "25", "26", "27", "28", "29", "30", "31"};
		for (int x = 1; x <= 31; x++) {
			String ndt = date[0] + "/" + date[1] + "/" + s[x].trim();
			ArrayList<String> sl = DBConnection.getSale(ndt, com);
			if (!sl.isEmpty()) {
				addRowsToTableModel(sl);
			}
		}
	}

	/**
	 * Method to clear all rows from the table model.
	 */
	private void clearTableModel() {
		dtm.setRowCount(0);
	}

	/**
	 * Method to add a row of data to the table model.
	 * @param sl ArrayList of strings representing sale data for a single row.
	 */
	private void addRowsToTableModel(ArrayList<String> sl) {
		String x1, x2, x3, x4;
		int sz = sl.size() / 4;
		for (int i = 0; i < sz; i++) {
			x1 = sl.get(0);
			sl.remove(0);
			x2 = sl.get(0);
			sl.remove(0);
			x3 = sl.get(0);
			sl.remove(0);
			x4 = sl.get(0);
			sl.remove(0);
			dtm.addRow(new Object[]{x1, x2, x3, x4});
		}
	}
}