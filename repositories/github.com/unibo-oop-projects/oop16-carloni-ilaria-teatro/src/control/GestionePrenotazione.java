package control;

import java.util.ArrayList;

import model.Prenotazione;

/**
 * /** Classe che eredita da Gestione e permette la gestione delle prenotazioni
 * 
 * @author Ilaria Carloni
 * 
 */
public class GestionePrenotazione extends Gestione<Prenotazione> {
	private static GestionePrenotazione prenotazione = null;
	public final static String PATH = "prenotazione.ttr";

	/**
	 * Il costruttore permette di ritornare un'unica istanza della classe,
	 * secondo il pattern Singleton
	 * 
	 * @return
	 */
	public static GestionePrenotazione istanzaPrenotazione() {
		if (GestionePrenotazione.prenotazione == null) {
			GestionePrenotazione.prenotazione = new GestionePrenotazione(); // crea
																			// una
																			// nuova
																			// mappa,
																			// se
																			// non
																			// esiste
																			// già
			GestionePrenotazione.prenotazione.carica();
		}
		return GestionePrenotazione.prenotazione;
	}

	/**
	 * Metodo che ritorna una lista contenente tutte le prenotazioni del cliente
	 * con il codice preso in input
	 * 
	 * @param codCliente
	 * @return
	 */
	public ArrayList<Prenotazione> getListaSpettCliente(int codCliente) {
		ArrayList<Prenotazione> lista = new ArrayList<>(20);
		for (Prenotazione prenotato : this.getLista()) {
			if (prenotato.getCodCliente() == codCliente) {
				lista.add(prenotato);
			}
		}
		return lista;
	}

	/**
	 * Metodo che permette l'aggiunta di una nuova prenotazione
	 * 
	 * @param prenot
	 * @return
	 */
	public void aggiungiPrenotazione(Prenotazione prenot) {
		prenot.setCodPrenot(this.assegnaCod());
		this.mappa.put(prenot.getCodPrenot(), prenot);
		this.salva();
	}

	/**
	 * Metodo che ritorna il percorso in cui salvare/caricare la mappa relativa
	 * alle prenotazioni
	 * 
	 * @return
	 */
	@Override
	public String getPercorso() {
		return PATH;
	}

	/**
	 * Metodo che ritorna una lista contenente gli spettacoli che contengono la
	 * stringa presa in input nel nome
	 * 
	 * @param s
	 * @return
	 */
	public ArrayList<Prenotazione> cerca(String s) {
		ArrayList<Prenotazione> lista = new ArrayList<>(20);
		for (Prenotazione prenot : this.getLista()) {
			if (GestioneSpettacolo.istanzaSpettacolo()
					.get(prenot.getCodSpett()).getNomeSp().contains(s)) {
				lista.add(prenot);
			}
		}
		return lista;
	}
}