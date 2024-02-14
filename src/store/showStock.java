package store;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.util.ArrayList;

/**
 * Panel to display available stock information.
 */
public class showStock extends JPanel {
	JTable stockTable;
	JComboBox<String> comp;
	DefaultTableModel model;

	/**
	 * Create the panel.
	 */
	public showStock() {
		// Set panel layout and bounds
		setLayout(null);
		setBounds(100, 100, 840, 619);

		// Label for displaying title
		JLabel lblStock = new JLabel("Available Stock");
		lblStock.setBounds(328, 26, 182, 21);
		lblStock.setFont(new Font("Tahoma", Font.PLAIN, 17));
		add(lblStock);

		// Initialize table model and table
		model = new DefaultTableModel();
		stockTable = new JTable(model);
		stockTable.setBounds(98, 112, 645, 397);
		add(stockTable);

		// Add column headers to the table model
		model.addColumn("Product ID");
		model.addColumn("Product Detail");
		model.addColumn("Company");
		model.addColumn("Quantity");

		// Add scroll pane for table
		JScrollPane scroll = new JScrollPane(stockTable);
		scroll.setBounds(98, 112, 645, 397);
		add(scroll);

		// Dropdown for selecting company
		comp = new JComboBox<>();
		comp.setBackground(Color.WHITE);
		comp.setBounds(583, 81, 160, 20);
		add(comp);
		comp.addItem("All");
		comp.addItem("General");
		comp.addItem("Mats & Rugs");
		comp.addItem("N/S & Electric");

		// Label for company selection
		JLabel lblCompany = new JLabel("Company");
		lblCompany.setBounds(582, 68, 161, 14);
		add(lblCompany);

		// Button to refresh stock information
		JButton btnRefresh = new JButton("Refresh");
		btnRefresh.addActionListener(e -> updateTable());
		btnRefresh.setBounds(457, 525, 138, 23);
		add(btnRefresh);

		// Load initial stock information
		updateTable();
	}

	/**
	 * Update the table with stock information based on the selected company.
	 */
	public void updateTable() {
		// Clear existing table rows
		model.setRowCount(0);

		// Get selected company from dropdown
		String selectedCompany = (String) comp.getSelectedItem();

		// If a company is selected, retrieve stock information and populate the table
		if (selectedCompany != null) {
			ArrayList<String> stock = DBConnection.showStock(selectedCompany);
			for (int x = 0; x < stock.size(); x += 4) {
				// Add stock data to the table model
				model.addRow(new Object[]{stock.get(x), stock.get(x + 1), stock.get(x + 2), stock.get(x + 3)});
			}
		}
	}
}