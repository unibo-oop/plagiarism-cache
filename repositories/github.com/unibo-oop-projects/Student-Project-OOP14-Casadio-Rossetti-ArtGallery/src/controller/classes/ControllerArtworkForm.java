package controller.classes;

import java.time.LocalDate;

import javax.swing.JFrame;
import javax.swing.JOptionPane;

import model.classes.Artwork;
import model.interfaces.IArtGallery;
import view.classes.ArtworkForm;
import view.interfaces.IArtworkForm;
import view.interfaces.IArtworkView;
import controller.interfaces.IControllerArtworkForm;
import exceptions.DatingNotExistException;

/**
 * The controller for the {@link ArtworkForm}.
 * @author Elisa Casadio
 *
 */

public class ControllerArtworkForm implements IControllerArtworkForm {

	private static final double MIN_DIM = 0.1;
	private static final double MAX_HEIGHT = 350;
	private static final double MAX_DIM = 500;
	private static final String ERROR = "ERRORE";
	private static final String COMPILE_FIELD = "Compilare tutti i campi"
			+ "\n(\"Descrizione\" è facoltativo).";
	private static final String COMPILE_DATING = "Selezionare la datazione"
			+ " dell'anno desiderato.";
	private static final String WRONG_DATE_AC = "Datazione non accettabile."
			+ "\nL'anno 0 è considerato Dopo Cristo (D.C.).";
	private static final String WRONG_DATE_DC = "Datazione non accettabile."
			+ "\nL'anno scelto non fa parte della datazione Dopo Cristo (D.C.)."
			+ "\nSelezionare un anno compreso tra 0 e "
			+ LocalDate.now().getYear() + ".";
	private static final String WRONG_HEIGHT = "Altezza non accettabile."
			+ "\nInserire una misura compresa tra " + MIN_DIM + " cm e " 
			+ MAX_HEIGHT + " cm.";
	private static final String WRONG_WIDTH = "Larghezza non accettabile."
			+ "\nInserire una misura compresa tra " + MIN_DIM + " cm e " 
			+ MAX_DIM + " cm.";
	private static final String WRONG_DEPTH = "Profondità non accettabile."
			+ "\nInserire una misura compresa tra " + MIN_DIM + " cm e " 
			+ MAX_DIM + " cm.";
	private static final String MUL = " x ";
	private static final String AC = " A.C.";
	private static final String DC = " D.C.";
	
	private final IArtGallery model;
	private final JFrame artworkFrame;
	private final IArtworkView viewArtwork;
	private final int edit;
	private final boolean show;
	
	/**
	 * Constructor.
	 * @param newModel
	 * 			the model.
	 * @param newView
	 * 			the view.
	 * @param frame
	 * 			the main frame in which the view refers to.
	 * @param newViewArtwork
	 * 			the artwork's view.
	 * @param editValue
	 * 			the artwork's position if the artwork's informations are to be
	 * 			changed or -1 if it is a new artwork.
	 * @param isShow
	 * 			true if the artwork's informations must be shown otherwise false.
	 */
	public ControllerArtworkForm(final IArtGallery newModel,
			final IArtworkForm newView, final JFrame frame, 
			final IArtworkView newViewArtwork, final int editValue,
			final boolean isShow) {
		this.model = newModel;
		final IArtworkForm view = newView;
		view.attachController(this);
		this.artworkFrame = frame;
		this.viewArtwork = newViewArtwork;
		this.edit = editValue;
		this.show = isShow;
	}
	
	@Override
	public void commandConfirm(final Long code, final String title, 
			final String author, final boolean isAC, final boolean isDC,
			final int year, final boolean isPaint, final String artD,
			final String techn, final double height, final double width,
			final double depth, final String descr, final ArtworkForm form) {
		
		try {
			if (title.isEmpty() || author.isEmpty() || artD == null) {
				throw new IllegalArgumentException(COMPILE_FIELD);
			}
			if (!isAC && !isDC) {
				throw new IllegalArgumentException(COMPILE_DATING);
			}
			if (isAC && year == 0) {
				throw new DatingNotExistException(WRONG_DATE_AC);
			}
			if (isDC && year > LocalDate.now().getYear()) {
				throw new DatingNotExistException(WRONG_DATE_DC);
			}
			if (height < MIN_DIM || height > MAX_HEIGHT) {
				throw new IllegalArgumentException(WRONG_HEIGHT);
			}
			if (width < MIN_DIM || width > MAX_DIM) {
				throw new IllegalArgumentException(WRONG_WIDTH);
			}
			if (!isPaint && depth < MIN_DIM || !isPaint && depth > MAX_DIM) {
				throw new IllegalArgumentException(WRONG_DEPTH);
			} else {
				if (this.edit == -1 && !this.show) {
					if (isAC) {
						this.updaterArt(false, code, title, author, year, -1,
								artD, techn, height, width, depth, descr, AC);
					} else {
						this.updaterArt(false, code, title, author, year, 1,
								artD, techn, height, width, depth, descr, DC);
					}
				}
				if (this.edit != -1 && !this.show) {
					if (isAC) {
						this.updaterArt(true, code, title, author, year, -1,
								artD, techn, height, width, depth, descr, AC);
					} else {
						this.updaterArt(true, code, title, author, year, 1,
								artD, techn, height, width, depth, descr, DC);
					}
				}
				form.setVisible(false);
			}
		} catch (DatingNotExistException | IllegalArgumentException e) {
			JOptionPane.showMessageDialog(this.artworkFrame, e.getMessage(),
					ERROR, JOptionPane.ERROR_MESSAGE);
		}
		
	}
	
	/**
	 * Allows to add or to edit an element to the model and the view.
	 * 
	 * @param isEdit
	 * 			true if the user change the data of artwork otherwise false. 
	 * @param title
	 * 			the artwork's title.
	 * @param author
	 * 			the artwork's author.
	 * @param year
	 * 			the year of the artwork.
	 * @param isAC
	 * 			-1 if the dating year is A.C. otherwise 1.
	 * @param artD
	 * 			the type of artistic discipline of the artwork.
	 * @param techn
	 * 			the painting technique or material of the artwork.
	 * @param height
	 * 			the height of artwork.
	 * @param width
	 * 			the width of artwork.
	 * @param depth
	 * 			the depth of artwork.
	 * @param descr
	 * 			the description of artwork.
	 * @param dating
	 * 			the type of dating year (A.C. or D.C.)
	 */
	private void updaterArt(final boolean isEdit, final Long code,
			final String title, final String author, final int year,
			final int isAC, final String artD, final String techn,
			final double height, final double width, final double depth,
			final String descr, final String dating) {
		if (isEdit) {
			this.model.getArtwork().set(this.edit, new Artwork(code, title,
					author, year * isAC, artD, techn, height, width, depth,
					descr));
			this.viewArtwork.editData(this.edit, new Object[] {code, title,
					author, year + dating, artD, techn,
					height + MUL + width + MUL + depth});
		} else {
			this.model.addArtwork(new Artwork(code, title, author, year * isAC,
					artD, techn, height, width, depth, descr));
			this.viewArtwork.addData(new Object[] {code, title, author, 
					year + dating, artD, techn, 
					height + MUL + width + MUL + depth});
		}
	}	

}
