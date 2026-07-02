package controller.classes;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.util.Calendar;
import java.util.Iterator;
import java.util.ListIterator;
import java.util.NoSuchElementException;

import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

import model.classes.Discounts;
import model.classes.Exhibit;
import model.classes.SalesManagement;
import model.interfaces.IArtGallery;
import model.interfaces.IExhibit;
import view.classes.MainView;
import view.classes.TicketOfficeView;
import controller.interfaces.ITicketOfficeController;

/**
 * This is the controller of the {@link TicketOfficeView}.
 * @author Sofia Rosetti
 *
 */
public class TicketOfficeController implements ITicketOfficeController {

	private final MainView mainView;
	private final SalesManagement model;
	private final TicketOfficeView parentComponent;
	private final IArtGallery gallery;
	private final String path;
	private static final String[] PROPS = new String[] { "Esposizione", "Prezzo base (€)" };
	private static final String ERROR = "Errore";
	private final double temp = Math.pow(10, 2);
	
	/**
	 * Constructor.
	 * @param newMainView
	 * 					the MainView
	 * @param v
	 * 			the TicketOfficeView
	 * @param newGallery
	 * 			the ArtGallery
	 * @param newSales
	 * 			the SalesManagement
	 * @param newPath
	 * 			the file path
	 */
	public TicketOfficeController(final MainView newMainView, final TicketOfficeView v, 
			final IArtGallery newGallery, final SalesManagement newSales, final String newPath) {
		this.mainView = newMainView;
		this.model = newSales;
		this.parentComponent = v;
		this.gallery = newGallery;
		this.path = newPath;
		this.checkDate();
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
	public void addView(final TicketOfficeView v) {
		v.attachController(this);
		v.createTab();
	}
	
	@Override
	public void commandClose() {
		this.save();
		this.parentComponent.setVisible(false);
		this.mainView.setVisible(true);
	}
	
	@Override
	public void commandConfirm(final String ex, final String percentage, final String number, 
			final double price) {
		try {
			double percSelected = 0;
			for (final Discounts d: Discounts.values()) {
				if (percentage.equals(d.getDescription())) {
					percSelected = d.getPercentage();
				}
			}
			if (number.startsWith("-")) {
				throw new NumberFormatException();
			} else {
				final int tickNum = Integer.parseInt(number);
				final ListIterator<Exhibit> it = this.gallery.getExhibit().listIterator();
				while (it.hasNext()) {
					final Exhibit exb = it.next();
					if (exb.getTitleExhibit().equals(ex)) {
						if (tickNum > this.model.getAvailableTickets(exb)) {
							throw new IllegalArgumentException();
						}
						if (tickNum == 0) {
							throw new NumberFormatException();
						}
						final double temp = Math.pow(10, 2);						
						this.parentComponent.updateTotal(Math.round(this.model.purchase(exb, percSelected, tickNum, price) * temp) / temp);
					}
				}				
			}
		} catch (NumberFormatException e) {
			JOptionPane.showMessageDialog(this.parentComponent, "Inserisci un numero di biglietti maggiore di 0", 
					ERROR, JOptionPane.ERROR_MESSAGE);
		} catch (IllegalArgumentException e) {
			JOptionPane.showMessageDialog(this.parentComponent, "Non ci sono abbastanza biglietti disponibili" , 
					ERROR, JOptionPane.ERROR_MESSAGE);
		} catch (NoSuchElementException e) {
			JOptionPane.showMessageDialog(this.parentComponent, "Impossibile trovare l'esposizione" , 
					ERROR, JOptionPane.ERROR_MESSAGE);
		}
	}
	
	@Override
	public DefaultTableModel uploadExhibits() {
		final DefaultTableModel tm = new DefaultTableModel(new Object[][] {}, PROPS);
		final Iterator<IExhibit> it = this.model.getData().keySet().iterator();
		while (it.hasNext()) {
			final IExhibit ex = it.next();
			if (ex.getEnd().after(Calendar.getInstance())){
				tm.addRow(new Object[] {ex.getTitleExhibit(), Math.round(ex.getCostTicket() * temp) / temp});
			}
			
		}
		return tm;
	}
	
	/**
	 * This method checks if the date of the last purchase is different from the current date.
	 * If so, it calls the model method "resetTickets" in order to reset the number of 
	 * available tickets for the current day.
	 */
	private void checkDate() {
		final Calendar date = Calendar.getInstance();
		if (date.after(this.model.getLastDate()) && date.get(Calendar.DAY_OF_MONTH) 
				!= this.model.getLastDate().get(Calendar.DAY_OF_MONTH)) {
				this.model.resetTickets();
		}
	}
	
}
