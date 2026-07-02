/**
 * 
 */
package controller.creaFattura;

import javax.management.InstanceNotFoundException;

import controller.IAnagraficaViewObserver;
import controller.dbController.DBSaver;
import controller.main.MainControllerImpl;
import controller.popup.PopupControllerImpl;
import dataEnum.PopupMode;
import dataModel.Customers_Suppliers;
import dataModel.DBDataModel;
import model.CreaFattureModel;
import view.creaFattura.CreaFatturaView;

/**
 * implementazione controller finestra creazione fattura di acquisto
 * 
 * @author Pentolo
 *
 */
public class CreaFatturaControllerImpl implements IAnagraficaViewObserver, ICreaFatturaController {

	private final CreaFattureModel model;
	private final CreaFatturaView view;

	/**
	 * @param db
	 *            il database
	 * @param title
	 *            il titolo della finestra
	 */
	public CreaFatturaControllerImpl(final DBDataModel db, final String title) {
		this.model = new CreaFattureModel(db);
		this.view = new CreaFatturaView(model.load(), title, model.getListaclienti());
		this.view.setObserver(this);
		view.start();
	}

	@Override
	public void chiusura() {
		final DBDataModel db = model.saveDBAndClose();
		new DBSaver(db.getPath(), view, db).start();
		view.close();
		new MainControllerImpl(db);
	}

	/**
	 * crea la fattura
	 * 
	 * @param item
	 *            il cliente selezionato
	 */
	@Override
	public void create(Customers_Suppliers item) {
		final DBDataModel db = model.create(item);
		new DBSaver(db.getPath(), view, db).start();
		view.close();
		new MainControllerImpl(db);
	}

	@Override
	public void refresh() {
		view.setList(model.load());
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
			new PopupControllerImpl(PopupMode.ADD, model, this, view);
		} catch (InstanceNotFoundException | IllegalArgumentException e) {
			view.errorDialog("Errore", e.getMessage());
		}
	}

	@Override
	public void tasto2() {
		try {
			new PopupControllerImpl(PopupMode.EDIT, model, this, view);
		} catch (InstanceNotFoundException | IllegalArgumentException e) {
			view.errorDialog("Errore", e.getMessage());
		}
	}

	@Override
	public void tasto3() {
		try {
			model.remove(view.getSelectedItem());
		} catch (InstanceNotFoundException e) {
			view.errorDialog("Errore", e.getMessage());
		}
		refresh();
	}
}
