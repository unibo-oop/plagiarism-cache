package control;

import java.util.ArrayList;
import java.util.Calendar;
import model.Spettacolo;

/**
 * /** Classe che eredita da Gestione e permette la gestione degli spettacoli
 * 
 * @author Ilaria Carloni
 * 
 */
public class GestioneSpettacolo extends Gestione<Spettacolo> {
	private static GestioneSpettacolo spettacolo = null;
	public final static String PATH = "spetacolo.ttr";

	/**
	 * Il costruttore permette di ritornare un'unica istanza della classe,
	 * secondo il pattern Singleton
	 * 
	 * @return
	 */
	public static GestioneSpettacolo istanzaSpettacolo() {
		if (GestioneSpettacolo.spettacolo == null) {
			GestioneSpettacolo.spettacolo = new GestioneSpettacolo();
			GestioneSpettacolo.spettacolo.carica(); // carica i dati dal file
		}
		return GestioneSpettacolo.spettacolo;
	}

	/**
	 * Metodo che ritorna una lista di spettacoli, contenenti la stringa del
	 * titolo cercata
	 * 
	 * @param s
	 * @return
	 */
	public ArrayList<Spettacolo> cerca(String s) {
		ArrayList<Spettacolo> lista = new ArrayList<>(20);
		for (Spettacolo spett : this.getLista()) {
			if (spett.getNomeSp().contains(s)) {
				lista.add(spett);
			}
		}
		return lista;
	}

	/**
	 * Metodo che ritorna una lista degli spettacoli che devono ancora essere
	 * messi in scena
	 * 
	 * @param s
	 * @return
	 */
	public ArrayList<Spettacolo> getSpettCorr() {
		ArrayList<Spettacolo> lista = new ArrayList<>(30);
		for (Spettacolo spett : this.getLista()) {
			if (spett.getData().getTime() >= Calendar.getInstance()
					.getTimeInMillis()) {
				lista.add(spett);
			}
		}
		return lista;
	}

	/**
	 * Metodo che ritorna una lista degli spettacoli che sono gia' stati messi
	 * in scena
	 * 
	 * @param s
	 * @return
	 */
	public ArrayList<Spettacolo> getStoricoSpett() {
		ArrayList<Spettacolo> lista = new ArrayList<>(30);
		for (Spettacolo spett : this.getLista()) {
			if (spett.getData().getTime() < Calendar.getInstance()
					.getTimeInMillis()) {
				lista.add(spett);
			}
		}
		return lista;
	}

	/**
	 * Metodo che permette l'aggiunta di un nuovo spettacolo
	 * 
	 * @param spett
	 */
	public void aggiungiSpett(Spettacolo spett) {
		spett.setCodice(this.assegnaCod()); // aggiunge automaticamente un
											// codice univoco
		this.mappa.put(spett.getCodice(), spett); // inserisce lo spett nella
													// mappa
		this.salva();
	}

	/**
	 * Metodo che ritorna il percorso in cui salvare/caricare la mappa relativa
	 * agli spettacoli
	 * 
	 * @return
	 */
	@Override
	public String getPercorso() {
		return PATH;
	}

}