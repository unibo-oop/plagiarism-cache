package model;

import java.io.Serializable;
import java.util.Date;

import exception.ExceptionNumNegativo;
import exception.ExceptionPostiNonDisponibili;

/**
 * Classe che modella uno spettacolo
 * 
 * @author Ilaria Carloni
 * 
 */
public class Spettacolo implements Serializable {

	private static final long serialVersionUID = 1L;
	private String nomeSp;
	private Date data;
	private float prezzo;
	private String compagnia;
	private int codice = 0;
	private int postiVenduti;
	public static final int MAX_POSTI = 100;

	/**
	 * Costruttore che crea uno spettacolo coi parametri in input
	 * 
	 * @param nomeSp
	 * @param data
	 * @param prezzo
	 * @param compagnia
	 */
	public Spettacolo(String nomeSp, Date data, float prezzo, String compagnia) {
		this.nomeSp = nomeSp;
		this.data = data;
		this.prezzo = prezzo;
		this.compagnia = compagnia;
	}

	/**
	 * Metodo che ritorna il nome di uno spettacolo
	 * 
	 * @return
	 */
	public String getNomeSp() {
		return this.nomeSp;
	}

	/**
	 * Metodo che ritorna la data di uno spettacolo
	 * 
	 * @return
	 */
	public Date getData() {
		return this.data;
	}

	/**
	 * Metodo che ritorna il prezzo di uno spettacolo
	 */
	public float getPrezzo() {
		return this.prezzo;
	}

	/**
	 * Metodo che ritorna la compagnia di attori che recita nello spettacolo
	 * 
	 * @return
	 */
	public String getCompagnia() {
		return this.compagnia;
	}

	/**
	 * Metodo che ritorna il codice univoco di uno spettacolo
	 * 
	 * @return
	 */
	public int getCodice() {
		return this.codice;
	}

	/**
	 * Metodo che ritorna i posti venduti
	 * 
	 * @return
	 */
	public int getPostiVenduti() {
		return this.postiVenduti;
	}

	/**
	 * Metodo che permette di impostare il codice univoco di uno spettacolo
	 * 
	 * @param cod
	 */
	public void setCodice(int cod) {
		this.codice = cod;
	}

	/**
	 * Metodo che permette di gestire il numero di posti prenotabili
	 * 
	 * @param numPosti
	 * @throws ExceptionPostiNonDisponibili
	 * @throws ExceptionNumNegativo
	 */
	public void aggiungiPosto(int numPosti)
			throws ExceptionPostiNonDisponibili, ExceptionNumNegativo {
		if (this.postiVenduti + numPosti > MAX_POSTI) {
			throw new ExceptionPostiNonDisponibili();
		} else if (numPosti < 0) {
			throw new ExceptionNumNegativo();
		}
		this.postiVenduti += numPosti;
	}

	/**
	 * Metodo che stampa uno spettacolo in console
	 */
	public String toString() {
		return this.getNomeSp() + " " + this.getData() + " " + this.getPrezzo()
				+ " " + this.compagnia + " " + this.getCodice();
	}

}