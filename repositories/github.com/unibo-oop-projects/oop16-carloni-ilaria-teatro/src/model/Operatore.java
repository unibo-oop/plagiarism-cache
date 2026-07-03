package model;

import java.io.Serializable;

/**
 * Classe che modella un operatore
 * 
 * @author Ilaria Carloni
 * 
 */
public class Operatore extends Persona implements Serializable {

	private static final long serialVersionUID = 1L;
	private int codiceOp;

	/**
	 * Costruttore che crea un operatore coi paramentri in input
	 * 
	 * @param pers
	 * @param codiceOp
	 */
	public Operatore(Persona pers, int codiceOp) {
		super(pers.getNome(), pers.getCognome(), pers.getUsername(), pers
				.getPassword());
		this.codiceOp = codiceOp;
	}

	/**
	 * Metodo che ritorna il codice univoco di un operatore
	 * 
	 * @return
	 */
	public int getCodiceOp() {
		return this.codiceOp;
	}

	/**
	 * Metodo che stampa in console i dati di un operatore
	 */
	public String toString() {
		return this.getNome() + " " + this.getCognome() + " "
				+ this.getUsername() + " " + this.getPassword() + " "
				+ this.getCodiceOp();
	}
}
