package model.riepilogo;

import java.time.LocalDate;
import model.piantina.ImplRistorante;
import model.piantina.Prenotazione;
import model.piantina.Ristorante;
import model.utili.Periodo;

public final class ModelRiepilogoImpl implements ModelRiepilogo {

	private final Ristorante ristorante = new ImplRistorante();
	private Prenotazione prenotazione;

	public ModelRiepilogoImpl(Periodo periodoPrenotato, LocalDate dataPrenotata, String idTavoloPrenotato) {
		this.prenotazione = this.ristorante.getListPrenotazioni(dataPrenotata, periodoPrenotato).stream()
		.filter(p -> p.getTavolo().getName() == Integer.parseInt(idTavoloPrenotato)).findFirst().get();
	}
	
	@Override
	public String getNome() {
		return this.prenotazione.getCliente().getNome();
	}
	
	@Override
	public String getCognome() {
		return this.prenotazione.getCliente().getCognome();
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
	public String getPosti() {
		return String.valueOf(this.prenotazione.getPostiPrenotati());
	}
	
	@Override
	public String getCodice() {
		return this.prenotazione.getCodicePrenotazione();
	}
	
}
