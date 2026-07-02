package controller.classes;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import javax.swing.JFrame;
import javax.swing.JOptionPane;

import model.classes.Exhibit;
import model.interfaces.IArtGallery;
import controller.interfaces.IControllerExhibitForm;
import view.classes.ExhibitForm;
import view.interfaces.IExhibitForm;
import view.interfaces.IExhibitView;

/**
 * The controller for the {@link ExhibitForm}.
 * @author Elisa Casadio
 *
 */

public class ControllerExhibitForm implements IControllerExhibitForm {

	private static final double MIN_COST_EXHIBIT = 100;
	private static final double MAX_COST_EXHIBIT = 5000000;
	private static final double MIN_COST_TICKET = 1;
	private static final double MAX_COST_TICKET = 100;
	private static final String ERROR = "ERRORE";
	private static final String COMPILE_FIELD = "Compilare tutti i"
			+ " campi.";
	private static final String WRONG_DATE_END = "Data di fine mostra non "
			+ "accettabile.\nInserire una data che non sia "
			+ "antecedente a quella di inizio mostra.";
	private static final String WRONG_DATE_BEG = "Data di inizio mostra non "
			+ "accettabile.\nInserire una data che non sia antecedente a "
			+ "quella odierna.";
	private static final String WRONG_COST_EX = "Costo mostra non accettabile."
			+ "\nInserire un costo compreso tra € " + MIN_COST_EXHIBIT
			+ "0 e € " + MAX_COST_EXHIBIT + "0.";
	private static final String WRONG_COST_TICKET = "Costo biglietto non "
			+ "accettabile.\nInserire un costo compreso tra € "
			+ MIN_COST_TICKET + "0 e € " + MAX_COST_TICKET + "0.";
	
	private final IArtGallery model;
	private final JFrame mainFrame;
	private final IExhibitView viewArtG;
	private final int edit;
	
	/**
	 * Constructor.
	 * @param newModel
	 * 			the model.
	 * @param newView
	 * 			the view.
	 * @param frame
	 * 			the main frame.
	 * @param newViewArtG
	 * 			the artgallery's view.
	 * @param isEdit
	 * 			the exhibit's position if the exhibit's informations are to be
	 *          changed or -1 if it is a new exhibit.
	 */
	public ControllerExhibitForm(final IArtGallery newModel, 
			final IExhibitForm newView, final JFrame frame, 
			final IExhibitView newViewArtG, final int isEdit) {
		this.model = newModel;
		final IExhibitForm view = newView;
		view.attachController(this);
		this.mainFrame = frame;
		this.viewArtG = newViewArtG;
		this.edit = isEdit;
	}
	
	@Override
	public void commandConfirm(final Long code, final String title, 
			final String curator, final Calendar dateB, final Calendar dateE,
			final double costEx, final double costTicket, 
			final ExhibitForm form) {
		
		try {
			if (title.isEmpty() || curator.isEmpty() || dateB == null 
					|| dateE == null) {
				throw new IllegalArgumentException(COMPILE_FIELD);
			}
			if (dateE.before(dateB)) {
				throw new IllegalArgumentException(WRONG_DATE_END);
			}
			final Calendar yesterday = Calendar.getInstance();
			yesterday.add(Calendar.DATE, -1);
			if (dateB.before(yesterday)) {
				throw new IllegalArgumentException(WRONG_DATE_BEG);
			}
			if (costEx < MIN_COST_EXHIBIT || costEx > MAX_COST_EXHIBIT) {
				throw new IllegalArgumentException(WRONG_COST_EX);
			}
			if (costTicket < MIN_COST_TICKET || costTicket > MAX_COST_TICKET) {
				throw new IllegalArgumentException(WRONG_COST_TICKET);
			} else {
				if (this.edit == -1) {
					this.model.addExhibit(new Exhibit(code, title, curator, 
							dateB, dateE, new ArrayList<>(), costEx, costTicket));
					final int index = this.model.getExhibit().size() - 1;
					this.viewArtG.addData(code, title, curator, dateB, dateE, 
							this.model.getExhibit().get(index).getNumPieces(),
							costEx, costTicket);
				} else {
					final List<Long> artworks = this.model.getExhibit()
							.get(this.edit).getArtworks();
					this.model.getExhibit().set(this.edit, new Exhibit(code, 
							title, curator, dateB, dateE, artworks, costEx, costTicket));
					this.viewArtG.editData(this.edit, code, title, curator, 
							dateB, dateE, this.model.getExhibit().get(this.edit)
							.getNumPieces(), costEx, costTicket);
				}				
				form.setVisible(false);				
			}
		} catch (IllegalArgumentException e) {
			JOptionPane.showMessageDialog(this.mainFrame, e.getMessage(), ERROR,
					JOptionPane.ERROR_MESSAGE);
		}
	}
	
}
