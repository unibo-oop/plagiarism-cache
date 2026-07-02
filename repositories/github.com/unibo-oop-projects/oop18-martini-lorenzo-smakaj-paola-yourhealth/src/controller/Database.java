package controller;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;

public class Database {

	public static Connection getConnection() throws Exception {
		try {
			String url = "jdbc:mysql://127.0.0.1:3306/javaproject?serverTimezone=UTC";
			String username = "username";
			String password = "password";

			Connection conn = DriverManager.getConnection(url, username, password);
			return conn;

		} catch (Exception e) {
			System.out.println(e);
		}
		return null;
	}

	public static void createTablePazienti() throws Exception {
		try {
			Connection conn = getConnection();
			PreparedStatement create = conn.prepareStatement(
					"CREATE TABLE IF NOT EXISTS `pazienti` (`id` INT UNSIGNED NOT NULL AUTO_INCREMENT,`Nome` VARCHAR(255)"
							+ " NULL,`Cognome` VARCHAR(255) NULL,`Sesso` VARCHAR(255) NULL,`LuogoNascita` VARCHAR(255)"
							+ " NULL,`DataNascita` VARCHAR(255) NULL,`Codicefiscale` VARCHAR(255) NULL,"
							+ "`Residenza` VARCHAR(255) NULL,PRIMARY KEY (`id`));");

			create.executeUpdate();
			System.out.println("Table Pazienti Created");
			
			create.close();
			conn.close();
		} catch (Exception e) {
			System.out.println(e);
		}
	}

	public static void createTableDottori() throws Exception {
		try {
			Connection conn = getConnection();
			PreparedStatement create = conn.prepareStatement(
					"CREATE TABLE IF NOT EXISTS `dottori` (`id` INT UNSIGNED NOT NULL AUTO_INCREMENT,"
					+ "`Nome` VARCHAR(255) NULL,`Cognome` VARCHAR(255) NULL,`Sesso` VARCHAR(255) NULL,`LuogoNascita` VARCHAR(255)"
					+ " NULL,`DataNascita` VARCHAR(255) NULL,`Tesserino` INT(255) NULL,"
					+ "`Ruolo` VARCHAR(255) NULL,`OrarioInizio` VARCHAR(255) NULL,`OrarioFine` VARCHAR(255) NULL,PRIMARY KEY (`id`));");
			create.executeUpdate();
			System.out.println("Table Dottori Created");
			create.close();
			conn.close();
		} catch (Exception e) {
			System.out.println(e);
		}
	}

	public static void createTableMacchinari() throws Exception {
		try {
			Connection conn = getConnection();
			PreparedStatement create = conn.prepareStatement(
					"CREATE TABLE IF NOT EXISTS `macchinari` (id INT(6) UNSIGNED NOT NULL AUTO_INCREMENT,"
							+ "Codice VARCHAR(255) NULL,Tipo VARCHAR(255) NULL,PRIMARY KEY (`id`))");

			create.executeUpdate();
			System.out.println("Table Macchinari Created");
			create.close();
			conn.close();
		} catch (Exception e) {
			System.out.println(e);
		}
	}

	public static void createTableAmbulatori() throws Exception {
		try {
			Connection conn = getConnection();
			PreparedStatement create = conn.prepareStatement(
					"CREATE TABLE IF NOT EXISTS `ambulatori` (id INT(6) UNSIGNED NOT NULL AUTO_INCREMENT,"
							+ "Codice VARCHAR(255) NULL,Tipo VARCHAR(255) NULL,PRIMARY KEY (`id`))");

			create.executeUpdate();
			create.close();
			conn.close();
			System.out.println("Table Ambulatori Created");
		} catch (Exception e) {
			System.out.println(e);
		}
	}

	public static void createTablePrestazioni() throws Exception {
		try {
			Connection conn = getConnection();
			PreparedStatement create = conn.prepareStatement(
					"CREATE TABLE IF NOT EXISTS `prestazioni` (id INT(6) UNSIGNED NOT NULL AUTO_INCREMENT,"
							+ "CF_Paziente VARCHAR(255) NULL, ID_Dottore INT(255) NULL, Tipo VARCHAR(255) NULL, Data VARCHAR(255) NULL, "
							+ "Ora VARCHAR(255) NULL, Stato VARCHAR(255) NULL, Macchinario VARCHAR(255) NULL, "
							+ "Ambulatorio VARCHAR(255) NULL, PRIMARY KEY (`id`))");

			create.executeUpdate();
			System.out.println("Table Prestazioni Created");
			create.close();
			conn.close();
		} catch (Exception e) {
			System.out.println(e);
		}
	}
}