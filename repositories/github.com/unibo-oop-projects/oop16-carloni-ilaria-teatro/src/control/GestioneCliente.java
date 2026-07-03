package control;

import java.util.ArrayList;
import java.util.Collection;
import exception.ExceptionNomeEsistente;
import model.Cliente;
import model.Persona;

/**
 * Classe che eredita da Gestione e permette la gestione dei clienti
 * 
 * @author Ilaria Carloni
 * 
 */
public class GestioneCliente extends Gestione<Cliente> {
	private static GestioneCliente cliente = null;
	public final static String PERCORSO = "cliente.ttr";

	/**
	 * Il costruttore permette di ritornare un'unica istanza della classe,
	 * secondo il pattern Singleton
	 * 
	 * @return
	 */
	public static GestioneCliente istanzaCliente() {
		if (GestioneCliente.cliente == null) {
			GestioneCliente.cliente = new GestioneCliente(); // crea una nuova
																// mappa, se non
																// esiste
			GestioneCliente.cliente.carica();
		}
		return GestioneCliente.cliente; // richiama la mappa associata ai
										// clienti
	}

	/**
	 * Metodo che controlla le credenziali inserite da un cliente all'accesso e
	 * permette il login
	 * 
	 * @param username
	 * @param password
	 * @return
	 */
	public Cliente controlloLoginCl(String username, String password) {
		for (Cliente cl : this.mappa.values()) {
			if (cl.getUsername().equals(username)
					&& (cl.getPassword().equals(password))) {
				return cl;
			}
		}
		return null;
	}

	/**
	 * Metodo che ritorna il percorso in cui salvare/caricare la mappa relativa
	 * ai clienti
	 */
	@Override
	public String getPercorso() {
		return PERCORSO;
	}

	/**
	 * Metodo che permette l'aggiunta di un nuovo cliente
	 * 
	 * @param pers
	 * @throws ExceptionNomeEsistente
	 */
	public void aggiungiCliente(Persona pers) throws ExceptionNomeEsistente {
		for (Cliente cl : this.getLista()) {
			if (cl.getUsername().equals(pers.getUsername())) { // se lo username
																// inserito è
																// già contenuto
																// nella lista
				throw new ExceptionNomeEsistente();
			}
		}
		Cliente cl = new Cliente(pers, this.assegnaCod());
		this.mappa.put(cl.getCodice(), cl);
		this.salva();
	}

	/**
	 * Metodo che permette di cercare il nome di un cliente, partendo da una
	 * stringa presa in input
	 * 
	 * @param cerca
	 * @return
	 */
	public Collection<Cliente> cerca(String cerca) {
		Collection<Cliente> lista = new ArrayList<>(20);
		for (Cliente cl : this.getLista()) {
			if (cl.getNome().contains(cerca)) {
				lista.add(cl);
			}
		}
		return lista; // ritorna una nuova lista che contiene solo gli elementi
						// il cui nome contiene la stringa in input
	}
}
