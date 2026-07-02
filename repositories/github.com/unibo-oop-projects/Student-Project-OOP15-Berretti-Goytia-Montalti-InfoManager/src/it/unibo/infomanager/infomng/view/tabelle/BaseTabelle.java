package it.unibo.infomanager.infomng.view.tabelle;

import javax.swing.table.AbstractTableModel;

import it.unibo.infomanager.infomng.controller.Navigator;
import it.unibo.infomanager.infomng.controller.ObserverInterfaceImpl;
import it.unibo.infomanager.infomng.view.interfaces.ObserverInterface;

/**
 * Classe che definisce il modello di JTable.
 * 
 * @author Alessandro
 *
 */
public class BaseTabelle extends AbstractTableModel {
	/**
	 * 
	 */
	private static final long serialVersionUID = -6283359396361049566L;
	private ObserverInterface o = new ObserverInterfaceImpl(null);
	private Navigator<?> l = Navigator.creatNavigator(o.listOfProducts());

	/**
	 * 
	 */

	@Override
	public int getColumnCount() {
		return 3;
	}

	@Override
	public int getRowCount() {
		return o.quantitaProdotti();
	}

	@Override
	public Object getValueAt(final int row, final int col) {
		return new String(l.avanti().toString());
	}
/**
 * Metodo per ottenere il nome della colonna.
 * @param col
 * 			numero colonna
 * @return
 * 			nome colonna
 */
	public String getColumnName(final int col) {
		if (col == 0) {
			return new String("Prodotto");
		}
		if (col == 1) {
			return new String("Quantita'");
		}
		if (col == 2) {
			return new String("Prezzo");
		}
		return new String("");
	}

}
