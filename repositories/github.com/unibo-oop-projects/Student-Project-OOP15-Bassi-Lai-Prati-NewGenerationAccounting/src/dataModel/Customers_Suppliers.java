package dataModel;

/**
 * Classe per la creazione e Cliente\Fornitore\Credito\Debito.
 * 
 * Verrà usato in Customers_SuppliersModel.
 * 
 * 
 * @author Diego
 * 
 */

import dataEnum.Gender;
import dataEnum.KindPerson;

public class Customers_Suppliers implements IDataTableModel {

	private static final String[] INTESTAZIONE = { "Codice Fiscale", "Credito", "Debito" };
	// Nota personale : GUARDA BOOKMARK.

	private static final long serialVersionUID = 9254381L;

	public static String[] getIntestazione() {
		return INTESTAZIONE;
	}

	private int cap;
	private String cf;
	private String citta;
	private String cognome;
	private int credito;
	private int debito;
	private String indirizzo;
	private String nome;
	private KindPerson ruolo;
	private Gender sesso;
	private String telefono;

	public Customers_Suppliers(String nome, String cognome, String cf, String indirizzo, String citta, int cap,
			String telefono, Gender sesso, KindPerson ruolo, int debito, int credito) {

		this.nome = nome;
		this.cognome = cognome;
		this.cf = cf;
		this.indirizzo = indirizzo;
		this.citta = citta;
		this.cap = cap;
		this.telefono = telefono;
		this.sesso = sesso;
		this.ruolo = ruolo;
		this.debito = debito;
		this.credito = credito;
	}

	public int getCap() {
		return cap;
	}

	public String getCf() {
		return cf;
	}

	public String getCitta() {
		return citta;
	}

	public String getCognome() {
		return cognome;
	}

	public int getCredito() {
		return credito;
	}

	public int getDebito() {
		return debito;
	}

	public String getIndirizzo() {
		return indirizzo;
	}

	public String getNome() {
		return nome;
	}

	public KindPerson getRuolo() {
		return ruolo;
	}

	public Gender getSesso() {
		return sesso;
	}

	public String getTelefono() {
		return telefono;
	}

	@Override
	public String getValueAt(int column) {
		switch (column) {
		case 0:
			return getCf();
		case 1:
			return Integer.toString(credito);
		case 2:
			return Integer.toString(debito);
		default:
			return "";
		}
	}

	public void setCap(int cap) {
		this.cap = cap;
	}

	public void setCf(String cf) {
		this.cf = cf;
	}

	public void setCitta(String citta) {
		this.citta = citta;
	}

	public void setCognome(String cognome) {
		this.cognome = cognome;
	}

	public void setCredito(int credito) {
		this.credito = credito;
	}

	public void setDebito(int debito) {
		this.debito = debito;
	}

	public void setIndirizzo(String indirizzo) {
		this.indirizzo = indirizzo;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public void setRuolo(KindPerson ruolo) {
		this.ruolo = ruolo;
	}

	public void setSesso(Gender sesso) {
		this.sesso = sesso;
	}

	public void setTelefono(String telefono) {
		this.telefono = telefono;
	}
}
