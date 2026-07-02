/**
 * 
 */
package controller.anaConti;

import javax.management.InstanceNotFoundException;

import controller.IAnagraficaViewObserver;
import controller.dbController.DBSaver;
import controller.main.MainControllerImpl;
import controller.popup.PopupControllerImpl;
import dataEnum.PopupMode;
import dataModel.Account;
import dataModel.DBDataModel;
import model.AccountsModel;
import view.AnagraficaView;

/**
 * implementazione controller anagrafica conti
 * 
 * @author Pentolo
 *
 */
public class AnaContiControllerImpl implements IAnagraficaViewObserver, IAnaContiController {
	private final AccountsModel model;
	private final AnagraficaView<Account> view;

	/**
	 * @param db
	 *            il database
	 * @param title
	 *            il titolo della finestra
	 */
	public AnaContiControllerImpl(final DBDataModel db, final String title) {
		this.model = new AccountsModel(db);
		this.view = new AnagraficaView<Account>(model.load(), Account.getIntestazione(), title);
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
