package controller.classes;

import java.util.Calendar;

import javax.swing.JFrame;
import javax.swing.JOptionPane;

import model.interfaces.IArtGallery;
import view.classes.ManageArtworkExhibit;
import view.interfaces.IExhibitView;
import view.interfaces.IManageArtworkExhibit;
import controller.interfaces.IControllerManageArtworkExhibit;
import exceptions.IllegalOperationException;

/**
 * The controller for the {@link ManageArtworkExhibit}.
 * @author Elisa Casadio
 *
 */

public class ControllerManageArtworkExhibit implements IControllerManageArtworkExhibit {

	private static final String ADD_IMPOSSIBLE = "Impossibile aggiungere una "
			+ "nuova opera d'arte alla mostra, perchè già terminata.";
	private static final String ARTWORK_PRESENT = "Opera d'arte selezionata già"
			+ " inserita nell'elenco.";
	private static final String DELETE_IMPOSSIBLE = "Impossibile cancellare "
			+ "l'opera d'arte dalla mostra, perchè già terminata.";
	
	private final IArtGallery model;
	private final int indexEx;
	private final IManageArtworkExhibit view;
	private final JFrame frameEx;
	private final IExhibitView viewEx;
	
	/**
	 * Constructor.
	 * 
	 * @param newModel
	 * 			the model.
	 * @param newIndexEx
	 * 			the index of exhibit which the view refers to.
	 * @param newView
	 * 			the view.
	 * @param frame
	 * 			the main frame.
	 * @param newViewEx
	 * 			the view of the exhibit.
	 */
	public ControllerManageArtworkExhibit(final IArtGallery newModel, 
			final int newIndexEx, final IManageArtworkExhibit newView,
			final JFrame frame, final IExhibitView newViewEx) {
		this.model = newModel;
		this.indexEx = newIndexEx;
		this.view = newView;
		this.view.attachController(this);
		this.frameEx = frame;
		this.viewEx = newViewEx;
	}
	
	@Override
	public void commandAdd(final int index) {
		try {
			if (this.model.getExhibit().get(this.indexEx).getEnd()
					.before(Calendar.getInstance())) {
				throw new IllegalOperationException(ADD_IMPOSSIBLE);
			}
			for (final Long art : this.model.getExhibit().get(this.indexEx)
					.getArtworks()) {
				if (Long.compare(art, this.model.getArtwork().get(index)
						.getCode()) == 0) {
					throw new IllegalOperationException(ARTWORK_PRESENT);
				}
			}
			this.model.getExhibit().get(this.indexEx).addArtwork(
					this.model.getArtwork().get(index).getCode());
			this.view.addData(false, this.model.getArtwork().get(index).getCode(),
					this.model.getArtwork().get(index).getTitle(),
					this.model.getArtwork().get(index).getAuthor(), 
					this.model.getArtwork().get(index).getArtisticDiscipline(),
					this.model.getArtwork().get(index).getTechnique());
			this.viewEx.editNPiecesCell(this.model.getExhibit().get(this.indexEx)
					.getNumPieces(), this.indexEx);
		} catch (IllegalOperationException e) {
			JOptionPane.showMessageDialog(this.frameEx, e.getMessage(),
					"ERRORE", JOptionPane.ERROR_MESSAGE);
		}
	}
	
	@Override
	public void commandDelete(final int index) {
		try {
			if (this.model.getExhibit().get(this.indexEx).getEnd()
					.before(Calendar.getInstance())) {
				throw new IllegalOperationException(DELETE_IMPOSSIBLE);
			}
			this.view.clearData(index);
			this.model.getExhibit().get(this.indexEx).getArtworks().remove(index);
			this.viewEx.editNPiecesCell(this.model.getExhibit()
					.get(this.indexEx).getNumPieces(), this.indexEx);
		} catch (IllegalOperationException e) {
			JOptionPane.showMessageDialog(this.frameEx, e.getMessage(),
					"ERRORE", JOptionPane.ERROR_MESSAGE);
		}
	}

}
