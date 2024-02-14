package store;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;

/**
 * The Login class represents a JFrame for user login.
 */
public class Login extends JFrame {

	// Panel to hold the content
	JPanel contentPane;
	// Text field for username input
	private JTextField usernameField;
	// Text field for password input
	private JPasswordField passwordField;
	// Strings to store the entered username and password
	private String password, username;
	// Label to display error messages
	private JLabel error;
	// Constant error message text
	private static final String errorText = "Invalid user name or password!";
	// Label for the login form
	JLabel lbl;
	// Button to trigger login action
	private JButton btnLogin;



	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(() -> {
			try {
				// Create and display the login frame
				Login frame = new Login();
				frame.setVisible(true);
			} catch (Exception e) {
				e.printStackTrace();
			}
		});
	}



	/**
	 * Create the frame.
	 */
	public Login() {
		// Initialize the GUI components
		GUI();
	}

	/**
	 * Sets up the graphical user interface.
	 */
	private void GUI() {
		// Frame setup
		setTitle("Login");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 531, 387);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);


		// Set Frame to Open in the Center of the Screen at Runtime
		Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
		int centerX = (int) ((screenSize.getWidth() - getWidth()) / 2);
		int centerY = (int) ((screenSize.getHeight() - getHeight()) / 2);
		setLocation(centerX, centerY);


		// Label for username field
		JLabel lblUserName = new JLabel("User name");
		lblUserName.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblUserName.setBounds(154, 141, 91, 14);
		contentPane.add(lblUserName);

		// Text field for username input
		usernameField = new JTextField();
		usernameField.setBounds(282, 140, 129, 20);
		contentPane.add(usernameField);
		usernameField.setColumns(10);

		// Label for password field
		JLabel lblPassword = new JLabel("Password\r\n");
		lblPassword.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblPassword.setBounds(154, 174, 91, 14);
		contentPane.add(lblPassword);

		// Text field for password input
		passwordField = new JPasswordField();
		passwordField.setBounds(282, 173, 129, 20);
		contentPane.add(passwordField);

		// Trigger login action when Enter key is pressed in password field
		passwordField.addActionListener(e -> btnLogin.doClick());

		// Button for login
		btnLogin = new JButton("Login");

		// Action listener for login button
		btnLogin.addActionListener(e -> {
			// Get the entered username and password
			password = new String(passwordField.getPassword()).toLowerCase();
			username = usernameField.getText().toLowerCase();
			// Clear input fields
			passwordField.setText("");
			usernameField.setText("");
			// Check for empty inputs
			if (password.equals("") || username.equals("")) {
				error.setText(errorText);
			} else {
				error.setText("");
				// Check if the user is admin
				if (username.equals("admin")) {
					if (DBConnection.verifyLogin(username, password)) {
						error.setText("");
						// Open admin panel on successful login
						AdminPanel p = new AdminPanel();
						p.setVisible(true);
					} else {
						error.setText(errorText);
					}
				} else {
					// Open user panel on successful login
					if (DBConnection.verifyLogin(username, password)) {
						error.setText("");
						generateInvoice g = new generateInvoice();
						g.setVisible(true);
					} else {
						error.setText(errorText);
					}
				}
			}
		});



		// Button position
		btnLogin.setBounds(282, 227, 89, 23);
		contentPane.add(btnLogin);



		// Label to display error messages
		error = new JLabel("");
		error.setForeground(Color.RED);
		error.setBounds(104, 236, 220, 14);
		contentPane.add(error);


		// Label for the login form
		lbl = new JLabel("Store Login");
		lbl.setFont(new Font("Tahoma", Font.PLAIN, 17));
		lbl.setBounds(204, 26, 167, 28);
		contentPane.add(lbl);
	}
}