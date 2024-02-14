package store;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JMenu;
import javax.swing.JMenuItem;
import javax.swing.JMenuBar;

public class generateInvoice extends JFrame implements ActionListener{

	final JPanel contentPane;
	int jp; // Variable to track the currently displayed panel
	ArrayList<JPanel> panels=new ArrayList<>(); // List to hold different panels

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(() -> {
			try {
				generateInvoice frame = new generateInvoice(); // Create an instance of generateInvoice frame
				frame.setVisible(true); // Make the frame visible
			} catch (Exception e) {
				e.printStackTrace(); // Print stack trace if an exception occurs
			}
		});
	}



	/**
	 * Create the frame.
	 */
	public generateInvoice() {
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE); // Set default close operation
		setBounds(100, 100, 850,600); // Set frame bounds

		JMenuBar menuBar = new JMenuBar(); // Create a menu bar
		setJMenuBar(menuBar); // Set the menu bar for the frame

		JMenu mnNewMenu = new JMenu("File"); // Create a menu
		menuBar.add(mnNewMenu); // Add the menu to the menu bar

		JMenuItem mnItmGenerateInvoice = new JMenuItem("Generate Invoice"); // Create a menu item
		mnNewMenu.add(mnItmGenerateInvoice); // Add the menu item to the menu
		mnItmGenerateInvoice.addActionListener(this); // Add action listener to handle events

		JMenuItem mnItmSearch = new JMenuItem("Search Product"); // Create a menu item
		mnNewMenu.add(mnItmSearch); // Add the menu item to the menu
		mnItmSearch.addActionListener(this); // Add action listener to handle events

		JMenuItem mnItmLogout = new JMenuItem("Logout"); // Create a menu item
		mnNewMenu.add(mnItmLogout); // Add the menu item to the menu
		mnItmLogout.addActionListener(this); // Add action listener to handle events

		contentPane = new JPanel(); // Create a content pane
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5)); // Set border for the content pane
		setContentPane(contentPane); // Set the content pane for the frame
		contentPane.setLayout(new BorderLayout(0, 0)); // Set layout for the content pane


		// Set Frame to Open in the Center of the Screen at Runtime
		Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
		int centerX = (int) ((screenSize.getWidth() - getWidth()) / 2);
		int centerY = (int) ((screenSize.getHeight() - getHeight()) / 2);
		setLocation(centerX, centerY);


		// Add different panels to the list
		panels.add(new Invoice()); // Invoice panel
		panels.add(new searchProduct()); // Search product panel
		getContentPane().add(panels.get(0)); // Add the first panel to the content pane
		jp=0; // Initialize the current panel index
		this.setTitle("Generate Invoice"); // Set the title of the frame
	}



	@Override
	public void actionPerformed(ActionEvent e) {
		// Handle action events
		if(e.getActionCommand().equals("Search Product")) // If "Search Product" menu item is clicked
		{
			this.remove(panels.get(jp)); // Remove the current panel
			this.revalidate(); // Revalidate the frame
			this.repaint(); // Repaint the frame
			getContentPane().add(panels.get(1)); // Add the search product panel
			jp=1; // Update the current panel index
			this.setVisible(true); // Make the frame visible
			this.setTitle("Search Product"); // Set the title of the frame
		}
		else if(e.getActionCommand().equals("Generate Invoice")) // If "Generate Invoice" menu item is clicked
		{
			this.remove(panels.get(jp)); // Remove the current panel
			this.revalidate(); // Revalidate the frame
			this.repaint(); // Repaint the frame

			getContentPane().add(panels.get(0));
			this.setVisible(true); // Make the frame visible
			jp=0; // Update the current panel index
			this.setTitle("Generate Invoice"); // Set the title of the frame
		}

		else if(e.getActionCommand().equals("Logout")) // If "Logout" menu item is clicked
		{
			this.dispose(); // Close the frame
		}
	}
}