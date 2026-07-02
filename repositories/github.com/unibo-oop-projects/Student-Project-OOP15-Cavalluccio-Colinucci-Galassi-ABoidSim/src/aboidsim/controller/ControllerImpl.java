package aboidsim.controller;

import aboidsim.model.Model;
import aboidsim.view.View;

/**
 * Implementation of the Controller interface.
 *
 */
public class ControllerImpl implements Controller {

	private static final int FPS = 10;

	private Model model;
	private View view;
	private AbstractMainLoop mainLoop;

	/**
	 * Public constructor.
	 *
	 * @param m
	 *            a Model implementation
	 * @param v
	 *            a View implementation
	 */
	ControllerImpl(final Model m, final View v) {
		this.model = m;
		this.view = v;
		this.mainLoop = new FixedTimestepMainLoop(m, v, this, ControllerImpl.FPS);
	}

	@Override
	public void setView(final View newView) {
		this.view = newView;
	}

	@Override
	public View getView() {
		return this.view;
	}

	@Override
	public void setModel(final Model newModel) {
		this.model = newModel;
	}

	@Override
	public Model getModel() {
		return this.model;
	}

	@Override
	public void setMainLoop(final AbstractMainLoop newMainLoop) {
		this.mainLoop = newMainLoop;
	}

	@Override
	public AbstractMainLoop getMainLoop() {
		return this.mainLoop;
	}

	@Override
	public void startApp() {
		this.model.getSimulation().setScreenDimension(this.view.getScreenDimensions());
		this.mainLoop.start();
		this.view.start(this.model.getEntitiesNames(), this.model.getRulesNames(), this.model.getLevelAndImages(),
				this.model.getAllEnvironmentsNames());
	}

	@Override
	public void close() {
		System.exit(0);
	}

}
