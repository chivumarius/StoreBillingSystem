package store;

import javax.swing.*;
import java.sql.*;
import java.util.ArrayList;

public class DBConnection {

	// Method to establish database connection
	public static Connection getConnection() {
		Connection conn = null;
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/store_billing_system", "root", "");
			System.out.print("Database is connected !");
		} catch (Exception e) {
			JOptionPane.showMessageDialog(null, "Do not connect to DBConnection - Error:" + e);
		}
		return conn;
	}

	// Method to add a product to the database
	public static void addProductToDB(String id, String detail, String comp, int quan) {
		Connection conn = getConnection();
		try {
			Statement statement = conn.createStatement();
			statement.executeUpdate("INSERT INTO stock VALUES ('"+id+"','"+detail+"','"+comp+"',"+quan+");");
			JOptionPane.showMessageDialog(null, "Product added to database");
			conn.close();
		} catch (SQLException e) {
			JOptionPane.showMessageDialog(null, e.getMessage());
			e.printStackTrace();
		}
	}

	// Method to update a product in the database
	public static void updateProductToDB(String id, String detail, String comp, int quan) {
		Connection conn = getConnection();
		try {
			Statement statement = conn.createStatement();
			int status = statement.executeUpdate("UPDATE stock set Detail = '" + detail + "', Company = '"+ comp +"', Quantity = "+quan+" WHERE ProductID = '"+id+"';");
			if(status == 1)
				JOptionPane.showMessageDialog(null,  "Product updated");
			else
				JOptionPane.showMessageDialog(null,  "ProductID not found!");
			conn.close();
		} catch (SQLException e) {
			JOptionPane.showMessageDialog(null, e.getMessage());
			e.printStackTrace();
		}
	}

	// Method to delete a product from the database
	public static void deleteProductToDB(String id) {
		Connection conn = getConnection();
		try {
			Statement statement = conn.createStatement();
			int status = statement.executeUpdate("DELETE from stock WHERE ProductID = '"+id+"';");
			if(status == 1)
				JOptionPane.showMessageDialog(null,  "Product deleted");
			else
				JOptionPane.showMessageDialog(null,  "ProductID not found!");
			conn.close();
		} catch (SQLException e) {
			JOptionPane.showMessageDialog(null, e.getMessage());
			e.printStackTrace();
		}
	}

	// Method to search for a product in the database
	public static void searchProduct(String id) {
		Connection conn = getConnection();
		try {
			Statement statement = conn.createStatement();
			ResultSet rs = statement.executeQuery("Select * from stock WHERE ProductID = '"+id+"';");
			if (!rs.next())
				JOptionPane.showMessageDialog(null,"No product found with this id!");
			else
				JOptionPane.showMessageDialog(null, "ProductID: "+id+"\nQuantity: "+rs.getString("Quantity"));
			conn.close();
		} catch (SQLException e) {
			JOptionPane.showMessageDialog(null, e.getMessage());
			e.printStackTrace();
		}
	}

	// Method to search for a cashier in the database
	public static void searchCashier(String email) {
		Connection conn = getConnection();
		try {
			Statement statement = conn.createStatement();
			ResultSet rs = statement.executeQuery("Select * from users WHERE Email = '"+email+"';");
			if (!rs.next())
				JOptionPane.showMessageDialog(null,"No cashier found with this email!");
			else
				JOptionPane.showMessageDialog(null, "Email: "+email+"\nPassword: "+rs.getString("Password"));
			conn.close();
		} catch (SQLException e) {
			JOptionPane.showMessageDialog(null, e.getMessage());
			e.printStackTrace();
		}
	}

	// Method to verify login credentials
	public static boolean verifyLogin(String email, String pass) {
		boolean login = false;
		Connection conn = getConnection();
		try {
			Statement statement = conn.createStatement();
			ResultSet rs = statement.executeQuery("Select * from users WHERE Email = '"+email+"' and Password = '"+pass+"';");

			login = rs.next();
			conn.close();
		} catch (SQLException e) {
			JOptionPane.showMessageDialog(null, e.getMessage());
			e.printStackTrace();
		}
		return login;
	}


	// Method to add a cashier to the database
	public static void addCashier(String user, String pass) {
		Connection conn = getConnection();
		try {
			Statement statement = conn.createStatement();
			statement.executeUpdate("INSERT INTO users VALUES ('"+user+"','"+pass+"');");
			JOptionPane.showMessageDialog(null, "Cashier added to database");
			conn.close();
		} catch (SQLException e) {
			JOptionPane.showMessageDialog(null, e.getMessage());
			e.printStackTrace();
		}
	}

	// Method to delete a cashier from the database
	public static void deleteCashier(String user, String pass) {
		Connection conn = getConnection();
		try {
			Statement statement = conn.createStatement();
			int status = statement.executeUpdate("DELETE from users WHERE Email = '"+user+"' AND Password = '"+pass+"';");
			if(status == 1)
				JOptionPane.showMessageDialog(null,  "Cashier deleted");
			else
				JOptionPane.showMessageDialog(null,  "Cashier not found!");
			conn.close();
		} catch (SQLException e) {
			JOptionPane.showMessageDialog(null, e.getMessage());
			e.printStackTrace();
		}
	}









	// Method to retrieve sales data from the database
	public static ArrayList<String> getSale(String date, String comp) {
		String q;
		ArrayList<String> r = new ArrayList<>();

		if (comp.equals("All"))
			q = "Select * from sale WHERE Date = '" + date + "';";
		else
			q = "Select * from sale WHERE Date = '" + date + "' AND Company = '" + comp + "';";

		Connection conn = getConnection();
		try {
			Statement statement = conn.createStatement();
			ResultSet rs = statement.executeQuery(q);
			while (rs.next()) {
				r.add(rs.getString("Date"));
				r.add(rs.getString("ProductID"));
				r.add(rs.getString("Company"));
				r.add(rs.getString("Payment"));
			}
			conn.close();
		} catch (SQLException e) {
			JOptionPane.showMessageDialog(null, e.getMessage());
			e.printStackTrace();
		}
		return r;
	}


	// Method to retrieve stock data from the database
	public static ArrayList<String> showStock(String comp) {
		String q;
		ArrayList<String> r = new ArrayList<>();
		if (comp.equals("All"))
			q = "Select * from stock;";
		else
			q = "Select * from stock WHERE Company = '" + comp + "';";
		Connection conn = getConnection();
		try {
			Statement statement = conn.createStatement();
			ResultSet rs = statement.executeQuery(q);
			while (rs.next()) {
				r.add(rs.getString("ProductID"));
				r.add(rs.getString("Detail"));
				r.add(rs.getString("Company"));
				r.add(rs.getString("Quantity"));
			}
			conn.close();
		} catch (SQLException e) {
			JOptionPane.showMessageDialog(null, e.getMessage());
			e.printStackTrace();
		}
		return r;
	}


	// Method to search for product details
	public static ArrayList<String> searchPDetail(String id, int q) {
		Connection conn = getConnection();
		ArrayList<String> rt = new ArrayList<>();
		try {
			int quan;
			Statement statement = conn.createStatement();
			ResultSet rs = statement.executeQuery("Select * from stock WHERE ProductID = '" + id + "';");
			if (!rs.next()) {
				rt.add("null");
			} else {
				quan = Integer.parseInt(rs.getString("Quantity")) - q;
				if (quan < 0)
					rt.add("item is out of stock");
				else {
					rt.add(rs.getString("detail")); // Here we corrected the column name to "detail"
					rt.add(rs.getString("Company"));
					statement.executeUpdate("UPDATE stock set Quantity = " + quan + " WHERE ProductID = '" + id + "';");
				}
			}
			conn.close();
		} catch (SQLException e) {
			JOptionPane.showMessageDialog(null, e.getMessage());
			e.printStackTrace();
		}
		return rt;
	}

	// Method to search for a product in the database
	public static ArrayList<String> searchP(String id) {
		Connection conn = getConnection();
		ArrayList<String> data = new ArrayList<>();
		try {
			Statement statement = conn.createStatement();
			ResultSet rs = statement.executeQuery("Select * from stock WHERE ProductID = '" + id + "';");
			if (rs.next()) {
				data.add(rs.getString("Detail"));
				data.add(rs.getString("Company"));
				data.add(rs.getString("Quantity"));
			}
			conn.close();
		} catch (SQLException e) {
			JOptionPane.showMessageDialog(null, e.getMessage());
			e.printStackTrace();
		}
		return data;
	}







	// Method to update product quantity in the database
	public static void updateProduct(String id,int quan)
	{
		Connection conn= getConnection();
		try {
			Statement statement = conn.createStatement();
			ResultSet rs = statement.executeQuery("Select * from stock WHERE ProductID = '"+id+"';");
			int q;
			if(rs.next())
			{
				q=Integer.parseInt(rs.getString("Quantity"))+quan;
				statement.executeUpdate("UPDATE stock set Quantity = "+q+" WHERE ProductID = '"+id+"';");
			
			}
			conn.close();
		} catch (SQLException e) {
			 
			JOptionPane.showMessageDialog(null, e.getMessage());
			e.printStackTrace();
		}
		
	}


	public static void main(String[] args) {
		// Test the DBConnection method to establish a connection
		Connection conn = DBConnection.getConnection();
		if (conn != null) {
			System.out.println("Database connection successful!");
		} else {
			System.out.println("Failed to connect to the database.");
		}
	}
}
