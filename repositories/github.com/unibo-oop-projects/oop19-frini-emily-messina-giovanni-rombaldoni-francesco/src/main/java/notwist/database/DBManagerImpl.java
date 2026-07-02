package notwist.database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

import javax.swing.JOptionPane;

import rombo.crypt.CrypterImplementation;

abstract class DBManagerImpl extends CrypterImplementation {

	private Connection conn;
	private Statement stmt;

	public DBManagerImpl() {
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			open();
		} catch (ClassNotFoundException e) {
			e.getStackTrace();
		}

	}

	public Statement open() {
		try {
			conn = DriverManager.getConnection("jdbc:mysql://remotemysql.com:3306/7lncuOhnfh", "7lncuOhnfh",
					"DLmbbVHESb");
			stmt = conn.createStatement();
			System.out.print("Database is connected !");
			return stmt;
		} catch (SQLException e) {
			System.out.println("Error while connect with database" + e);
			JOptionPane.showMessageDialog(null, "Errore di connessione al database, controlla la connessione! ");
			
		}
		return null;
	}

	public void close() {
		try {
			conn.close();
			System.out.println("Connection closed safely");
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public Connection getConn() {
		return conn;
	}

}
