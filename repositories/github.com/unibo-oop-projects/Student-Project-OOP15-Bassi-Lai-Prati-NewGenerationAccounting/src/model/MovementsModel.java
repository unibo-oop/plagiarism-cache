package model;

import java.util.Date;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;

import javax.management.InstanceNotFoundException;

import dataEnum.Natures;
import dataEnum.Sections;
import dataModel.Account;
import dataModel.DBDataModel;
import dataModel.IDataTableModel;
import dataModel.Movement;
import dataModel.Operation;

/**
 * classe implementativa per la gestione dell'anagrafica dei movimenti
 * 
 * @author niky
 *
 */
public class MovementsModel implements ModelInterface {

	private final static String DATA = "Data Movimento";
	private final static String LISTA = "Lista Conti Mossi";
	private final static String DA = "Data da cui iniziare a cercare";
	private final static String A = "Data fino a cui cercare";
	private DBDataModel db;
	private LinkedList<Movement> listaMovimenti;

	public MovementsModel(DBDataModel db) {
		this.db = db;
		listaMovimenti = db.getMoviments();
	}

	@Override
	public void add(Map<String, Object> elem) throws InstanceNotFoundException {
		float totAvere = 0;
		float totDare = 0;
		// controllare qui e nella edit che il movimento abbia il saldo dare e
		// avere uguali
		// una riga del movimento non può avere dare e avere insieme
		if (!(elem.get(DATA) instanceof Date)) {
			throw new IllegalArgumentException("data inserita non valida");
		}
		if (!(elem.get(LISTA) instanceof LinkedList)) {
			throw new IllegalArgumentException("lista inserita non valida");
		}
		@SuppressWarnings("unchecked")
		Movement m = new Movement((Date) elem.get(DATA), (LinkedList<Operation>) elem.get(LISTA));
		// per ogni operazione in m.getList:
		// 1) dare e avere != 0 -> NO
		// 2) dare e avere ==0 -> NO
		// in m tot dare e tot avere devono essere uguali
		for (Operation op : m.getListaConti()) {
			if (op.getAvere() != 0) {
				if (op.getDare() == 0)
					totAvere = totAvere + op.getAvere();
				else
					throw new IllegalArgumentException("in un'operazione non possono esserci 2 valori > 0");
			} else if (op.getAvere() == 0) {
				if (op.getDare() != 0)
					totDare = totDare + op.getDare();
				else
					throw new IllegalArgumentException("in un'operazione non possono esserci 2 valori = 0");
			}
		}
		System.out.println(Float.toString(totDare));
		System.out.println(Float.toString(totAvere));
		if (totAvere != totDare) {
			throw new IllegalArgumentException("totale dare diverso da totale avere");
		}
		listaMovimenti.add(m);
		LinkedList<Account> accountList = db.getAccounts();
		for (Operation op : m.getListaConti()) {
			if (accountList.contains(op.getConto())) {
				for (Account a : accountList) {
					if (a == op.getConto()) {
						if (a.getNatura() == Natures.ATTIVITA || a.getNatura() == Natures.COSTO) {
							a.incrSaldo(op.getDare());
							a.decrSaldo(op.getAvere());
						} else if (a.getNatura() == Natures.PASSIVITA || a.getNatura() == Natures.RICAVO) {
							a.incrSaldo(op.getAvere());
							a.decrSaldo(op.getDare());
						}
					}
				}
			} else {
				throw new InstanceNotFoundException("il conto cercato non è presente in lista");
			}
		}
		db.setAccounts(accountList);
	}

	@Override
	public void edit(IDataTableModel obj, Map<String, Object> elemDaModificare)
			throws IllegalArgumentException, InstanceNotFoundException {
		// modifica un movimento e aggiorna i conti inerenti ad esso
		if (!(elemDaModificare.get(DATA) instanceof Date)) {
			throw new IllegalArgumentException("data non valida");
		} else if (!(elemDaModificare.get(LISTA) instanceof LinkedList)) {
			throw new IllegalArgumentException("lista non valida");
		}
		if (obj instanceof Movement) {
			if (listaMovimenti.contains(obj)) {
				for (Movement mov : listaMovimenti) {
					if (mov == obj) {
						remove(mov);
						add(elemDaModificare);
					}
				}
			} else {
				throw new InstanceNotFoundException("l'oggetto da modificare non è in lista");
			}
		} else {
			throw new IllegalArgumentException("l'oggetto passato come paramentro non è un movimento");
		}
	}

	public LinkedList<Account> getAllAccounts() {
		LinkedList<Account> accounts = db.getAccounts();
		accounts.add(0, new Account("", Natures.NESSUNO, Sections.NESSUNO, 0));
		return accounts;
	}

	@Override
	public Map<String, Object> getFilterMap() {
		Map<String, Object> mappaFiltro = new HashMap<>();
		mappaFiltro.put(DA, new Date());
		mappaFiltro.put(A, new Date());
		return mappaFiltro;
	}

	@Override
	public Map<String, Object> getMap(IDataTableModel obj) throws IllegalArgumentException {
		if (obj == null) {
			Map<String, Object> mappaVuota = new HashMap<>();
			mappaVuota.put(DATA, new Date());
			mappaVuota.put(LISTA, new LinkedList<Operation>());
			return mappaVuota;
		} else {
			if (obj instanceof Movement) {
				Map<String, Object> mappaPiena = new HashMap<>();
				mappaPiena.put(DATA, ((Movement) obj).getData());
				LinkedList<Operation> lista = new LinkedList<Operation>();
				for (Operation op : ((Movement) obj).getListaConti()) {
					lista.add(new Operation(op.getConto(), op.getDare(), op.getAvere()));
				}
				mappaPiena.put(LISTA, lista);
				System.out.println("sono la getMap" + mappaPiena);
				return mappaPiena;
			} else
				throw new IllegalArgumentException("l'oggetto inserito non è un movimento");
		}
	}

	@Override
	public LinkedList<Movement> load() {
		return listaMovimenti;
	}

	@Override
	public LinkedList<Movement> load(Map<String, Object> mappaFiltro) throws IllegalArgumentException {
		// carica dati utilizzando filtri
		LinkedList<Movement> listaFiltrata = new LinkedList<>();
		Date da = (Date) mappaFiltro.get(DA);
		Date a = (Date) mappaFiltro.get(A);
		if (da != null && a != null) {
			for (Movement m : listaMovimenti) {
				if (m.getData().after(da) && m.getData().before(a)) {
					listaFiltrata.add(m);
				}
			}
		} else if (da != null && a == null) {
			for (Movement m : listaMovimenti) {
				if (m.getData().after(da)) {
					listaFiltrata.add(m);
				}
			}
		} else if (da == null && a != null) {
			for (Movement m : listaMovimenti) {
				if (m.getData().before(a)) {
					listaFiltrata.add(m);
				}
			}
		} else if (da == null && a == null) {
			throw new IllegalArgumentException("le date inserite non sono valide");
		}

		return listaFiltrata;
	}

	@Override
	public void remove(IDataTableModel elemDaEliminare) throws InstanceNotFoundException, IllegalArgumentException {
		// rimuove movimento
		if (elemDaEliminare instanceof Movement) {
			Movement m = (Movement) elemDaEliminare;
			if (!(m.getData() instanceof Date) || m.getListaConti().isEmpty()) {
				throw new IllegalArgumentException("data non valida o lista vuota");
			} else {
				if (listaMovimenti.contains(m)) {
					listaMovimenti.remove(m);
					for (Operation op : m.getListaConti()) {
						for (Account acc : db.getAccounts()) {
							if (acc == op.getConto()) {
								if (acc.getNatura() == Natures.ATTIVITA || acc.getNatura() == Natures.COSTO) {
									if (op.getDare() > 0) {
										acc.decrSaldo(op.getDare());
									} else
										acc.incrSaldo(op.getAvere());
								} else if (acc.getNatura() == Natures.PASSIVITA || acc.getNatura() == Natures.RICAVO) {

									if (op.getDare() > 0) {
										acc.incrSaldo(op.getDare());
									} else
										acc.decrSaldo(op.getAvere());
								}
							}
						}
					}
				} else {
					throw new InstanceNotFoundException("elemento da eliminarenon è presente in lista");
				}
			}
		} else {
			throw new IllegalArgumentException("l'oggetto da rimuovere non è un movimento");
		}
	}

	@Override
	public DBDataModel saveDBAndClose() {
		// salva l'anagrafica movimenti sul DB
		db.setMoviments(listaMovimenti);
		return db;
	}
}
