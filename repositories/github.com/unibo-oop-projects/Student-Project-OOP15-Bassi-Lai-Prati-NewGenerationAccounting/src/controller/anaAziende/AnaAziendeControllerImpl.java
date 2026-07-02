package controller.anaAziende;

import java.io.IOException;
import java.util.LinkedList;
import java.util.UUID;

import javax.management.InstanceNotFoundException;

import controller.IAnagraficaViewObserver;
import controller.dbController.DBLoader;
import controller.dbController.DBSaver;
import controller.main.MainControllerImpl;
import controller.popup.PopupControllerImpl;
import dataEnum.PopupMode;
import dataModel.Company;
import model.CompanyModel;
import view.anaAziende.AnaAziendeView;

/**
 * implementazione del controller della anagrafica aziende
 * 
 * @author Pentolo
 *
 */
public class AnaAziendeControllerImpl implements IAnagraficaViewObserver, IAnaAziendeController {
	private final AnaAziendeView view;
	private final CompanyModel model;

	/**
	 * @param linkedList
	 *            lista delle aziende
	 */
	public AnaAziendeControllerImpl(final LinkedList<Company> linkedList) {
		this.model = new CompanyModel(linkedList);
		this.view = new AnaAziendeView(model.load());
		this.view.setObserver(this);
		view.start();
	}

	@Override
	public void accedi() {
		final Company company = getSelectedCompany();
		if (company != null) {
			if (checkPwd(company)) {
				saveCompanysList();
				view.close();
				new MainControllerImpl(DBLoader.loadDB(company.getCodice_azienda().toString(), view));
			} else {
				wrongPwd();
			}
		}
	}

	/**
	 * metodo del controller che controlla che la password inserita sia corretta
	 * 
	 * @param company
	 *            l'azienda selezionata
	 * @return booleano true se la password è corretta. altrimenti false
	 */
	private boolean checkPwd(final Company company) {
		return model.isPasswordCorrect(view.getInputPassword(), company);
	}

	@Override
	public void chiusura() {
		if (view.confirmDialog("Sei sicuro di voler uscire dal programma?", "Uscire")) {
			saveCompanysList();
			System.exit(0);
		}
	}

	/**
	 * ritorna l'azienda selezionata nella JTable. se nessuna è selezionata apre
	 * una finestra di dialogo con l'errore
	 * 
	 * @return azienda selezionata
	 */
	private Company getSelectedCompany() {
		Company company = null;
		try {
			company = (Company) view.getSelectedItem();
		} catch (InstanceNotFoundException e) {
			view.errorDialog("Errore", e.getMessage());
		}
		return company;
	}

	@Override
	public void refresh() {
		view.setList(model.load());
	}

	/**
	 * salva su disco la lista aziende
	 */
	private void saveCompanysList() {
		try {
			DBSaver.saveCompanys(model.saveCompanysAndClose());
		} catch (IOException e) {
			view.errorDialog("errore", e.getMessage());
		}
	}

	@Override
	public void tasto0() {
		try {
			new PopupControllerImpl(PopupMode.FIND, model, this, view);
		} catch (InstanceNotFoundException | IllegalArgumentException e) {
			view.errorDialog("Errore", e.getMessage());
		}
	}

	@Override
	public void tasto1() {
		try {
			new PopupControllerImpl(PopupMode.ADD, model, this, view) {
				@Override
				protected void beforeCloseActions() {
					final UUID codice = model.getLastAddedItemCode();
					if (codice != null) {
						DBSaver.addCompany(codice);
					}
				}
			};
		} catch (InstanceNotFoundException | IllegalArgumentException e) {
			view.errorDialog("Errore", e.getMessage());
		}
	}

	@Override
	public void tasto2() {
		final Company company = getSelectedCompany();
		if (company != null) {
			if (checkPwd(company)) {
				try {
					new PopupControllerImpl(PopupMode.EDIT, model, this, view);
				} catch (InstanceNotFoundException | IllegalArgumentException e) {
					view.errorDialog("Errore", e.getMessage());
				}
			} else {
				wrongPwd();
			}
		}
	}

	@Override
	public void tasto3() {
		final Company company = getSelectedCompany();
		if (company != null) {
			if (checkPwd(company)) {
				DBSaver.removeCompany(company.getCodice_azienda().toString());
				model.remove(company);
				refresh();
			} else {
				wrongPwd();
			}
		}
	}

	/**
	 * metodo che apre una finestra di dialogo in cui visualizza un messaggio di
	 * errore nel caso di password errata
	 */
	private void wrongPwd() {
		view.errorDialog("Password Errata", "Attenzione! La password inserita è errata. riprovare.");
	}

}