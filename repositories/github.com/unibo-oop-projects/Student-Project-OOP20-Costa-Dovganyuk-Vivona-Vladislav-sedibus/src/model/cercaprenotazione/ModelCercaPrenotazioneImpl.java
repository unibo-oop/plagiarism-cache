package model.cercaprenotazione;

import java.time.LocalDate;

import model.piantina.ImplRistorante;
import model.piantina.Prenotazione;
import model.piantina.Ristorante;
import model.utili.Periodo;

public final class ModelCercaPrenotazioneImpl implements ModelCercaPrenotazione {

	private final Ristorante ristorante = new ImplRistorante();
	private boolean prenotazTrovata;
	private LocalDate data;
	private Prenotazione prenotazione;
	private String codice;
	private String cognome;
	private Periodo periodo;
	
	public ModelCercaPrenotazioneImpl() {
		this.prenotazTrovata = false;
	}

	@Override
	public void prendiDati(String cod, String cognome, Periodo periodo) {
		this.codice = cod;
		this.cognome = cognome;
		this.periodo = periodo;
	}
	
	@Override
	public boolean cercaDati() {
		this.ristorante.getPrenotazioni(this.periodo).entrySet().forEach(elem -> {
			elem.getValue().forEach(pren -> {
				if (pren.getCodicePrenotazione().equals(this.codice) && 
					pren.getCliente().getCognome().equals(this.cognome)) {
					this.prenotazione = pren;
					this.data = LocalDate.parse(elem.getKey());
					this.prenotazTrovata = true;
				}
			});
		});
		return this.prenotazTrovata;
	}
	
	@Override
	public Prenotazione getPrenotazione() {
		return this.prenotazione;
	}
	
	@Override
	public String getNome() {
		return this.prenotazione.getCliente().getNome();
	}
	
	@Override
	public String getEmail() {
		return this.prenotazione.getCliente().getEmail();
	}
	
	@Override
	public String getTelefono() {
		return this.prenotazione.getCliente().getTelefono();
	}
	
	@Override
	public LocalDate getData() {
		return this.data;
	}
	
	@Override
	public String getPosti() {
		return String.valueOf(this.prenotazione.getPostiPrenotati());
	}
	
	@Override
	public String getIdTavolo() {
		return String.valueOf(this.prenotazione.getTavolo().getName());
	}
	
	@Override
	public boolean eliminaPrenotazione() {
		return this.ristorante.eliminaPrenotazione(this.periodo, this.codice, this.cognome);
	}

}