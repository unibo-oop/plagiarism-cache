package dataModel;

import java.util.UUID;

/**
 * Classe per la gestione della singola azienda.
 * 
 * @author Pentolo
 *
 */

public class Company implements IDataTableModel {

	private static final String[] INTESTAZIONE = { "Rag. Soc", "P.iva" };

	/**
	 * 
	 */
	private static final long serialVersionUID = 2480822272123138506L;

	public static String[] getIntestazione() {
		return INTESTAZIONE;
	}

	private int cap;
	private String citta;
	private UUID codice;
	private String indirizzo;
	private String p_iva;
	private char[] password;
	private String provincia;
	private String ragione_sociale;
	private String tel;

	public Company(final UUID codice, final char[] password, final String ragione_sociale, final String p_iva,
			final String indirizzo, final String citta, final int cap, final String provincia, final String tel) {

		this.codice = codice;
		this.password = password;
		this.ragione_sociale = ragione_sociale;
		this.indirizzo = indirizzo;
		this.citta = citta;
		this.cap = cap;
		this.provincia = provincia;
		this.tel = tel;
		this.p_iva = p_iva;

	}

	public int getCap() {
		return cap;
	}

	public String getCitta() {
		return citta;
	}

	public UUID getCodice_azienda() {
		return codice;
	}

	public String getIndirizzo() {
		return indirizzo;
	}

	public String getPartita_iva() {
		return p_iva;
	}

	public char[] getPassword() {
		return password;
	}

	public String getProvincia() {
		return provincia;
	}

	public String getRagione_sociale() {
		return ragione_sociale;
	}

	public String getTel() {
		return tel;
	}

	@Override
	public String getValueAt(final int column) {
		switch (column) {
		case 0:
			return getRagione_sociale();
		case 1:
			return getPartita_iva();
		default:
			return "";
		}
	}

	public void setCap(final int cap) {
		this.cap = cap;
	}

	public void setCitta(final String citta) {
		this.citta = citta;
	}

	public void setCodice(final UUID codice) {
		this.codice = codice;
	}

	public void setIndirizzo(final String indirizzo) {
		this.indirizzo = indirizzo;
	}

	public void setPartita_iva(final String p_iva) {
		this.p_iva = p_iva;
	}

	public void setPassword(final char[] password) {
		this.password = password;
	}

	public void setProvincia(final String provincia) {
		this.provincia = provincia;
	}

	public void setRagione_sociale(final String ragione_sociale) {
		this.ragione_sociale = ragione_sociale;
	}

	public void setTel(final String tel) {
		this.tel = tel;
	}
}
