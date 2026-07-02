package dataModel;

/**
 * Classe per la gestione di un singolo tipo oggetto, verra' usata per il
 * carrello dell'utente.
 * 
 * Usata da CreaFattureModel per la lista degli "Oggetti" all'interno.
 * 
 * @author Diego
 *
 */

public class Item implements IDataTableModel {

	private static final String[] INTESTAZIONE = { "Prodotto", "Quantita'", "Subtotale" };
	/**
	 * 
	 */
	private static final long serialVersionUID = 2052019487211753549L;

	public static String[] getIntestazione() {
		return INTESTAZIONE;
	}

	private Product prodotto;
	private int quantita;

	public Item(Product prodotto, int quantita) {

		this.prodotto = prodotto;
		this.quantita = quantita;

	}

	// prove, non sarebbero necessari

	public String getNome() {
		return prodotto.getNome();
	}

	public int getPrezzo() {
		return this.getProdotto().getPrezzovendita();
	}

	public Product getProdotto() {
		return prodotto;
	}

	public int getQuantita() {
		return quantita;
	}

	@Override
	public String getValueAt(int column) {
		switch (column) {
		case 0:
			return prodotto.getNome();
		case 1:
			return Integer.toString(quantita);
		case 2:
			return Integer.toString(prodotto.getPrezzovendita() * quantita);
		default:
			return "";
		}
	}

	public void setNome(String nomeProdotto) {

		this.getProdotto().setNome(nomeProdotto);
	}

	public void setPrezzo(int prezzo) {
		this.getProdotto().setPrezzovendita(prezzo);
	}

	public void setProdotto(Product prodotto) {
		this.prodotto = prodotto;
	}

	public void setQuantita(int quantita) {
		this.quantita = quantita;
	}

}
