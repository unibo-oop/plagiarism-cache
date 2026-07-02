package model.piantina;

import java.time.LocalDate;

import model.utili.Cliente;
import model.utili.Periodo;

public final class PrenotazioneEstesa extends Prenotazione {

	private String data;
	private Periodo periodo;
	
	public PrenotazioneEstesa(Periodo periodo, LocalDate data, String codicePrenotazione, Cliente cliente, Tavolo tavolo, int nPostiPrenotati) {
		super(codicePrenotazione, cliente, tavolo, nPostiPrenotati);
		this.periodo = periodo;
		this.data = data.toString();
	}
	
	/**
	 * @return the date of the reservation
	 */
	public String getLocalData() {
		return this.data;
	}

	/**
	 * @return the period of the reservation
	 */
	public Periodo getPeriodo() {
		return this.periodo;
	}
	
}
