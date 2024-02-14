package store;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.JFrame;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JMenu;

// AdminPanel class extends JFrame and implements ActionListener interface
public class AdminPanel extends JFrame implements ActionListener {
	JMenuItem itmAddProduct;
	JMenu mnProduct;
	JMenuItem itmUpdateProduct;
	JMenuItem itmDeleteProduct;
	JMenu mnCashier;
	JMenuItem itmDeleteCashier;
	JMenuItem itmAddCashier;
	JMenu mnStock;
	JMenuItem itmShowStock;
	JMenu mnExport;
	ArrayList<JPanel> panels = new ArrayList<>();
	int cPanel = 0;
	JMenu mnSearch;
	JMenuItem mnItmSearchProduct;
	JMenuItem mnItmSearchCashier;
	JMenu mnSale;
	JMenuItem mnItmPrintSale;



	/**
	 * Create the frame.
	 */
	public AdminPanel() {

		// Setting up frame properties
		setTitle("Admin Panel");
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 840, 619);

		// Creating menu bar and adding menus to it
		JMenuBar menuBar = new JMenuBar();
		setJMenuBar(menuBar);

		mnProduct = new JMenu("Product");
		menuBar.add(mnProduct);

		// Adding menu items to "Product" menu and attaching action listener
		itmAddProduct = new JMenuItem("Add Product");
		mnProduct.add(itmAddProduct);
		itmAddProduct.addActionListener(this);

		itmUpdateProduct = new JMenuItem("Update Product");
		mnProduct.add(itmUpdateProduct);
		itmUpdateProduct.addActionListener(this);

		itmDeleteProduct = new JMenuItem("Delete Product");
		mnProduct.add(itmDeleteProduct);
		itmDeleteProduct.addActionListener(this);

		mnCashier = new JMenu("Cashier");
		menuBar.add(mnCashier);

		// Adding menu items to "Cashier" menu and attaching action listener
		itmAddCashier = new JMenuItem("Add Cashier");
		mnCashier.add(itmAddCashier);
		itmAddCashier.addActionListener(this);

		itmDeleteCashier = new JMenuItem("Delete Cashier");
		mnCashier.add(itmDeleteCashier);
		itmDeleteCashier.addActionListener(this);

		mnStock = new JMenu("Stock");
		menuBar.add(mnStock);

		// Adding menu items to "Stock" menu and attaching action listener
		itmShowStock = new JMenuItem("Show Stock");
		mnStock.add(itmShowStock);
		itmShowStock.addActionListener(this);

		mnSearch = new JMenu("Search");
		menuBar.add(mnSearch);

		// Adding menu items to "Search" menu and attaching action listener
		mnItmSearchProduct = new JMenuItem("Search Product");
		mnSearch.add(mnItmSearchProduct);
		mnItmSearchProduct.addActionListener(this);

		mnItmSearchCashier = new JMenuItem("Search Cashier");
		mnSearch.add(mnItmSearchCashier);

		mnSale = new JMenu("Sale");
		menuBar.add(mnSale);

		// Adding menu items to "Sale" menu and attaching action listener
		mnItmPrintSale = new JMenuItem("View Sale");
		mnSale.add(mnItmPrintSale);
		mnItmPrintSale.addActionListener(this);

		mnExport = new JMenu("Account");
		menuBar.add(mnExport);

		// Adding "Logout" menu item to "Account" menu and attaching action listener
		JMenuItem logout = new JMenuItem("Logout");
		mnExport.add(logout);
		logout.addActionListener(this);
		mnItmSearchCashier.addActionListener(this);

		getContentPane().setLayout(new BorderLayout(0, 0));

		// Adding panels for different functionalities
		panels.add(new addProduct());
		panels.add(new updateProduct());
		panels.add(new deleteProduct());
		panels.add(new addCashier());
		panels.add(new deleteCashier());
		panels.add(new showStock());
		panels.add(new searchProduct());
		panels.add(new searchCashier());
		panels.add(new Sale());
		getContentPane().add(panels.get(0));

		// Set Frame to Open in the Center of the Screen at Runtime
		setLocationRelativeTo(null);
	}



	// Action listener method
	@Override
	public void actionPerformed(ActionEvent e) {

		// Printing out the selected action command
		System.out.println("Selected: " + e.getActionCommand());
		if (e.getActionCommand().equals("Add Product")) {
			this.remove(panels.get(cPanel));
			this.revalidate();
			this.repaint();
			getContentPane().add(panels.get(0));
			this.setVisible(true);
			cPanel = 0;
			this.setTitle("Add Product");
		} else if (e.getActionCommand().equals("Update Product")) {
			this.remove(panels.get(cPanel));
			this.revalidate();
			this.repaint();
			getContentPane().add(panels.get(1));
			this.setVisible(true);
			cPanel = 1;
			this.setTitle("Update Product");
		} else if (e.getActionCommand().equals("Delete Product")) {
			this.remove(panels.get(cPanel));
			this.revalidate();
			this.repaint();
			getContentPane().add(panels.get(2));
			this.setVisible(true);
			cPanel = 2;
			this.setTitle("Delete Product");
		} else if (e.getActionCommand().equals("Add Cashier")) {
			this.remove(panels.get(cPanel));
			this.revalidate();
			this.repaint();
			getContentPane().add(panels.get(3));
			this.setVisible(true);
			cPanel = 3;
			this.setTitle("Add Cashier");
		} else if (e.getActionCommand().equals("Delete Cashier")) {
			this.remove(panels.get(cPanel));
			this.revalidate();
			this.repaint();
			getContentPane().add(panels.get(4));
			this.setVisible(true);
			cPanel = 4;
			this.setTitle("Delete Cashier");
		} else if (e.getActionCommand().equals("Show Stock")) {
			this.remove(panels.get(cPanel));
			getContentPane().add(panels.get(5));
			this.revalidate();
			this.repaint();
			this.setVisible(true);
			cPanel = 5;
			this.setTitle("Show Stock");
		} else if (e.getActionCommand().equals("Search Product")) {
			this.remove(panels.get(cPanel));
			getContentPane().add(panels.get(6));
			this.revalidate();
			this.repaint();
			this.setVisible(true);
			cPanel = 6;
			this.setTitle("Search Product");
		} else if (e.getActionCommand().equals("Search Cashier")) {
			this.remove(panels.get(cPanel));
			getContentPane().add(panels.get(7));
			this.revalidate();
			this.repaint();
			this.setVisible(true);
			cPanel = 7;
			this.setTitle("Search Cashier");
		} else if (e.getActionCommand().equals("View Sale")) {
			this.remove(panels.get(cPanel));
			getContentPane().add(panels.get(8));
			this.revalidate();
			this.repaint();
			this.setVisible(true);
			cPanel = 8;
			this.setTitle("View Sale");
		} else if (e.getActionCommand().equals("Logout")) {
			this.dispose();
		}
	}
}