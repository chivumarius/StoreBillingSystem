package store;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.util.ArrayList;

public class Invoice extends JPanel {
	private JTextField name;
	private final JTextField pID = null;
	private final JTextField pQuan = null;
	private final JTable items;
	private JTextField UnitPrice;
	JLabel lblName;
	JLabel error;
	long total = 0;
	JLabel gTotal;

	ArrayList<String> comp = new ArrayList<>();
	DefaultTableModel dtm;

	public Invoice() {
		setLayout(null);

		// Customer label
		JLabel lblCustomer = new JLabel("Customer");
		lblCustomer.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblCustomer.setBounds(88, 59, 97, 23);
		add(lblCustomer);

		// Customer type combo box
		JComboBox<String> cType = new JComboBox<>();
		cType.setBounds(201, 62, 89, 20);
		add(cType);
		cType.addItem("Walk-in customer");
		cType.addItem("Company/customer name");
		cType.setSelectedIndex(1);
		// Listener for customer type change
		cType.addItemListener(arg0 -> {
			if (cType.getSelectedIndex() == 1) {
				lblName.setVisible(true);
				name.setVisible(true);
			} else {
				lblName.setVisible(false);
				name.setVisible(false);
			}
		});

		// Name label
		lblName = new JLabel("Name");
		lblName.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblName.setBounds(364, 59, 64, 23);
		add(lblName);

		// Name text field
		name = new JTextField();
		name.setBounds(438, 62, 150, 20);
		add(name);
		name.setColumns(10);

		// Product ID label
		JLabel lblProductId = new JLabel("Product ID");
		lblProductId.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblProductId.setBounds(88, 383, 80, 23);
		add(lblProductId);

		// Quantity label
		JLabel lblQuantity = new JLabel("Quantity");
		lblQuantity.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblQuantity.setBounds(88, 174, 97, 23);
		add(lblQuantity);

		// Product ID text field
		JTextField pID = new JTextField();
		pID.setBounds(201, 133, 89, 20);
		add(pID);
		pID.setColumns(10);

		// Quantity text field
		JTextField pQuan = new JTextField();
		pQuan.setColumns(10);
		pQuan.setBounds(201, 177, 89, 20);
		add(pQuan);

		// Add button
		JButton btnAdd = new JButton("Add");

		btnAdd.addActionListener(arg0 -> {
			// Check if any of the input fields are empty
			if (UnitPrice.getText().equals("") || pQuan.getText().equals("") || pID.getText().equals("")) {
				error.setText("Enter required data");
				return;
			} else
				error.setText("");

			long up, fp, q;
			String id, detail;
			// Parse input values to appropriate types
			up = Long.parseLong(UnitPrice.getText().trim());
			q = Long.parseLong(pQuan.getText().trim());
			id = pID.getText().trim();
			// Clear input fields after processing
			UnitPrice.setText("");
			pQuan.setText("");
			pID.setText("");
			fp = (up * q);
			// Search for product details based on the provided ID and quantity
			ArrayList<String> detailList = DBConnection.searchPDetail(id, (int) q);
			// Handle cases where product details are not found or item is out of stock
			if (detailList.get(0).equals("null")) {
				error.setText("Invalid product id!");
				return;
			} else if (detailList.get(0).equals("item is out of stock")) {
				error.setText(detailList.get(0));
				return;
			} else {
				error.setText("");
				comp.add(detailList.get(1));
			}
			// Add a row to the table with the product details and calculated final price
			dtm.addRow(new Object[]{id, detailList.get(0), up, q, fp});
			total += fp; // Add the final product value to total
			gTotal.setText(String.valueOf(total));

		});

		btnAdd.setBounds(201, 265, 89, 23);
		add(btnAdd);

		// Table header
		String[] header = {"Product ID", "Item Details", "Unit Price", "Quantity", "Final Price"};
		dtm = new DefaultTableModel(header, 0);
		// Table
		items = new JTable(dtm);
		items.setBounds(361, 135, 316, 298);
		// Scroll pane for the table
		JScrollPane s = new JScrollPane(items);
		s.setEnabled(false);
		s.setBounds(361, 135, 392, 265);
		add(s);

		// Unit price text field
		UnitPrice = new JTextField();
		UnitPrice.setColumns(10);
		UnitPrice.setBounds(201, 220, 89, 20);
		add(UnitPrice);

		// Unit price label
		JLabel lblUnitPrice = new JLabel("Unit Price");
		lblUnitPrice.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblUnitPrice.setBounds(88, 217, 97, 23);
		add(lblUnitPrice);

		// Error label
		error = new JLabel("");
		error.setForeground(Color.RED);
		error.setBounds(92, 319, 218, 14);
		add(error);

		// Delete product label
		JLabel lblDeleteProduct = new JLabel("Delete Product");
		lblDeleteProduct.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblDeleteProduct.setBounds(88, 344, 132, 14);
		add(lblDeleteProduct);

		// Product ID label for deletion
		JLabel label = new JLabel("Product ID");
		label.setFont(new Font("Tahoma", Font.PLAIN, 14));
		label.setBounds(88, 135, 80, 23);
		add(label);

		// Product ID text field for deletion
		JTextField dField = new JTextField();
		dField.setColumns(10);
		dField.setBounds(201, 386, 89, 20);
		add(dField);


		// Delete button
		JButton delButton = new JButton("Delete");
		delButton.addActionListener(arg0 -> {
			String df = dField.getText().trim();
			for (int row = 0; row < dtm.getRowCount(); row++) {
				if (items.getValueAt(row, 0).equals(df)) {
					long q = (long) items.getValueAt(row, 3);
					String i = (String) items.getValueAt(row, 1);
					DBConnection.updateProduct(i, (int) q);
					total -= (long) items.getValueAt(row, 4);
					dtm.removeRow(row);
					gTotal.setText(Long.toString(total));
					dField.setText("");
					break;
				}
			}
		});
		delButton.setBounds(201, 440, 89, 23);
		add(delButton);

		// Grand total label
		JLabel lblGrandTotal = new JLabel("Grand total");
		lblGrandTotal.setFont(new Font("Tahoma", Font.BOLD, 15));
		lblGrandTotal.setBounds(364, 449, 89, 14);
		add(lblGrandTotal);

		// Grand total value label
		gTotal = new JLabel("");
		gTotal.setFont(new Font("Tahoma", Font.BOLD, 15));
		gTotal.setBounds(470, 449, 132, 14);
		add(gTotal);
	}
}