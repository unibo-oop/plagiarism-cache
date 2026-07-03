package model;

import java.io.Serializable;

/**
 * Classe che modella un cliente
 * 
 * @author Ilaria Carloni
 * 
 */
public class Cliente extends Persona implements Serializable {

	private static final long serialVersionUID = 1L;
	private int codice;

	/**
	 * Costruttore che crea un cliente coi paramentri in input
	 * 
	 * @param pers
	 * @param codice
	 */
	public Cliente(Persona pers, int codice) {
		super(pers.getNome(), pers.getCognome(), pers.getUsername(), pers
				.getPassword());
		this.codice = codice;
	}

	/**
	 * Metodo che ritorna il codice univoco di un cliente
	 * 
	 * @return
	 */
	public int getCodice() {
		return this.codice;
	}

	/**
	 * Metodo che stampa in console i dati di un cliente
	 */
	public String toString() {
		return this.getNome() + " " + this.getCognome() + " "
				+ this.getUsername() + " " + this.getPassword() + " "
				+ this.getCodice();
	}
}
