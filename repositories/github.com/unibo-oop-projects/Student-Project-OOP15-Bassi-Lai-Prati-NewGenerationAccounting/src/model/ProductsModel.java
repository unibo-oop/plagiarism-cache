package model;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;

import javax.management.InstanceAlreadyExistsException;
import javax.management.InstanceNotFoundException;

import dataModel.DBDataModel;
import dataModel.IDataTableModel;
import dataModel.Product;

/**
 * Classe implementativa per la gestione dei prodotti dell'azienda e la loro
 * modellazione.
 * 
 * @author Diego
 *
 */

public class ProductsModel implements ModelInterface {

	private final static String nome = "Nome Prodotto";
	private final static String codiceP = "Codice Prodotto";
	private final static String codiceA = "CodiceAcquisto";
	private final static String codiceV = "CodiceVendita";
	private final static String categoria = "Categoria";
	private final static String descrizione = "Descrizione";
	private final static String prezzo = "Prezzo";
	private final static String rimanenze = "Rimanenze"; // string di scorta
	private int scorta;
	LinkedList<Product> listaProdotti;

	private DBDataModel db;

	public ProductsModel(DBDataModel db) {
		this.db = db;
		this.listaProdotti = db.getProducts();
	}

	/**
	 * Metodo per la creazione di un nuovo prodotto.
	 * 
	 * @throws InstanceAlreadyExistsException
	 * 
	 * 
	 */

	@Override
	public void add(Map<String, Object> elem) throws IllegalArgumentException, InstanceAlreadyExistsException {

		if (elem.get(nome).equals("")) {
			throw new IllegalArgumentException("Nome non valido. Riprovare.");
		}

		if (elem.get(codiceP).equals(null)) {
			throw new IllegalArgumentException("Codice Prodotto non valido. Riprovare.");
		}

		if (elem.get(codiceA).equals(null)) {
			throw new IllegalArgumentException("Codice Acquisto non valido. Riprovare.");
		}

		if (elem.get(codiceV).equals(null)) {
			throw new IllegalArgumentException("Codice Vendita non valido. Riprovare.");
		}

		if (elem.get(categoria).equals(null)) {
			throw new IllegalArgumentException("Categoria non valida. Riprovare.");
		}

		if (elem.get(prezzo).equals(null)) {
			throw new IllegalArgumentException("Prezzo non valido. Riprovare.");
		}

		// if (listaProdotti.contains(elem)) {
		// throw new IllegalArgumentException("Elemento gia' esistente!");
		// } else {
		Product nuovoprodotto = new Product(elem.get(nome).toString(), (int) elem.get(codiceP),
				(Integer) elem.get(codiceA), (Integer) elem.get(codiceV), (int) elem.get(rimanenze),
				elem.get(descrizione).toString(), elem.get(categoria).toString(), (int) elem.get(prezzo));

		if (listaProdotti.contains(nuovoprodotto)) {
			throw new InstanceAlreadyExistsException("L'elemento e' gia' presente.");
		}
		listaProdotti.add(nuovoprodotto);

		// }
	}

	/**
	 * Metodo per la modifica di un prodotto.
	 * 
	 * 
	 */

	@Override
	public void edit(IDataTableModel obj, Map<String, Object> infoDaModificare) throws InstanceNotFoundException {

		((Product) obj).setNome((String) infoDaModificare.get(nome));
		((Product) obj).setCodice_Prodotto((Integer) infoDaModificare.get(codiceP));
		((Product) obj).setCod_acquisto((Integer) infoDaModificare.get(codiceA));
		((Product) obj).setCod_vendita((Integer) infoDaModificare.get(codiceV));
		((Product) obj).setCategoria((String) infoDaModificare.get(categoria));
		((Product) obj).setDescrizione((String) infoDaModificare.get(descrizione));
		((Product) obj).setPrezzovendita((Integer) infoDaModificare.get(prezzo));
		((Product) obj).setScorta((Integer) infoDaModificare.get(rimanenze));

	}

	/**
	 * Metodo per la creazione di filtri di ricerca per i prodotti.
	 * 
	 * 
	 */

	@Override
	public Map<String, Object> getFilterMap() {
		Map<String, Object> mappaFiltro = new HashMap<>();
		mappaFiltro.put(nome, new String(""));
		return mappaFiltro;
	}

	/**
	 * Metodo per la creazione di un nuove mappe e la loro restituzione, sia con
	 * valori vuoti che con quelli definiti.
	 * 
	 * 
	 */

	@Override
	public Map<String, Object> getMap(IDataTableModel obj) {

		if (obj == null) {
			Map<String, Object> mappaVuota = new HashMap<>();

			mappaVuota.put(nome, new String(""));
			mappaVuota.put(codiceP, new Integer(0));
			mappaVuota.put(codiceA, new Integer(0));
			mappaVuota.put(codiceV, new Integer(0));
			mappaVuota.put(categoria, new String(""));
			mappaVuota.put(descrizione, new String(""));
			mappaVuota.put(prezzo, new Integer(0));
			mappaVuota.put(rimanenze, new Integer(0));

			return mappaVuota;

		} else {
			if (obj instanceof Product) {
				Map<String, Object> mappaPiena = new HashMap<>();
				mappaPiena.put(nome, ((Product) obj).getNome());
				mappaPiena.put(codiceP, ((Product) obj).getCodice_Prodotto());
				mappaPiena.put(codiceA, ((Product) obj).getCod_acquisto());
				mappaPiena.put(codiceV, ((Product) obj).getCod_vendita());
				mappaPiena.put(categoria, ((Product) obj).getCategoria());
				mappaPiena.put(descrizione, ((Product) obj).getDescrizione());
				mappaPiena.put(prezzo, ((Product) obj).getPrezzovendita());
				mappaPiena.put(rimanenze, ((Product) obj).getScorta());

				return mappaPiena;
			} else {
				throw new IllegalArgumentException("Valori non validi, riprovare.");
			}
		}

	}

	/**
	 * Metodo per la restituzione listaProdotti
	 * 
	 * 
	 */

	@Override
	public LinkedList<Product> load() {
		return new LinkedList<Product>(listaProdotti);
	}

	/**
	 * Metodo per la creazione della mappa filtrata.
	 * 
	 * 
	 */

	@Override
	public LinkedList<? extends IDataTableModel> load(Map<String, Object> mappaFiltro)
			throws InstanceNotFoundException {

		LinkedList<Product> listaFiltrata = new LinkedList<>();

		if (mappaFiltro.get(nome) != null) {
			for (Product controllofiltro : listaProdotti) {
				if (controllofiltro.getNome().equals(mappaFiltro.get(nome))) {
					listaFiltrata.add(controllofiltro);

				}
			}
		}
		if (listaFiltrata.isEmpty()) {
			throw new InstanceNotFoundException("Nella lista non sono presenti elementi che soddisfano i filtri.");
		}

		return listaFiltrata;
	}

	/**
	 * Metodo per rimuovere un prodotto esistente.
	 * 
	 * 
	 */

	@Override
	public void remove(IDataTableModel elem) {
		if (listaProdotti.contains(elem)) {
			// controllo scorta = 0? -> Y = ok cancella N = errore
			if ((listaProdotti.contains(scorta > 0))) {
				throw new IllegalArgumentException(
						"Hai ancora rimanenze in magazzino di questo prodotto, non puoi eliminarlo.");
			}
			listaProdotti.remove(elem);
		} else {
			throw new IllegalArgumentException("Elemento non trovato.");
		}
	}

	/**
	 * Metodo per spostare i dati dalla lista interna al database e restituire
	 * quest'ultimo.
	 * 
	 * 
	 */

	@Override
	public DBDataModel saveDBAndClose() {
		db.setProducts(listaProdotti);
		return db;
	}

}
