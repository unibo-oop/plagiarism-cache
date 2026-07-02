/**
 * 
 */
package controller.movimenti;

import javax.management.InstanceNotFoundException;

import controller.IAnagraficaViewObserver;
import controller.main.MainControllerImpl;
import controller.popup.PopupControllerImpl;
import dataEnum.PopupMode;
import dataModel.DBDataModel;
import model.MovementsModel;
import view.movimenti.MovimentiView;

/**
 * implementazione del controller della sezione movimenti
 * 
 * @author Pentolo
 *
 */
public class MovimentiControllerImpl implements IAnagraficaViewObserver, IAnaMovimentiController {

	private final MovementsModel model;
	private final MovimentiView view;

	/**
	 * @param db
	 *            il database
	 * @param title
	 *            il titolo della finestra
	 */
	public MovimentiControllerImpl(final DBDataModel db, final String title) {
		this.model = new MovementsModel(db);
		this.view = new MovimentiView(model.load(), title);
		this.view.setObserver(this);
		view.start();
	}

	@Override
	public void chiusura() {
		view.close();
		new MainControllerImpl(model.saveDBAndClose());
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
