package store;

import javax.swing.*;
import java.awt.*;

public class searchCashier extends JPanel {

	// Components
	JTextField idField;
	JButton btnUpdateProduct;
	private JLabel error;

	// Fields
	String id, err = "Enter product id!";

	/**
	 * Create the panel.
	 */
	public searchCashier() {
		setLayout(null);
		setBounds(100, 100, 840, 619);

		// Title label
		JLabel lblSearch = new JLabel("Search Cashier");
		lblSearch.setBounds(319, 84, 182, 21);
		lblSearch.setFont(new Font("Tahoma", Font.PLAIN, 17));
		add(lblSearch);

		// Label for user name
		JLabel lblUser = new JLabel("User name");
		lblUser.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblUser.setBounds(253, 156, 124, 21);
		add(lblUser);


		// Text field for id field
		idField = new JTextField();
		idField.setBounds(449, 158, 136, 20);
		add(idField);
		idField.setColumns(10);

		// Button to search
		btnUpdateProduct = new JButton("Search");
		btnUpdateProduct.addActionListener(e -> {
			// Action performed when the button is clicked
			if(idField.getText().equals("")) {
				error.setText(err);
			} else {
				error.setText("");
				id = idField.getText().trim();
				DBConnection.searchCashier(id);
				idField.setText("");
			}
		});
		btnUpdateProduct.setBounds(449, 219, 136, 23);
		add(btnUpdateProduct);

		// Error label
		error = new JLabel("");
		error.setForeground(Color.RED);
		error.setBounds(349, 277, 217, 14);
		add(error);
	}
}