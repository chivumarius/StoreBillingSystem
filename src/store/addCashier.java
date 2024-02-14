package store;

import java.awt.Font;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.JPasswordField;
import java.awt.Color;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;



/**
 * Panel for adding a new cashier to the system.
 */
public class addCashier extends JPanel {

	// Components for user input and interaction
	JTextField userField; // Text field for entering the username
	JButton btnAddCashier; // Button to add a new cashier
	private final JPasswordField passwordField; // Field for entering the password

	// Component for displaying error messages
	private final JLabel error; // Label for displaying error messages

	// Error message for empty username or password
	String err = "Enter username and password";

	// Variables to store the username and password
	String user, pass;

	/**
	 * Create the panel.
	 */
	public addCashier() {
		setLayout(null); // Set layout to null for custom positioning
		setBounds(100, 100, 840, 619); // Set the bounds of the panel

		// Label for the title
		JLabel lblAddCashier = new JLabel("Add Cashier");
		lblAddCashier.setBounds(328, 45, 182, 21);
		lblAddCashier.setFont(new Font("Tahoma", Font.PLAIN, 17));
		add(lblAddCashier);

		// Label for the username field
		JLabel lblUserName = new JLabel("User name");
		lblUserName.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblUserName.setBounds(246, 104, 124, 21);
		add(lblUserName);

		// Label for the password field
		JLabel lblPassword = new JLabel("Password");
		lblPassword.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblPassword.setBounds(246, 136, 124, 21);
		add(lblPassword);

		// Text field for entering the username
		userField = new JTextField();
		userField.setBounds(436, 106, 147, 20);
		add(userField);
		userField.setColumns(10);

		// Button to add a new cashier
		btnAddCashier = new JButton("Add Cashier");
		btnAddCashier.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				user = userField.getText().trim();
				pass = String.valueOf(passwordField.getPassword()).trim().toLowerCase();

				// Check if username or password is empty
				if (user.equals("") || pass.equals(""))
					error.setText(err);
				else {
					error.setText("");
					DBConnection.addCashier(user, pass);
					userField.setText("");
					passwordField.setText("");
				}
			}
		});
		btnAddCashier.setBounds(436, 194, 147, 23);
		add(btnAddCashier);

		// Field for entering the password
		passwordField = new JPasswordField();
		passwordField.setBounds(436, 138, 147, 19);
		add(passwordField);

		// Label for displaying error messages
		error = new JLabel("");
		error.setForeground(Color.RED);
		error.setBounds(328, 241, 201, 14);
		add(error);
	}
}