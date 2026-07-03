package control;

import exception.ExceptionNomeEsistente;
import model.Operatore;
import model.Persona;

/**
 * Classe che eredita da Gestione e permette la gestione degli operatori
 * 
 * @author Ilaria Carloni
 * 
 */
public class GestioneOperatore extends Gestione<Operatore> {
	private static GestioneOperatore operatore = null;
	public final static String PERCORSO = "operatore.ttr";

	/**
	 * Il costruttore permette di ritornare un'unica istanza della classe,
	 * secondo il pattern Singleton
	 * 
	 * @return
	 */
	public static GestioneOperatore istanzaOperatore() {
		if (GestioneOperatore.operatore == null) {
			GestioneOperatore.operatore = new GestioneOperatore(); // crea una
																	// nuova
																	// mappa, se
																	// non
																	// esiste
																	// già
			GestioneOperatore.operatore.carica();
		}
		return GestioneOperatore.operatore;// richiama la mappa associata agli
											// operatori
	}

	/**
	 * Metodo che controlla le credenziali inserite da un operatore all'accesso
	 * e permette il login
	 * 
	 * @param username
	 * @param password
	 * @return
	 */
	public Operatore controlloLoginOp(String username, String password) {
		for (Operatore op : this.mappa.values()) {
			if (op.getUsername().equals(username)
					&& (op.getPassword().equals(password))) {
				return op;
			}
		}
		return null;
	}

	/**
	 * Metodo che ritorna il percorso in cui salvare/caricare la mappa relativa
	 * agli operatori
	 */
	@Override
	public String getPercorso() {
		return PERCORSO;
	}

	/**
	 * Metodo che permette l'aggiunta di un nuovo operatore
	 * 
	 * @param pers
	 * @throws ExceptionNomeEsistente
	 */
	public void aggiungiOperatore(Persona pers) throws ExceptionNomeEsistente {
		for (Operatore op : this.getLista()) {
			if (op.getUsername().equals(pers.getUsername())) {
				throw new ExceptionNomeEsistente();
			}
		}
		Operatore op = new Operatore(pers, this.assegnaCod());
		this.mappa.put(op.getCodiceOp(), op);
		this.salva();
	}

}