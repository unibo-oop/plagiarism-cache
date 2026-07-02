package main;

import java.io.File;

import view.classes.MainView;
import controller.classes.MainViewController;
import controller.interfaces.IMainViewController;

/**
 * Main class of the program.
 * @author Elisa Casadio
 * @author Sofia Rosetti
 *
 */

public final class Main {

	private static final String PATH = System.getProperty("user.home") 
			+ System.getProperty("file.separator") + "ArtGalleryManagement";
	
	private Main() {
	}

	/**
	 * Main method. It starts the program.
	 * @param args
	 * 			args
	 */
	public static void main(final String[] args) {
		
		final File file = new File(PATH);
		if (!file.exists()) {
			file.mkdirs();
		}
		
		final MainView view = new MainView();
		final IMainViewController c = new MainViewController(view, PATH);
		c.addView(view);

	}

}
