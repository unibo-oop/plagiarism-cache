package aboidsim.controller;

import aboidsim.model.Model;
import aboidsim.model.ModelImpl;
import aboidsim.view.View;
import aboidsim.view.ViewImpl;

/**
 * This class starts the program by declaring Controller and View
 * implementation.
 *
 */
public final class Application {
	/**
	 * This methods starts the application.
	 *
	 * @param args
	 *            the arguments
	 */
	public static void main(final String[] args) {
		final Model model = new ModelImpl();
		final View view = new ViewImpl();
		final Controller controller = new ControllerImpl(model, view);
		view.setController(controller);
		controller.startApp();
	}

	private Application() {
	}
}
