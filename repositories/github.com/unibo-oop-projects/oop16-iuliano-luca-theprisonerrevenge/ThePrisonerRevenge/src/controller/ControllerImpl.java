package controller;

import java.awt.event.KeyEvent;
import model.Model;
import view.View;

/**
 * This Class implements {@link Controller}. This is the CONTROLLER of MVC
 * PATTERN. The Controller is a Listener, so it will be call when the View
 * detects a user's interaction. The view Listener will get this instance of
 * controller in parameters. The methods to update the model (the state of the
 * application) and the view are call by the {@link ControllerThread}.
 * 
 * @author Luca
 */
public class ControllerImpl implements Controller {

	private final Model model;
	private final View view;
	private final Thread controllerThread;

	/**
	 * Initialize the model and the view passed in argument, create the Thread
	 * and add the listener to the view.
	 * 
	 * @param model
	 *            Model that is the model instance of the application.
	 * @param view
	 *            View that is the view instance of the application.
	 */
	public ControllerImpl(final Model model, final View view) {
		this.model = model;
		this.view = view;
		this.controllerThread = new ControllerThread(this);
		this.view.addListener(this);
	}

	@Override
	public void startControllerThread() {
		this.controllerThread.start();
	}

	@Override
	public void updateModel() {
		this.model.updateModel();
	}

	@Override
	public void updateView() {
		this.view.updateView();
	}

	@Override
	public void printView() {
		this.view.printView();
	}

	@Override
	public void keyPressed(KeyEvent key) {
		this.model.keyPressed(key);
	}

	@Override
	public void keyReleased(KeyEvent key) {
		this.model.keyReleased(key);
	}

	@Override
	public void keyTyped(KeyEvent key) {
		// not USED.
	}
}
