package application;

import java.io.Serializable;

public class Utente implements Serializable{
	
	private int grado;
	private String username;
	private char[] password;
	
	
	
	public Utente(String username, char[] password) {
		
		this.grado = 3;
		this.username = username;
		this.password = password;
	}
	protected int getGrado() {
		return grado;
	}
	protected void setGrado(int grado) {
		this.grado = grado;
	}
	protected String getUsername() {
		return username;
	}
	protected void setUsername(String username) {
		this.username = username;
	}
	protected char[] getPassword() {
		return password;
	}
	protected void setPassword(char[] password) {
		this.password = password;
	}

}
