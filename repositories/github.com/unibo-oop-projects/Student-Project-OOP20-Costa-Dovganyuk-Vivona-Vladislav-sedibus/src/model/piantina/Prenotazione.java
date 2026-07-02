package model.piantina;

import model.utili.Cliente;

public class Prenotazione {

	private String codicePrenotazione;
	private Cliente cliente;
	private Tavolo tavolo;
	private int nPostiPrenotati;
	
	public Prenotazione(String codicePrenotazione, Cliente cliente, Tavolo tavolo, int nPostiPrenotati) {
		super();
		this.codicePrenotazione = codicePrenotazione;
		this.cliente = cliente;
		this.tavolo = tavolo;
		this.nPostiPrenotati = nPostiPrenotati;
	}	

	/**
	 * @return the reservation code
	 */
	public String getCodicePrenotazione() {
		return this.codicePrenotazione;
	}

	/**
	 * @return the booking customer
	 */
	public Cliente getCliente() {
		return this.cliente;
	}

	/**
	 * @return the reserved table
	 */
	public Tavolo getTavolo() {
		return this.tavolo;
	}

	/**
	 * @return the reserved seats
	 */
	public int getPostiPrenotati() {
		return this.nPostiPrenotati;
	}

	/**
	 * @return the current base reservation
	 */
	public Prenotazione getPrenotazione() {
		return new Prenotazione(getCodicePrenotazione(),getCliente(), getTavolo(), getPostiPrenotati());
	}
	
}
