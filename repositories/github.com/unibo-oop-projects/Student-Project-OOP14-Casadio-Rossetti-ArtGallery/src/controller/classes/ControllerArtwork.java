package controller.classes;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;

import javax.swing.JFrame;
import javax.swing.JOptionPane;

import model.classes.ArtGallery;
import model.classes.Exhibit;
import view.classes.MainView;
import view.classes.ArtworkForm;
import view.classes.ArtworkView;
import view.interfaces.IArtworkView;
import controller.interfaces.IControllerArtwork;
import exceptions.IllegalOperationException;

/**
 * The controller for the {@link ArtworkView}.
 * @author Elisa Casadio
 *
 */

public class ControllerArtwork implements IControllerArtwork {

	private static final String DELETE = "ELIMINA";
	private static final String ERROR = "ERRORE";
	private static final String CONFIRM_DELETE = "Sei sicuro di voler eliminare"
			+ " questa opera d'arte?";
	private static final String DELETE_IMPOSSIBLE = "Impossibile cancellare questa opera d'arte, "
			+ "perchè usata in almeno una esposizione.";
	private static final String SAVE_ERROR = "Errore nel salvataggio del file.";
	
	private final ArtGallery model;
	private final IArtworkView view;
	private final MainView mainView;
	private final String path;
	
	/**
	 * Constructor.
	 * 
	 * @param newModel
	 * 			the model.
	 * @param newView
	 * 			the view.
	 * @param newMainView
	 * 			the view of the home of the program.
	 * @param newPath
	 * 			the data path where the model is saved.
	 */
	public ControllerArtwork(final ArtGallery newModel, 
			final IArtworkView newView, final MainView newMainView, 
			final String newPath) {
		this.model = newModel;
		this.view = newView;
		this.view.attachController(this);
		this.mainView = newMainView;
		this.path = newPath;
	}
	
	@Override
	public void commandNew(final JFrame frame) {
		ArtworkForm artwork = new ArtworkForm(frame, 1L);
		if (this.model.getArtwork().size() != 0) {
			artwork = new ArtworkForm(frame, this.model.getArtwork().get(
							this.model.getArtwork().size() - 1).getCode() + 1);
		}
		artwork.reinit();
		artwork.setVisible(true);
		new ControllerArtworkForm(this.model, artwork, frame, this.view,
				-1, false);
	}
	
	@Override
	public void commandEdit(final int index, final JFrame frame) {
		final ArtworkForm artwork = new ArtworkForm(frame, 
				this.model.getArtwork().get(index).getCode());
		artwork.setForm(index, this.model);
		artwork.setVisible(true);
		new ControllerArtworkForm(this.model, artwork, frame, this.view,
				index, false);
	}
	
	@Override
	public void commandShow(final int index, final JFrame frame) {
		final ArtworkForm artwork = new ArtworkForm(frame, 
				this.model.getArtwork().get(index).getCode());
		artwork.setForm(index, this.model);
		artwork.setVisible(true);
		new ControllerArtworkForm(this.model, artwork, frame, this.view,
				-1, true);
	}
	
	@Override
	public void commandDelete(final int index, final JFrame frame) {
		try {
			for (final Exhibit ex : this.model.getExhibit()) {
				for (final Long code : ex.getArtworks()) {
					if (Long.compare(code, 
							this.model.getArtwork().get(index).getCode()) == 0) {
						throw new IllegalOperationException(DELETE_IMPOSSIBLE);
					}
				}
			}
			
			final int n = JOptionPane.showConfirmDialog(frame, CONFIRM_DELETE,
					DELETE, JOptionPane.YES_NO_OPTION);
			if (n == JOptionPane.YES_OPTION) {
				this.view.clearData(index);
				this.model.getArtwork().remove(index);
			}
		} catch (IllegalOperationException e) {
			JOptionPane.showMessageDialog(frame, e.getMessage(), ERROR,
					JOptionPane.ERROR_MESSAGE);
		}
	}
	
	@Override
	public void commandClose(final JFrame frame) {
		try {
			final ObjectOutputStream out = new ObjectOutputStream(
					new FileOutputStream(this.path));
			out.writeObject(this.model);
			out.close();
			frame.setVisible(false);
			this.mainView.setVisible(true);
		} catch (IOException e) {
			JOptionPane.showMessageDialog(frame, SAVE_ERROR, ERROR,
					JOptionPane.ERROR_MESSAGE);
		}
	}

}
