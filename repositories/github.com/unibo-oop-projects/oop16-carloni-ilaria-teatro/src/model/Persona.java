package model;

import java.io.Serializable;

/**
 * Classe che modella una persona
 * 
 * @author Ilaria Carloni
 * 
 */
public class Persona implements ILogin, Serializable {

	private static final long serialVersionUID = 1L;
	private String nome;
	private String cognome;
	private String username;
	private String password;
	private static Persona utenteLoggato = null;
	private static boolean clienteLoggato;

	/**
	 * Costruttore che crea una persona coi parametri presi in input
	 * 
	 * @param nome
	 * @param cognome
	 * @param username
	 * @param password
	 */
	public Persona(String nome, String cognome, String username, String password) {
		this.nome = nome;
		this.cognome = cognome;
		this.username = username;
		this.password = password;
	}

	/**
	 * Metodo che ritorna il nome di una persona
	 * 
	 * @return
	 */
	public String getNome() {
		return this.nome;
	}

	/**
	 * Metodo che ritorna il cognome di una persona
	 * 
	 * @return
	 */
	public String getCognome() {
		return this.cognome;
	}

	/**
	 * Metodo che ritorna lo username
	 */
	public String getUsername() {
		return this.username;
	}

	/**
	 * Metodo che ritorna la password
	 */
	public String getPassword() {
		return this.password;
	}

	/**
	 * Metodo che ritorna l'utente attualmente loggato
	 * 
	 * @return
	 */
	public static Persona getUtenteLoggato() {
		return utenteLoggato;
	}

	/**
	 * Metodo che permette di impostare l'utente attualmete loggato con quello
	 * preso in input
	 * 
	 * @param utenteLoggato
	 */
	public static void setUtenteLoggato(Persona utenteLoggato) {
		Persona.utenteLoggato = utenteLoggato;
	}

	/**
	 * Metodo che ritorna true se l'utente loggato è un cliente, false se è un
	 * operatore
	 * 
	 * @return
	 */
	public static boolean getClienteLoggato() {
		return clienteLoggato;
	}

	/**
	 * Metodo che permette di impostare l'utente attualmente loggato
	 * 
	 * @param clienteLoggato
	 */
	public static void setClienteLoggato(boolean clienteLoggato) {
		Persona.clienteLoggato = clienteLoggato;
	}

}
