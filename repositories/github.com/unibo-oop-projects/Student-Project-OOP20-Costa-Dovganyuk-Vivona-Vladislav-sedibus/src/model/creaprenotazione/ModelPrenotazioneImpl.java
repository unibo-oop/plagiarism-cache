package model.creaprenotazione;

import java.time.LocalDate;
import java.util.stream.Collectors;
import java.util.*;

import model.piantina.ImplRistorante;
import model.piantina.PrenotazioneEstesa;
import model.piantina.Ristorante;
import model.piantina.Tavolo;
import model.utili.Cliente;
import model.utili.Periodo;

public final class ModelPrenotazioneImpl implements ModelPrenotazione {
	
	private Cliente cliente;
	private final Ristorante ristorante = new ImplRistorante();
	private Tavolo tavoloScelto;
	private PilotaPosti gestorePosti;
	private Periodo periodoScelto;
	private LocalDate dataScelta;
	private String vecchioCodice;
	private Periodo vecchioPeriodo;
	private LocalDate vecchiaData;
	
	public ModelPrenotazioneImpl() { }
	
	@Override
	public void prendiTavolo(String idTavolo) {
		final int id = Integer.parseInt(idTavolo);
		this.ristorante.tavoliRistorante().forEach(t -> {
			if(t.getName() == id) {
				this.tavoloScelto = new Tavolo(id, t.getMaxPosti());
			}
		});
	}
	
	@Override
	public void settaPostiModifica(int posti) {
		this.istanziaGestore();
		this.gestorePosti.setNumeroPosti(posti);
	}
	
	@Override
	public void settaPostiCreazione() {
		this.istanziaGestore();
	}
	
	private void istanziaGestore() {
		this.gestorePosti = new PilotaPosti(this.tavoloScelto.getMaxPosti());
	}
	
	@Override
	public void prendiVecchiaPrenotazione(String codice, Periodo periodo, LocalDate data) {
		this.vecchioCodice = codice;
		this.vecchioPeriodo = periodo;
		this.vecchiaData = data;
	}

	private String prendiCognomeVecchioCliente() {
		return this.ristorante.getListPrenotazioni(this.dataScelta, this.periodoScelto).stream()
						.filter(e -> e.getCodicePrenotazione().equals(this.vecchioCodice))
						.map(p -> p.getCliente().getCognome()).findFirst()
						.get();
	}
	
	@Override
	public void incrementaPosti() {
		this.gestorePosti.aggiungiPosto();
	}

	@Override
	public void decrementaPosti() {
		this.gestorePosti.togliPosto();
	}

	@Override
	public void inizializzaPosti() {
		this.gestorePosti.azzeraPosti();
	}

	@Override
	public int postiCorrenti() {
		return this.gestorePosti.getNumeroPosti();
	}

	@Override
	public boolean prendiDatiCliente(String nome, String cognome, String email, String telefono) {
		this.cliente = new Cliente(nome, cognome, email, telefono);
		return this.cliente.rispettaControlli();
	}

	@Override
	public void prendiPeriodo(String periodo) {
		this.periodoScelto = periodo.equalsIgnoreCase(Periodo.PRANZO.toString()) ? Periodo.PRANZO : Periodo.CENA;
	}
	
	@Override
	public void prendiData(LocalDate data) {
		this.dataScelta = data;
	}
	
	@Override
	public void aggiungiPrenotazione() {
		this.ristorante.nuovaPrenotazione(new PrenotazioneEstesa(this.periodoScelto, this.dataScelta, 
				this.generaCodice(), this.cliente, this.tavoloScelto, this.gestorePosti.getNumeroPosti()));
	}
	
	private String generaCodice() {
		return new GeneratoreCodice().ottieni();
	}

	@Override
	public boolean cercaTavolo() {
		final int postiScelti = this.gestorePosti.getNumeroPosti();
		if (postiScelti <= this.tavoloScelto.getMaxPosti() && 
			this.vecchiaData.equals(this.dataScelta) && 
			this.vecchioPeriodo.equals(this.periodoScelto)) {
			return true;
		}
		final List<Integer> pieni = this.ristorante.tavoliPrenotati(this.dataScelta, this.periodoScelto).stream()
				.map(tp -> tp.getName())
				.collect(Collectors.toList());
		final Optional<Tavolo> libero = this.ristorante.tavoliRistorante().stream()
				.filter(t -> !pieni.contains(t.getName()) && t.getMaxPosti() >= postiScelti)
				.findFirst();
		if(libero.isPresent()) {
			this.tavoloScelto = new Tavolo(libero.get().getName(), libero.get().getMaxPosti());
			return true;
		} else {
			return false;
		}
	}
	
	@Override
	public void modificaPrenotazione() {
		this.ristorante.getPrenotazioni(this.vecchioPeriodo).entrySet().forEach(e -> {
			e.getValue().forEach(p -> {
				if(p.getCodicePrenotazione().equals(this.vecchioCodice)) {
					this.ristorante.eliminaPrenotazione(this.vecchioPeriodo, this.vecchioCodice, this.prendiCognomeVecchioCliente());
					this.ristorante.nuovaPrenotazione(new PrenotazioneEstesa(this.periodoScelto, 
							this.dataScelta, this.vecchioCodice, this.cliente, this.tavoloScelto, 
							this.gestorePosti.getNumeroPosti()));
				}
			});
		});
	}
	
}
