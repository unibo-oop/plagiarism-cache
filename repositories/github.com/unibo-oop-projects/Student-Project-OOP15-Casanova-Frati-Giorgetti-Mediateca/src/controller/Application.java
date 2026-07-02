package controller;

import view.View;
import view.ViewImpl;

/**
 * Class designed to start the program.
 */

public final class Application {

	private Application() {
	}

	/**
	 * First method to be executed.
	 *
	 * @param args
	 *            Arguments passed to the main method
	 * @throws Exception
	 */
	public static void main(final String[] args) throws Exception {
		// application starter
		final Controller c = new ControllerImpl();
		final View v = new ViewImpl();
		v.setController(c);
		c.setView(v);
		v.startView();
	}
}
