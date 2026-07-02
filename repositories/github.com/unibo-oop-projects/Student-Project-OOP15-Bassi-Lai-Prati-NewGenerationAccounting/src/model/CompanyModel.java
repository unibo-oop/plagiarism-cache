package model;

import java.util.Arrays;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;
import java.util.UUID;

import javax.management.InstanceAlreadyExistsException;
import javax.management.InstanceNotFoundException;

import dataModel.Company;
import dataModel.DBDataModel;
import dataModel.IDataTableModel;

/**
 * Classe implementativa per la gestione dell'anagrafica aziende e la creazione
 * di quest'ultime.
 * 
 * @author Diego
 *
 */

public class CompanyModel implements ModelInterface {

	private final static String ragione_sociale = "Ragione Sociale";
	private final static String cap = "CAP";
	private final static String citta = "Citta'";
	private final static String provincia = "Provincia";
	private final static String indirizzo = "Indirizzo";
	private final static String telefono = "Telefono";
	private final static String p_iva = "P.IVA";
	private final static String password = "Password";

	private Company nuovaazienda;

	private final LinkedList<Company> listaAziende;

	public CompanyModel(final LinkedList<Company> linkedList) {
		listaAziende = linkedList;
	}

	/**
	 * Metodo per la creazione di una nuova azienda.
	 * 
	 * @author Diego
	 *
	 */

	@Override
	public void add(Map<String, Object> elem) throws InstanceAlreadyExistsException {

		System.out.print(elem);

		if (elem.get(ragione_sociale).equals("")) {
			throw new IllegalArgumentException("Ragione sociale non valida. Riprovare.");
		}

		if (elem.get(password).equals("")) {
			throw new IllegalArgumentException("Password non valida. Riprovare.");
		}

		if (elem.get(p_iva).equals("")) {
			throw new IllegalArgumentException("PartitaIVA non valida. Riprovare.");
		}

		if (elem.get(cap).equals(null)) {
			throw new IllegalArgumentException("CAP non valido. Riprovare.");

		}

		if (elem.get(citta).equals("")) {
			throw new IllegalArgumentException("Citta' non valida. Riprovare.");

		}
		if (elem.get(provincia).equals("")) {
			throw new IllegalArgumentException("Provincia non valida. Riprovare.");

		}

		if (elem.get(indirizzo).equals("")) {
			throw new IllegalArgumentException("Indirizzo non valido. Riprovare.");

		}

		if (elem.get(telefono).equals("")) {
			throw new IllegalArgumentException("Numero di telefono non valido. Riprovare.");

		}

		nuovaazienda = new Company(UUID.randomUUID(), (char[]) elem.get(password), (String) elem.get(ragione_sociale),
				(String) elem.get(p_iva), (String) elem.get(indirizzo), (String) elem.get(citta), (int) elem.get(cap),
				(String) elem.get(provincia), (String) elem.get(telefono));

		if (listaAziende.contains(nuovaazienda)) {
			throw new InstanceAlreadyExistsException("L'elemento e' gia' presente.");
		}
		listaAziende.add(nuovaazienda);
	}

	/**
	 * Metodo per modificare una azienda già esistente.
	 * 
	 * @author Diego
	 *
	 */

	@Override
	public void edit(IDataTableModel obj, Map<String, Object> infoDaModificare)
			throws InstanceAlreadyExistsException, InstanceNotFoundException {

		((Company) obj).setPassword((char[]) infoDaModificare.get(password));
		((Company) obj).setRagione_sociale((String) infoDaModificare.get(ragione_sociale));
		((Company) obj).setPartita_iva((String) infoDaModificare.get(p_iva));
		((Company) obj).setIndirizzo((String) infoDaModificare.get(indirizzo));
		((Company) obj).setCitta((String) infoDaModificare.get(citta));
		((Company) obj).setCap((Integer) infoDaModificare.get(cap));
		((Company) obj).setProvincia((String) infoDaModificare.get(provincia));
		((Company) obj).setTel((String) infoDaModificare.get(telefono));

	}

	/**
	 * Metodo per la creazione dei filtri di ricerca per le aziende presenti.
	 * 
	 * @author Diego
	 *
	 */

	@Override
	public Map<String, Object> getFilterMap() {
		Map<String, Object> mappaFiltro = new HashMap<>();
		mappaFiltro.put(ragione_sociale, new String(""));

		return mappaFiltro;
	}

	/**
	 * Metodo per individuare l'ultimo codice UUID(Codice_azienda) utilizzato.
	 * 
	 * 
	 *
	 */

	public UUID getLastAddedItemCode() {
		if (nuovaazienda != null && nuovaazienda.getCodice_azienda() != null
				&& nuovaazienda.getCodice_azienda() instanceof UUID) {
			return nuovaazienda.getCodice_azienda();
		}
		return null;
	}

	/**
	 * Restituisco la mappa delle aziende, con o senza dati.
	 * 
	 * 
	 * 
	 * @param iDataTableModel
	 * 
	 * 
	 * 
	 */

	@Override
	public Map<String, Object> getMap(IDataTableModel obj) {

		if (obj == null) {
			Map<String, Object> mappaVuota = new HashMap<>();

			mappaVuota.put(password, new char[] {});
			mappaVuota.put(ragione_sociale, new String(""));
			mappaVuota.put(p_iva, new String(""));
			mappaVuota.put(indirizzo, new String());
			mappaVuota.put(citta, new String(""));
			mappaVuota.put(cap, new Integer(0));
			mappaVuota.put(provincia, new String(""));
			mappaVuota.put(telefono, new String(""));

			return mappaVuota;

		} else {
			if (obj instanceof Company) {
				Map<String, Object> mappaPiena = new HashMap<>();
				mappaPiena.put(password, ((Company) obj).getPassword());
				mappaPiena.put(ragione_sociale, ((Company) obj).getRagione_sociale());
				mappaPiena.put(p_iva, ((Company) obj).getPartita_iva());
				mappaPiena.put(indirizzo, ((Company) obj).getIndirizzo());
				mappaPiena.put(citta, ((Company) obj).getCitta());
				mappaPiena.put(cap, ((Company) obj).getCap());
				mappaPiena.put(provincia, ((Company) obj).getProvincia());
				mappaPiena.put(telefono, ((Company) obj).getTel());

				return mappaPiena;

			} else {
				throw new IllegalArgumentException("Valori non validi, riprovare.");
			}
		}

	}

	/**
	 * Controlla che la password inserita dall'utente corrisponda a quella del
	 * DB.
	 * 
	 * 
	 * 
	 */

	public boolean isPasswordCorrect(final char[] password, final Company company) {
		return Arrays.equals(password, company.getPassword());
	}

	/**
	 * Restituisce listaAziende.
	 * 
	 * 
	 * 
	 */

	@Override
	public LinkedList<Company> load() {
		return new LinkedList<Company>(listaAziende);
	}

	/**
	 * Creazione liste filtrate.
	 * 
	 * 
	 * 
	 */
	// ? extends IDataTableModel
	@Override
	public LinkedList<Company> load(Map<String, Object> mappaFiltro) throws InstanceNotFoundException {

		LinkedList<Company> listaFiltrata = new LinkedList<>();

		if (mappaFiltro.get(ragione_sociale) != null) {
			for (Company controllofiltro : listaAziende) {
				if (controllofiltro.getRagione_sociale().equals(mappaFiltro.get(ragione_sociale))) {
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
	 * Metodo che permette l'eliminazione di una azienda già esistente.
	 * 
	 *
	 * 
	 */

	@Override
	public void remove(IDataTableModel elem) {
		if (listaAziende.contains(elem)) {
			listaAziende.remove(elem);
		} else {
			throw new IllegalArgumentException("Elemento non trovato.");
		}
	}

	/**
	 * 
	 * Metodo per spostare i dati dalla lista interna al database e restituire
	 * quest'ultimo.
	 * 
	 * Utilizzato in questo caso al posto di saveDBAndClose.
	 * 
	 * saveCompanyAndClose = saveDBAndClose senza l'utilizzo del DB.
	 * 
	 * 
	 * 
	 */

	public LinkedList<Company> saveCompanysAndClose() {
		return listaAziende;
	}

	/**
	 * 
	 * Metodo per spostare i dati dalla lista interna al database e restituire
	 * quest'ultimo.
	 * 
	 * In questo caso il metodo risulta inutilizzabile. Al suo posto si usa
	 * saveCompanysAndClose().
	 * 
	 * 
	 * 
	 */

	@Override
	public DBDataModel saveDBAndClose() {
		return null;
	}
}