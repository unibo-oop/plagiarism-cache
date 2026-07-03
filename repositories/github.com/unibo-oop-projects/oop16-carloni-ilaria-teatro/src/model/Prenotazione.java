package model;

import java.io.Serializable;

/**
 * Classe che modella una prenotazione
 * 
 * @author Ilaria Carloni
 * 
 */
public class Prenotazione implements Serializable {

	private static final long serialVersionUID = 1L;
	private int codCliente;
	private int codOperatore;
	private int codSpett;
	private int codPrenotazione = -1;
	private int postiPrenotati = 0;

	/**
	 * Costruttore che crea una prenotazione coi paramentri in input
	 * 
	 * @param codCliente
	 * @param codOperatore
	 * @param codSpett
	 * @param codPosto
	 */
	public Prenotazione(int codCliente, int codOperatore, int codSpett,
			int codPosto) {
		this.codCliente = codCliente;
		this.codOperatore = codOperatore;
		this.codSpett = codSpett;
		this.postiPrenotati = codPosto;
	}

	/**
	 * Metodo che ritorna il codice del cliente associato alla prenotazione
	 * 
	 * @return
	 */
	public int getCodCliente() {
		return codCliente;
	}

	/**
	 * Metodo che ritorna il codice dell'eventuale operatore associato alla
	 * prenotazione (-1 se il cliente la esegue tramite la sua interfaccia)
	 * 
	 * @return
	 */
	public int getCodOperatore() {
		return codOperatore;
	}

	/**
	 * Metodo che ritorna il codice dello Spettacolo prenotato
	 * 
	 * @return
	 */
	public int getCodSpett() {
		return codSpett;
	}

	/**
	 * Metodo che ritorna il codice della prenotazione
	 * 
	 * @return
	 */
	public int getCodPrenot() {
		return codPrenotazione;
	}

	/**
	 * Metodo che ritorna il numero dei posti prenotati per lo spettacolo in
	 * esame dall'utente
	 * 
	 * @return
	 */
	public int getPostiPrenotati() {
		return this.postiPrenotati;
	}

	/**
	 * Metodo che ritorna il codice univoco di una prenotazione
	 * 
	 * @return
	 */
	public void setCodPrenot(int codPrenotazione) {
		this.codPrenotazione = codPrenotazione;
	}

	/**
	 * Metodo che stampa in console una prenotazione
	 */
	@Override
	public String toString() {
		return "cod Cliente: " + codCliente + " cod Operatore: " + codOperatore
				+ " cod Spettacolo: " + codSpett + "posti prenotati: "
				+ postiPrenotati;
	}
}
