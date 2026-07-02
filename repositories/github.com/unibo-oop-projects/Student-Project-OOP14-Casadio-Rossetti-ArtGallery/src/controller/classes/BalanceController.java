package controller.classes;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.util.Calendar;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.NoSuchElementException;

import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

import model.classes.Balance;
import model.classes.SalesManagement;
import model.interfaces.IExhibit;
import view.classes.BalanceView;
import view.classes.MainView;
import controller.interfaces.IBalanceController;

/**
 * This is the controller of the {@link BalanceView}.
 * @author Sofia Rosetti
 *
 */
public class BalanceController implements IBalanceController {

	private static final String ERROR = "Errore";
	private static final int FIRST_YEAR = 2002;
	private final int lastYear;
	private static final String[] PROPS = new String[] { "Esposizione", "Incasso (€)" };
	private final MainView mainView;
	private final Balance model;
	private final BalanceView parentComponent;
	private final SalesManagement salesData;
	private final Map<IExhibit, Double> map = new HashMap<IExhibit, Double>();
	private final String path;
	private final double temp = Math.pow(10, 2);
	
	/**
	 * Constructor.
	 * @param newMainView
	 * 					the newMainView
	 * @param v
	 * 			the BalanceView
	 * @param newSales
	 * 			the SalesManagement model
	 * @param newBalance
	 * 			the Balance model
	 * @param newPath
	 * 			the file path
	 */
	public BalanceController(final MainView newMainView, final BalanceView v, 
			final SalesManagement newSales, final Balance newBalance, final String newPath) {
		this.mainView = newMainView;
		this.model = newBalance;
		this.parentComponent = v;
		this.salesData = newSales;
		this.path = newPath;
		this.lastYear = Calendar.getInstance().get(Calendar.YEAR);
	}
	
	@Override
	public void save() {
		try {
			final ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream(this.path));
			out.writeObject(this.model);
			out.close();
		} catch (IOException ex) {
			JOptionPane.showMessageDialog(this.parentComponent, "Errore nel salvataggio", 
					ERROR, JOptionPane.ERROR_MESSAGE);
		}	
	}
	
	@Override
	public void addView(final BalanceView v) {
		v.attachController(this);
		v.createTab();
	}
	
	@Override
	public DefaultTableModel uploadTable() {
		final Iterator<IExhibit> it1 = this.salesData.getData().keySet().iterator();
		while (it1.hasNext()) {
			final Calendar date = Calendar.getInstance();
			final IExhibit ex = it1.next();
			if (date.get(Calendar.YEAR) == ex.getEnd().get(Calendar.YEAR)) {
				this.map.put(ex, this.salesData.getData().get(ex).getIncome());
			}		
		}
		
		final DefaultTableModel tm = new DefaultTableModel(new Object[][] {}, PROPS);
		final Iterator<IExhibit> it = this.map.keySet().iterator();
		while (it.hasNext()) {
			final IExhibit ex = it.next();
			tm.addRow(new Object[] {ex.getTitleExhibit(), Math.round(this.map.get(ex) * temp) / temp});
		}
		return tm;
	}
	
	@Override
	public double computeProfit() {
		return Math.round(this.model.profit(this.map) * temp) / temp;
	}
	
	@Override
	public void commandSearch(final String year) {
		try {
			if (year.startsWith("-")) {
				throw new NumberFormatException();
			} else {
				final int chosenYear = Integer.parseInt(year);
				if (chosenYear > this.lastYear - 1 || chosenYear < FIRST_YEAR) {
					throw new NumberFormatException();
				}
				this.parentComponent.updateTotal(Math.round(this.model.getPreviousIncome(chosenYear) * temp) / temp);
			}
		} catch (NoSuchElementException e) {
			JOptionPane.showMessageDialog(this.parentComponent, "L'anno " + year + " non è presente", 
					ERROR, JOptionPane.ERROR_MESSAGE);
		} catch (NumberFormatException e) {
			JOptionPane.showMessageDialog(this.parentComponent, "Inserisci un anno tra " + FIRST_YEAR 
					+ " e " + (this.lastYear - 1), ERROR, JOptionPane.ERROR_MESSAGE);
		}
		
	}
	
	@Override
	public void commandClose() {
		this.save();
		this.parentComponent.setVisible(false);
		this.mainView.setVisible(true);
	}
	
}
