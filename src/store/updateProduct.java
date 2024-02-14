package store;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.ArrayList;

public class updateProduct extends JPanel {

	// Components
	private final JTextField idField;
	private final JTextArea descField;
	private final JComboBox<String> company;
	private final JTextField quanField;
	private final JLabel error;

	// Fields
	private String id;
	private String detail;
	private String comp;
	private int quan;
	private final String err = "Enter product id and quantity";

	/**
	 * Create the panel.
	 */
	public updateProduct() {
		setLayout(null);
		setBounds(100, 100, 840, 619);

		// Label for error
		error = new JLabel();
		error.setForeground(Color.RED);
		error.setBounds(299, 95, 286, 14);
		add(error);

		// Title label
		JLabel lblUpdateProduct = new JLabel("Update Pproduct");
		lblUpdateProduct.setBounds(328, 45, 182, 21);
		lblUpdateProduct.setFont(new Font("Tahoma", Font.PLAIN, 17));
		add(lblUpdateProduct);


		// Label for product ID
		JLabel lblProductName = new JLabel("Product ID");
		lblProductName.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblProductName.setBounds(246, 136, 124, 21);
		add(lblProductName);

		// Label for product description
		JLabel lblProductDescription = new JLabel("Product detail");
		lblProductDescription.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblProductDescription.setBounds(246, 168, 139, 21);
		add(lblProductDescription);

		// Text field for product ID
		idField = new JTextField();
		idField.setBounds(449, 137, 136, 20);
		add(idField);
		idField.setColumns(10);

		// Text area for product description
		descField = new JTextArea();
		descField.setBounds(449, 168, 136, 58);
		add(descField);
		JScrollPane scroll = new JScrollPane(descField);
		scroll.setBounds(449, 168, 136, 58);
		add(scroll);

		// Label for company
		JLabel lblCompany = new JLabel("Company");
		lblCompany.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblCompany.setBounds(246, 241, 124, 21);
		add(lblCompany);

		// Initialize quanField
		quanField = new JTextField();
		quanField.setColumns(10);
		quanField.setBounds(449, 278, 136, 20);
		add(quanField);

		// Initialize company
		company = new JComboBox<>();
		company.setBounds(449, 243, 136, 20);
		add(company);
		company.addItem("General");
		company.addItem("Mats & Rugs");
		company.addItem("N/S & Electric");

		// Key listener for the idField
		idField.addKeyListener(new KeyListener() {
			@Override
			public void keyTyped(KeyEvent e) {
				id = idField.getText().trim() + e.getKeyChar();
				ArrayList<String> data = DBConnection.searchP(id);
				if (data.size() == 3) {
					descField.setText(data.get(0));
					quanField.setText(data.get(2));
					String companyValue = data.get(1);
					switch (companyValue) {
						case "General" -> company.setSelectedIndex(0);
						case "Mats & Rugs" -> company.setSelectedIndex(1);
						case "N/S & Electric" -> company.setSelectedIndex(2);
					}
				}
			}

			@Override
			public void keyPressed(KeyEvent e) {
			}

			@Override
			public void keyReleased(KeyEvent e) {
			}
		});

		// Button to update product
		JButton btnUpdateProduct = new JButton("Update Product");
		btnUpdateProduct.addActionListener(e -> {
			if (quanField.getText().equals("") || idField.getText().equals("")) {
				error.setText(err);
			} else {
				error.setText("");
				id = idField.getText().trim();
				quan = Integer.parseInt(quanField.getText().trim());
				detail = descField.getText().trim();
				comp = company.getSelectedItem() != null ? company.getSelectedItem().toString() : "";
				DBConnection.updateProductToDB(id, detail, comp, quan);
				idField.setText("");
				quanField.setText("");
				descField.setText("");
			}
		});
		btnUpdateProduct.setBounds(449, 338, 136, 23);
		add(btnUpdateProduct);

		// Label for quantity
		JLabel lblQuantity = new JLabel("Items available");
		lblQuantity.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblQuantity.setBounds(246, 276, 124, 21);
		add(lblQuantity);
	}
}