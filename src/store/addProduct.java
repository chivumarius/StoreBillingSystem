package store;

// Imports
import javax.swing.*;
import java.awt.*;

// Class definition
public class addProduct extends JPanel {

	// Components
	JTextField idField;
	JTextArea descField;
	JTextField quanField;
	JLabel error;
	JComboBox<String> company;

	// Fields
	String id, detail, comp;
	int quan;
	String err = "Enter product id and quantity";

	/**
	 * Create the panel.
	 */
	public addProduct() {
		// Set layout and bounds
		setLayout(null);
		setBounds(100, 100, 840, 619);

		// Title label
		JLabel lblAddProduct = new JLabel("Add Product");
		lblAddProduct.setBounds(328, 45, 115, 21);
		lblAddProduct.setFont(new Font("Tahoma", Font.PLAIN, 17));
		add(lblAddProduct);

		// Label for product ID
		JLabel lblProductName = new JLabel("Product ID");
		lblProductName.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblProductName.setBounds(246, 136, 124, 21);
		add(lblProductName);

		// Label for product description
		JLabel lblProductDescription = new JLabel("Product Details");
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

		// Button to add product
		JButton btnAddProduct = new JButton("Add Product");
		btnAddProduct.addActionListener(e -> {
			// Action performed when the button is clicked
			if(quanField.getText().equals("") || idField.getText().equals("")) {
				error.setText(err);
			} else {
				error.setText("");
				id = idField.getText().trim();
				quan = Integer.parseInt(quanField.getText().trim());
				detail = descField.getText().trim();
				if (company.getSelectedItem() != null) { // Check for null selection
					comp = company.getSelectedItem().toString();
				} else {
					comp = ""; // Set comp to empty string if selection is null
				}
				DBConnection.addProductToDB(id, detail, comp, quan);
				idField.setText("");
				quanField.setText("");
				descField.setText("");
			}
		});
		btnAddProduct.setBounds(449, 334, 136, 23);
		add(btnAddProduct);

		// Text field for quantity
		quanField = new JTextField();
		quanField.setColumns(10);
		quanField.setBounds(449, 274, 136, 20);
		add(quanField);

		// Label for quantity
		JLabel lblQuantity = new JLabel("Items available");
		lblQuantity.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblQuantity.setBounds(246, 273, 124, 21);
		add(lblQuantity);

		// Combo box for company
		company = new JComboBox<>();
		company.setBounds(449, 243, 136, 20);
		add(company);

		// Error label
		error = new JLabel("");
		error.setForeground(Color.RED);
		error.setBounds(339, 92, 265, 14);
		add(error);

		// Adding items to the company combo box
		company.addItem("General");
		company.addItem("Mats & Rugs");
		company.addItem("N/S & Electric");
	}
}