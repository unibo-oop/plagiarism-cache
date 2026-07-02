package application;

import view.View;
import view.interfaces.IView;
import controller.Controller;
import controller.interfaces.IController;

/**
 * Class to launch the program.
 * 
 * @author Lorenzo Cottignoli
 *
 */
public final class Application {
	
	private Application() {
		super();
	}

	/**
	 * @param args
	 *            ignored
	 */
	public static void main(final String[] args) {
		final IController c = new Controller();
		final IView v = new View();
		c.addView(v);
	}

}
