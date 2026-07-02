package controller.classes;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.util.Calendar;
import java.util.ListIterator;

import javax.swing.JOptionPane;

import model.classes.ArtGallery;
import model.classes.Balance;
import model.classes.Exhibit;
import model.classes.SalesManagement;
import model.interfaces.IArtwork;
import model.interfaces.IExhibit;
import view.classes.ArtworkView;
import view.classes.BalanceView;
import view.classes.ClassificationView;
import view.classes.ExhibitView;
import view.classes.MainView;
import view.classes.TicketOfficeView;
import view.interfaces.IArtworkView;
import view.interfaces.IExhibitView;
import view.interfaces.IMainView;
import controller.interfaces.IMainViewController;

/**
 * This is the controller of the {@link MainView}.
 * @author Sofia Rosetti
 * @author Elisa Casadio
 *
 */

public class MainViewController implements IMainViewController {

	private static final String ARCHIVE_FILE = System.getProperty("file.separator")
			+ "Archive.agm";
	private static final String SALES_FILE = System.getProperty("file.separator") 
			+ "SalesManagement.agm";
	private static final String BALANCE_FILE = System.getProperty("file.separator") 
			+ "Balance.agm";
	private static final String ERROR = "ERRORE";
	private static final String LOAD_ERROR = "Errore nel caricamento.";
	private static final String OPEN_EX_ERROR = "Impossibile vedere le "
			+ "esposizioni, perchè non ci sono opere d'arte nell'archivio.";
	
	private final MainView view;
	private final String path;
	
	/**
	 * Constructor.
	 * 
	 * @param newView
	 * 			the view.
	 * @param newPath
	 * 			the path of the folder where the models are saved.
	 */
	public MainViewController(final MainView newView, final String newPath) {
		this.view = newView;
		this.path = newPath;
	}
	
	@Override
	public void addView(final IMainView v) {
		v.attachController(this);
	}

	@Override
	public void artworkCmd() {
		ArtGallery model = new ArtGallery();
		final ArtworkView artView = new ArtworkView();
		try {
			if (this.isFilePresent(this.path + ARCHIVE_FILE)) {
				final ObjectInputStream in = new ObjectInputStream(
						new FileInputStream(this.path + ARCHIVE_FILE));
				model = (ArtGallery) in.readObject();
				in.close();
				for (final IArtwork art : model.getArtwork()) {
					if (art.getYear() < 0) {
						this.addDataTable(artView, art, -1, " A.C.");
					} else {
						this.addDataTable(artView, art, 1, " D.C.");
					}
				}
			}
			new ControllerArtwork(model, artView, this.view, this.path 
					+ ARCHIVE_FILE);
			this.view.setVisible(false);
		} catch (IOException | ClassNotFoundException e) {
			artView.setVisible(false);
			JOptionPane.showMessageDialog(this.view, LOAD_ERROR, ERROR,
					JOptionPane.ERROR_MESSAGE);
		}
	}

	@Override
	public void exhibitCmd() {
		ArtGallery model = new ArtGallery();
		if (this.isFilePresent(this.path + ARCHIVE_FILE)) {
			try {
				final ObjectInputStream in = new ObjectInputStream(
						new FileInputStream(this.path + ARCHIVE_FILE));
				model = (ArtGallery) in.readObject();
				in.close();
				
			} catch (IOException | ClassNotFoundException e) {
				JOptionPane.showMessageDialog(this.view, LOAD_ERROR, ERROR,
						JOptionPane.ERROR_MESSAGE);
			}
		}
		if (model.getArtwork().size() == 0) {
			JOptionPane.showMessageDialog(this.view, OPEN_EX_ERROR, ERROR,
					JOptionPane.ERROR_MESSAGE);
		} else {
			final IExhibitView exView = new ExhibitView();
			for (final IExhibit exhibit : model.getExhibit()) {
				exView.addData(exhibit.getCode(), exhibit.getTitleExhibit(),
						exhibit.getCurator(), exhibit.getBeginning(), 
						exhibit.getEnd(), exhibit.getNumPieces(), 
						exhibit.getCostExhibit(), exhibit.getCostTicket());
			}
			new ControllerExhibit(model, exView, this.view, this.path + ARCHIVE_FILE);
			this.view.setVisible(false);
		}
	}

	@Override
	public void ticketOfficeCmd() {
		final TicketOfficeView v = new TicketOfficeView();
		SalesManagement salesModel = new SalesManagement();
		ArtGallery galleryModel = new ArtGallery();
		try {
			if (this.isFilePresent(this.path + SALES_FILE)) {
				final ObjectInputStream in = new ObjectInputStream(new FileInputStream(this.path + SALES_FILE));
				salesModel = (SalesManagement) in.readObject();
				in.close();
			}
			if (this.isFilePresent(this.path + ARCHIVE_FILE)) {
				final ObjectInputStream in2 = new ObjectInputStream(new FileInputStream(this.path + ARCHIVE_FILE));
				galleryModel = (ArtGallery) in2.readObject();
				in2.close();
			}
		} catch (IOException | ClassNotFoundException ex) {
			JOptionPane.showMessageDialog(this.view, LOAD_ERROR, ERROR, JOptionPane.ERROR_MESSAGE);
		}
			
		TicketOfficeController c = new TicketOfficeController(this.view, v, galleryModel, salesModel, this.path + SALES_FILE);
		this.checkNewExPresent(salesModel, galleryModel, c);
		
		c = new TicketOfficeController(this.view, v, galleryModel, salesModel, this.path + SALES_FILE);			
		c.addView(v);
		
		this.view.setVisible(false);

	}

	@Override
	public void balanceCmd() {
		final BalanceView v = new BalanceView();
		SalesManagement salesModel = new SalesManagement();
		Balance balanceModel = new Balance();
		if (this.isFilePresent(this.path + ARCHIVE_FILE)) {
			try {
				if (this.isFilePresent(this.path + SALES_FILE)) {
					final ObjectInputStream in = new ObjectInputStream(new FileInputStream(this.path + SALES_FILE));
					salesModel = (SalesManagement) in.readObject();
					in.close();
				}
				if (this.isFilePresent(this.path + BALANCE_FILE)) {
					final ObjectInputStream in = new ObjectInputStream(new FileInputStream(this.path + BALANCE_FILE));
					balanceModel = (Balance) in.readObject();
					in.close();
				}
			} catch (IOException | ClassNotFoundException ex) {
				JOptionPane.showMessageDialog(this.view, LOAD_ERROR, ERROR, JOptionPane.ERROR_MESSAGE);
			}
			
			final BalanceController c = new BalanceController(this.view, v, salesModel, balanceModel, this.path + BALANCE_FILE);
			c.addView(v);
			
			this.view.setVisible(false);
		} else {
			JOptionPane.showMessageDialog(this.view, "Non ci sono esposizioni salvate.", ERROR, JOptionPane.ERROR_MESSAGE);
		}
	}

	@Override
	public void classificationCmd() {
		final ClassificationView v = new ClassificationView();
		SalesManagement salesModel = new SalesManagement();
		if (this.isFilePresent(this.path + SALES_FILE)) {
			try {
				final ObjectInputStream in = new ObjectInputStream(new FileInputStream(this.path + SALES_FILE));
				salesModel = (SalesManagement) in.readObject();
				in.close();
			} catch (IOException | ClassNotFoundException ex) {
				JOptionPane.showMessageDialog(this.view, LOAD_ERROR, ERROR, JOptionPane.ERROR_MESSAGE);
			}	
			final ClassificationController c = new ClassificationController(this.view, v, salesModel);
			c.addView(v);
			
			this.view.setVisible(false);
		} else {
			JOptionPane.showMessageDialog(this.view, "Non sono stati venduti biglietti per le esposizioni.", ERROR, JOptionPane.ERROR_MESSAGE);
		}

	}

	@Override
	public void commandQuit() {
		System.exit(0);
	}
	
	/**
	 * This method checks if it's necessary to add a new exhibit to the map which stores all the sales data.
	 * @param newSales
	 * 				the SalesManagement model
	 * @param newGallery
	 * 				the ArtGallery model
	 * @param newCtrl
	 * 				the TicketOfficeController
	 */
	private void checkNewExPresent(final SalesManagement newSales, final ArtGallery newGallery, final TicketOfficeController newCtrl) {
		final ListIterator<Exhibit> it = newGallery.getExhibit().listIterator();
		while (it.hasNext()) {
			final Exhibit ex = it.next();
			final Calendar date = Calendar.getInstance();
			if (date.after(ex.getBeginning()) && date.before(ex.getEnd())) {
				if (!newSales.isExPresent(ex)) {
					newSales.addExhibit(ex);
				}
			}
		}
		newCtrl.save();
	}
	
	/**
	 * This method checks if the file is already present.
	 * 
	 * @param currentPath
	 * 			the current file path.
	 * 
	 * @return true if the file exists.
	 */
	private boolean isFilePresent(final String currentPath) {
		final File file = new File(currentPath);
		return file.exists();
	}
	
	/**
	 * This method adds the artwork's datas to the related table.
	 * 
	 * @param v
	 * 			the view of the artworks.
	 * @param art
	 * 			the model of the artworks.
	 * @param one
	 * 			-1 if it is negative, otherwise 1.
	 * @param dating
	 * 			"A.C." if the year is negative, otherwise "D.C.".
	 */
	private void addDataTable(final IArtworkView v, final IArtwork art,
			final int one, final String dating) {
		v.addData(new Object[] {art.getCode(), art.getTitle(), art.getAuthor(),
				art.getYear() * one + dating, art.getArtisticDiscipline(),
				art.getTechnique(), art.getHeight() + "x" + art.getWidth() 
				+ "x" + art.getDepth()});
	}

}
