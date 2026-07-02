/**
 * 
 */
package controller.popup;

import java.awt.Dimension;
import java.util.Date;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;

import javax.management.InstanceNotFoundException;
import javax.swing.JComboBox;
import javax.swing.JComponent;
import javax.swing.JSpinner;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.TableModel;

import controller.IAnagraficaViewObserver;
import controller.IViewObserver;
import dataEnum.IDataEnum;
import dataEnum.PopupMode;
import dataModel.Account;
import dataModel.Operation;
import dataModel.Product;
import model.CreaFattureModel;
import model.ModelInterface;
import model.MovementsModel;
import view.AnagraficaView;
import view.popup.AddEditPopupView;

/**
 * implementazione del controller di AddEditPopupView
 * 
 * @author Pentolo
 *
 */
@SuppressWarnings("rawtypes")
public class PopupControllerImpl implements IViewObserver, IPopupController {

	private final ModelInterface model;
	private final IAnagraficaViewObserver parentController;
	private final AnagraficaView parentView;
	private final AddEditPopupView view;
	private final PopupMode mode;
	private final Map<String, Object> mappa;
	private Account emptyAccount;

	public PopupControllerImpl(final PopupMode mode, final ModelInterface model,
			final IAnagraficaViewObserver parentController, final AnagraficaView parentView)
			throws InstanceNotFoundException, IllegalArgumentException {
		this.mode = mode;
		this.model = model;
		this.parentController = parentController;
		this.parentView = parentView;
		String titolo;
		switch (mode) {
		case ADD:
			titolo = "Aggiungi";
			mappa = model.getMap(null);
			break;
		case EDIT:
			titolo = "Modifica";
			mappa = model.getMap(parentView.getSelectedItem());
			break;
		case FIND:
			titolo = "Filtra/Cerca";
			mappa = model.getFilterMap();
			break;
		default:
			throw new IllegalArgumentException("Modalita non consentita.");
		}
		final Dimension dim = new Dimension(350, 150 + 50 * mappa.size());
		view = new AddEditPopupView(titolo, dim, mappa, this);
		view.start();
		parentView.disableView();
	}

	protected void beforeCloseActions() {
		// DO NOTHING. THIS METHOD CAN BE OVERRIDEN FOR ACTIONS BEFORE CLOSE.
	}

	@Override
	public void chiusura() {
		beforeCloseActions();
		parentView.enableView();
		view.close();
	}

	@Override
	@SuppressWarnings("unchecked")
	public void filterList(final Map<String, Object> mappa) {
		try {
			parentView.setList(model.load(mappa));
		} catch (InstanceNotFoundException e) {
			view.errorDialog("errore", e.getMessage());
		}
	}

	@Override
	public LinkedList<Account> getAccountsList() {
		if (model instanceof MovementsModel) {
			LinkedList<Account> accounts = ((MovementsModel) model).getAllAccounts();
			emptyAccount = accounts.getFirst();
			return accounts;
		}
		return null;
	}

	@Override
	public LinkedList<Product> getProductsList() {
		if (model instanceof CreaFattureModel) {
			return ((CreaFattureModel) model).getAllProducts();
		}
		return null;
	}

	@Override
	public void go(final HashMap<String, JComponent> compoMap) {
		try {
			switch (mode) {
			case ADD:
				model.add(populateMap(compoMap));
				parentController.refresh();
				break;
			case EDIT:
				model.edit(parentView.getSelectedItem(), populateMap(compoMap));
				parentController.refresh();
				break;
			case FIND:
				filterList(populateMap(compoMap));
				break;
			}
			chiusura();
		} catch (Exception e) {
			view.errorDialog("errore", e.getMessage());
		}
	}

	@SuppressWarnings("unchecked")
	@Override
	public Map<String, Object> populateMap(final HashMap<String, JComponent> compoMap) {
		Map<String, Object> map = new HashMap<String, Object>();
		for (String key : mappa.keySet()) {
			JComponent field = compoMap.get(key);
			Object defaultValue = mappa.get(key);
			if (defaultValue instanceof String && field instanceof JTextField) {
				map.put(key, ((JTextField) field).getText());
			} else if (defaultValue instanceof char[] && field instanceof JTextField) {
				map.put(key, ((JTextField) field).getText().toCharArray());
			} else if (defaultValue instanceof Date && field instanceof JSpinner) {
				map.put(key, ((JSpinner) field).getValue());
			} else if (defaultValue instanceof Number && field instanceof JSpinner) {
				final Number numero = (Number) ((JSpinner) field).getValue();
				if (defaultValue instanceof Float) {
					map.put(key, numero.floatValue());
				} else if (defaultValue instanceof Double) {
					map.put(key, numero.doubleValue());
				} else if (defaultValue instanceof Long) {
					map.put(key, numero.longValue());
				} else {
					map.put(key, numero.intValue());
				}
			} else if (defaultValue instanceof Enum && defaultValue instanceof IDataEnum
					&& field instanceof JComboBox) {
				map.put(key, ((JComboBox<?>) field).getSelectedItem());
			} else if (defaultValue instanceof Product && field instanceof JComboBox) {
				map.put(key, ((JComboBox<Product>) field).getSelectedItem());
			} else if (defaultValue instanceof LinkedList && field instanceof JTable) {
				final LinkedList<Operation> operations = new LinkedList<Operation>();
				final TableModel table = ((JTable) field).getModel();
				for (int i = 0; i < table.getRowCount(); i++) {
					if (Operation.getColumnClass(0) == table.getValueAt(i, 0).getClass()
							&& Operation.getColumnClass(1) == table.getValueAt(i, 1).getClass()
							&& Operation.getColumnClass(2) == table.getValueAt(i, 2).getClass()
							&& !table.getValueAt(i, 0).equals(emptyAccount)) {
						operations.add(new Operation((Account) table.getValueAt(i, 0), (float) table.getValueAt(i, 1),
								(float) table.getValueAt(i, 2)));
					}
				}
				map.put(key, operations);
			} else {
				throw new IllegalArgumentException(
						"Errore di conversione del dato " + key + " correggere e riprovare.");
			}
		}
		return map;
	}
}
