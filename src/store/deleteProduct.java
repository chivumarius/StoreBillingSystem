package store;

// Imports
import javax.swing.*;
import java.awt.*;



// Class definition
public class deleteProduct extends JPanel {

	// Components
	JTextField idField;
	JButton btnDeleteProduct;
	private JLabel error;

	// Fields
	String id, err = "Enter product id!";

	/**
	 * Create the panel.
	 */
	public deleteProduct() {
		// Set layout and bounds
		setLayout(null);
		setBounds(100, 100, 840, 619);

		// Title label
		JLabel lblUpdateProduct = new JLabel("Delete Product");
		lblUpdateProduct.setBounds(319, 84, 182, 21);
		lblUpdateProduct.setFont(new Font("Tahoma", Font.PLAIN, 17));
		add(lblUpdateProduct);

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

		// Button to delete product
		btnDeleteProduct = new JButton("Delete Product");
		btnDeleteProduct.addActionListener(e -> {
			// Action performed when the button is clicked
			if(idField.getText().equals("")) {
				error.setText(err);
			} else {
				error.setText("");
				id = idField.getText().trim();
				DBConnection.deleteProductToDB(id);
				idField.setText("");
			}
		});
		btnDeleteProduct.setBounds(449, 219, 136, 23);
		add(btnDeleteProduct);

		// Error label
		error = new JLabel("");
		error.setForeground(Color.RED);
		error.setBounds(349, 277, 217, 14);
		add(error);
	}
}