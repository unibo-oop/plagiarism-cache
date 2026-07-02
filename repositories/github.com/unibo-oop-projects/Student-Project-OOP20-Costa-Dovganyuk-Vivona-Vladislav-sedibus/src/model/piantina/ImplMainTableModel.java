package model.piantina;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Stream;
import model.utili.Cliente;
import model.utili.Periodo;

public final class ImplMainTableModel implements MainTableModel {

	private final Ristorante ristorante = new ImplRistorante();
	private List<Tavolo> listaTavoli = new ArrayList<>();  //lista totale dei Tavoli
	private Periodo periodo;
	private LocalDate data;
	
	
	public ImplMainTableModel(Periodo p, LocalDate d) {
		this.listaTavoli = this.ristorante.tavoliRistorante();
		this.periodo = p;
		this.data = d;
	}
	
	
	@Override
	public List<Integer> tavoliPrenotati(LocalDate date, Periodo p) {
		final List<Integer> listaID = new ArrayList<>();
		ristorante.tavoliPrenotati(data, periodo).forEach(t ->{
			listaID.add(t.getName());
		});
		
		return listaID;
	}
	
	@Override
	public int getPostiMax(int ID) {
		return this.listaTavoli.stream().filter(t -> t.getName() == ID).mapToInt(e -> e.getMaxPosti()).findFirst().getAsInt();
	}
	
	@Override
	public String getCodicePrenotazione(int idTavolo) {
		return ristorante.getListPrenotazioni(data, periodo).stream().filter(e -> e.getTavolo().getName() == idTavolo).findFirst().get().getCodicePrenotazione();
	}

	@Override
	public String getCognomeNomeCliente(int idTavolo) {
		final Cliente c = getCliente(idTavolo);
		return c.getNome().concat(" "+c.getCognome());
	}
	
	@Override
	public String getPostiPrenotati(int idTavolo) {
		return String.valueOf(getInformazioniPrenotazione(idTavolo).get().getPrenotazione().getPostiPrenotati());
	}

	@Override
	public String getNumTelefonoCliente(int idTavolo) {
		return getCliente(idTavolo).getTelefono();
	}

	@Override
	public String getEmailCliente(int idTavolo) {
		return getCliente(idTavolo).getEmail();
	}
	
	private Optional<Prenotazione> getInformazioniPrenotazione(int idTavolo) {
		return streamPrenotazioni().filter(p -> p.getTavolo().getName() == idTavolo).findFirst();
	}
	
	@Override
	public Cliente getCliente(int idTavolo) {
		return getInformazioniPrenotazione(idTavolo).get().getCliente();
	}
	
	private Stream<Prenotazione> streamPrenotazioni(){
		return ristorante.getListPrenotazioni(data, periodo).stream();
	}

	@Override
	public Prenotazione getPrenotazione(String codicePrenotazione) {
		return streamPrenotazioni().filter(p -> p.getCodicePrenotazione().equals(codicePrenotazione)).findFirst().get().getPrenotazione();
	}

	@Override
	public int getIdTavolo(String codicePrenotazione) {
		return streamPrenotazioni().filter(p -> p.getCodicePrenotazione().equals(codicePrenotazione)).findFirst().get().getTavolo().getName();
	}

}
