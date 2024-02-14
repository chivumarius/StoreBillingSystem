package store;

// Imports
import javax.swing.*;
import java.awt.*;

// Class definition
public class searchProduct extends JPanel {

	// Components
	JTextField idField;
	JButton btnSearch;
	private JLabel error;

	// Fields
	String id, err = "Enter product id!";

	/**
	 * Create the panel.
	 */
	public searchProduct() {
		// Set layout and bounds
		setLayout(null);
		setBounds(100, 100, 840, 619);

		// Title label
		JLabel lblSearchProduct = new JLabel("Search Product");
		lblSearchProduct.setBounds(319, 84, 182, 21);
		lblSearchProduct.setFont(new Font("Tahoma", Font.PLAIN, 17));
		add(lblSearchProduct);

		// Label for product ID
		JLabel lblProductName = new JLabel("Product ID");
		lblProductName.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblProductName.setBounds(253, 156, 124, 21);
		add(lblProductName);

		// Text field for product ID
		idField = new JTextField();
		idField.setBounds(449, 158, 136, 20);
		add(idField);
		idField.setColumns(10);

		// Button to search for product
		btnSearch = new JButton("Search");
		btnSearch.addActionListener(e ->  {
			// Action performed when the button is clicked
			if(idField.getText().equals("")) {
				error.setText(err);
			} else {
				error.setText("");
				id = idField.getText().trim();
				DBConnection.searchProduct(id);
				idField.setText("");
			}
		});
		btnSearch.setBounds(449, 219, 136, 23);
		add(btnSearch);

		// Error label
		error = new JLabel("");
		error.setForeground(Color.RED);
		error.setBounds(349, 277, 217, 14);
		add(error);

	}

}