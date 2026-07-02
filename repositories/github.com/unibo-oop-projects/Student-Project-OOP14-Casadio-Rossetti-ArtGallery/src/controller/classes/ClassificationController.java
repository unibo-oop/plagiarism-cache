package controller.classes;

import java.util.HashMap;
import java.util.Iterator;
import java.util.ListIterator;
import java.util.Map;
import java.util.Map.Entry;

import javax.swing.table.DefaultTableModel;

import controller.interfaces.IClassificationController;
import view.classes.ClassificationView;
import view.classes.MainView;
import model.classes.Classification;
import model.classes.SalesManagement;
import model.interfaces.IExhibit;

/**
 * This is the controller of the {@link ClassificationView}.
 * @author Sofia Rosetti
 *
 */
public class ClassificationController implements IClassificationController {
	
	private static final String[] PROPS = new String[] { "Esposizione", "Incasso (€)" };
	private final MainView mainView;
	private final Classification model;
	private final SalesManagement salesData;
	private final Map<IExhibit, Double> map = new HashMap<IExhibit, Double>();
	private final ClassificationView parentComponent;
	private final double temp = Math.pow(10, 2);
	
	/**
	 * Constructor.
	 * @param newMainView
	 * 					the MainView
	 * @param v
	 * 			the ClassificationView
	 * @param newSales
	 * 			the SalesModel
	 */
	public ClassificationController(final MainView newMainView, final ClassificationView v, 
			final SalesManagement newSales) {
		this.mainView = newMainView;
		this.model = new Classification();
		this.parentComponent = v;
		this.salesData = newSales;
	}
	
	@Override
	public void addView(final ClassificationView v) {
		v.attachController(this);
		v.createTab();
	}
	
	@Override
	public void createClassificationStructure() {
		final Iterator<IExhibit> it = this.salesData.getData().keySet().iterator();
		while (it.hasNext()) {
			final IExhibit ex = it.next();
			this.map.put(ex, this.salesData.getData().get(ex).getIncome());
		}
		this.model.sortMap(map);
	}
	
	@Override
	public DefaultTableModel uploadClassification() {
		final DefaultTableModel tm = new DefaultTableModel(new Object[][] {}, PROPS);
		final ListIterator<Entry<IExhibit, Double>> it = this.model.getList().listIterator();
		while (it.hasNext()) {
			final Entry<IExhibit, Double> e = it.next();
			tm.addRow(new Object[] {e.getKey().getTitleExhibit(), Math.round(e.getValue() * temp) / temp});
		}
		return tm;
	}
	
	@Override
	public void commandClose() {
		this.parentComponent.setVisible(false);
		this.mainView.setVisible(true);
	}
	
}
