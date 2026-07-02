package model;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;

import javax.management.InstanceAlreadyExistsException;
import javax.management.InstanceNotFoundException;

import dataEnum.Natures;
import dataEnum.Sections;
import dataModel.Account;
import dataModel.DBDataModel;
import dataModel.IDataTableModel;

/**
 * classe implementativa per la gestione dell'anagrafica dei conti
 * 
 * @author niky
 *
 */
public class AccountsModel implements ModelInterface {

	private final static String NATURA = "Natura Conto";
	private final static String NOME = "Nome Conto";
	private final static String SEZIONE = "Sezione del Conto";

	private boolean trovato = false;
	private final DBDataModel db;

	private final LinkedList<Account> listaaccount;

	public AccountsModel(DBDataModel db) {
		this.db = db;
		this.listaaccount = db.getAccounts();
	}

	@Override
	public void add(Map<String, Object> elem) throws InstanceAlreadyExistsException, IllegalArgumentException {
		if (elem.get(NOME) == "" || elem.get(NATURA) == Natures.NESSUNO
				|| (Sections) elem.get(SEZIONE) == Sections.NESSUNO) {
			throw new IllegalArgumentException("nome, natura o sezione non valide");
		}
		Account a = new Account((String) elem.get(NOME), (Natures) elem.get(NATURA), (Sections) elem.get(SEZIONE), 0);
		if (checkSection((Natures) elem.get(NATURA), (Sections) elem.get(SEZIONE))) {
			if (listaaccount.contains(a)) {
				throw new InstanceAlreadyExistsException("elemento già esistente in lista");
			}
			listaaccount.add(a);
		} else {
			throw new IllegalArgumentException("sezione non appartenente alla natura");
		}
	}

	private boolean checkSection(Natures nat, Sections sez) {
		switch (nat) {
		case ATTIVITA:
			return Sections.getAttivita().contains(sez);
		case COSTO:
			return Sections.getCosti().contains(sez);
		case PASSIVITA:
			return Sections.getPassivita().contains(sez);
		case RICAVO:
			return Sections.getRicavi().contains(sez);
		default:
			return false;

		}
	}

	@Override
	public void edit(IDataTableModel obj, Map<String, Object> elemDaModificare)
			throws InstanceNotFoundException, IllegalArgumentException { // modifica
																			// elementi
		trovato = false;
		if (obj instanceof Account) {
			Account a = (Account) obj;
			if (!elemDaModificare.get(NOME).toString().isEmpty()) {
				for (Account elem : listaaccount) {
					if (elem.getName().equals(a.getName())) {
						elem.setName(elemDaModificare.get(NOME).toString());
						trovato = true;
					}
				}
			} else {
				throw new IllegalArgumentException("la stringa inserita come nome non è valida");
			}

			if (trovato == false) {
				throw new InstanceNotFoundException("elemento da modificare non presente in lista");
			}
		} else
			throw new IllegalArgumentException("l'oggetto inserito non è un Conto");
	}

	@Override
	public Map<String, Object> getFilterMap() {
		Map<String, Object> mappaFiltro = new HashMap<>();
		mappaFiltro.put(NOME, new String(""));
		mappaFiltro.put(NATURA, Natures.ATTIVITA);
		mappaFiltro.put(SEZIONE, Sections.CREDITI_VS_SOCI);
		return mappaFiltro;
	}

	@Override
	public Map<String, Object> getMap(IDataTableModel obj) {
		if (obj == null) {
			Map<String, Object> mappaVuota = new HashMap<>();
			mappaVuota.put(NOME, new String(""));
			mappaVuota.put(NATURA, Natures.NESSUNO);
			mappaVuota.put(SEZIONE, Sections.NESSUNO);
			return mappaVuota;
		} else {
			if (obj instanceof Account) {
				Map<String, Object> mappaPiena = new HashMap<>();
				mappaPiena.put(NOME, ((Account) obj).getName());
				return mappaPiena;
			} else {
				throw new IllegalArgumentException("l'oggetto inserito non è un Conto");
			}
		}
	}

	@Override
	public LinkedList<Account> load() { // carica tutti i dati
		return new LinkedList<Account>(listaaccount);
	}

	@Override
	public LinkedList<Account> load(Map<String, Object> mappaFiltro)
			throws InstanceNotFoundException, IllegalArgumentException {
		// carica dati in base ai filtri
		LinkedList<Account> listaFiltrata = new LinkedList<>();
		if (!(mappaFiltro.get(NOME).toString().isEmpty())) { // controllo il
																// nome
			for (Account a : db.getAccounts()) {
				if (a.getName().contentEquals(mappaFiltro.get(NOME).toString()))
					listaFiltrata.add(a);
			}
		}
		// filtro con la natura
		if (mappaFiltro.get(NATURA) instanceof Natures) {
			if (mappaFiltro.get(NATURA) != Natures.NESSUNO) { // controllo
				if (listaFiltrata.isEmpty()) {
					for (Account a : db.getAccounts()) { // singolo filtro su
															// natura
						if (a.getNatura() == mappaFiltro.get(NATURA))
							listaFiltrata.add(a);
					}
				} else { // doppio filtro tra nome e natura
					for (Account a : listaFiltrata) {
						if (a.getNatura() != mappaFiltro.get(NATURA))
							listaFiltrata.remove(a);
					}
				}
			}
		}
		if (mappaFiltro.get(SEZIONE) instanceof Sections && mappaFiltro.get(SEZIONE) != Sections.NESSUNO) {
			// controllo se la sezione appartiene alla nature
			if (mappaFiltro.get(NATURA) != Natures.NESSUNO) {
				if ((checkSection((Natures) mappaFiltro.get(NATURA), (Sections) mappaFiltro.get(SEZIONE)))) {
					for (Account a : listaFiltrata) { // doppio filtro sez + nat
						if (a.getSezione() != mappaFiltro.get(SEZIONE))
							listaFiltrata.remove(a);
					}
				} else {
					throw new IllegalArgumentException("la sezione non appartiene alla natura");
				}
			}
			// natura = null
			if (listaFiltrata.isEmpty()) {
				for (Account a : listaaccount) { // singolo filtro su sezione
					if (a.getSezione() == mappaFiltro.get(SEZIONE))
						listaFiltrata.add(a);
				}
			} else {
				// lista filtrata NON vuota
				for (Account a : listaFiltrata) { // doppio filtro sez + nat
					if (a.getSezione() != mappaFiltro.get(SEZIONE))
						listaFiltrata.remove(a);
				}
			}
		}

		if (listaFiltrata.isEmpty()) {
			throw new InstanceNotFoundException("nella lista non sono presenti elementi che soddisfano i filtri");
		} else
			return listaFiltrata;
	}

	@Override
	public void remove(IDataTableModel elemDaEliminare) throws InstanceNotFoundException, IllegalArgumentException { // elimina
																														// dati
		if (elemDaEliminare instanceof Account) {
			Account a = (Account) elemDaEliminare;
			for (Account elem : listaaccount) {
				if (elem.getName().equals(a.getName())) {
					trovato = true;
					if (elem.getSaldo() == 0) {
						listaaccount.remove(elem);
						db.setAccounts(listaaccount);
					} else {
						throw new IllegalArgumentException("non posso eliminare l'elemento perchè ha saldo > 0");
					}
				}
			}
			if (trovato == false) {
				throw new InstanceNotFoundException("elemento da eliminare non trovato");
			}
		} else {
			throw new IllegalArgumentException("l'elemento da eliminare NON è un conto");
		}
	}

	@Override
	public DBDataModel saveDBAndClose() { // salva i dati sul database
		db.setAccounts(listaaccount);
		return db;
	}

}
