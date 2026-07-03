package pvz.controller;

import java.util.List;
import java.util.Optional;
import java.util.Set;

import javax.swing.text.View;
import pvz.model.entity.Entity;
import pvz.model.entity.plant.PlantType;
import pvz.utility.Pair;
import pvz.view.ViewInterface;
import pvz.view.input.Input;
import pvz.view.input.InputInterface;
import pvz.view.input.InputType;

import pvz.controller.data.DataManagerInterface;
import pvz.model.GameStatus;
import pvz.model.Model;
//import pvz.model.ModelImpl;

public class GameLoop extends Thread {

	public enum State {
		READY, RUNNING, PAUSED, KILLED;
	}

	private static final int PAUSE_MS = 500;
	private static final int UPS = 120;
	private static final int MS_PER_UPDATE =1000/UPS;
	
	
	private ControllerInterface controller;
	private DataManagerInterface dataManager;
	private ViewInterface view;
	private Model model;
	private State state;

	private int level;

	private int fps;

	private int msPerRender;
	private Mode loopMode;
	
	

	public GameLoop(final ControllerInterface controller, final ViewInterface view, final Optional<Integer> level,
			final DataManagerInterface dtm, final Mode mode, final int fps,final Set<PlantType> plants) {
		
		this.state = State.READY;
		this.controller = controller;
		this.view = view;
		// this.model = new ModelImpl(level);
		this.fps = fps;
		this.dataManager = dtm;
		this.msPerRender = 1000 / this.fps;
		
	}

	public void setState(final State state) {
		this.state = state;
	}

	public boolean isInState(final State state) {
		return this.state.equals(state);
	}

	public void abort() {
		if (this.isInPause()) {
			this.setState(State.KILLED);
		}
	}

	public void pause() {
		if (this.isRunning()) {
			this.setState(State.PAUSED);
		}
	}

	public void resumeGame() {
		if (this.isInPause()) {
			this.setState(State.RUNNING);
		}
	}

	public boolean isRunning() {
		return this.isInState(State.RUNNING);
	}

	public boolean isInPause() {
		return this.isInState(State.RUNNING);
	}
	@Override
	public void run() {
		this.setState(State.RUNNING);

		long lastStep = System.currentTimeMillis();
		long lag = 0;

		while (!this.isInState(State.KILLED)) {
			if (this.isInState(State.RUNNING)) {
				if (this.model.getStatus().equals(GameStatus.PLAYING)) {

					long loopTimer = System.currentTimeMillis();
					long firstStep = System.currentTimeMillis();
					long elapsed = firstStep - lastStep;
					lastStep = firstStep;
					lag += elapsed;

					// INPUTS----------------------------------------------

					this.parseInputs(this.view.getInput());

					// UPDATE---------------------------------------------

					while (lag >= MS_PER_UPDATE) {

						this.model.update();
						lag -= MS_PER_UPDATE;
					}
					// RENDER-------------------------------------------------

					final List<Entity> toDraw = this.model.getEntities();
					this.view.render(toDraw);

					// SLEEP----------------------------------------------------
					try {
						long gap = System.currentTimeMillis() - loopTimer;
						if (gap < this.msPerRender) {
							Thread.sleep(this.msPerRender - gap);
						}

					} catch (InterruptedException e) {

						e.printStackTrace();

					}

				} else if (this.model.getStatus().equals(GameStatus.LOST)) {
					this.setState(State.KILLED);
					this.view.notifyLevelEnd(GameStatus.LOST);
				} else {
					this.setState(State.KILLED);
					this.view.notifyLevelEnd(GameStatus.WON);
					this.dataManager.getCurrentPlayer().get().updateHistoryProgress();
				}

			} else {
				this.pauseGameLoop();
			}
		}
		this.controller.abortGameLoop();
	}

	private void pauseGameLoop() {
		try {
			Thread.sleep(GameLoop.PAUSE_MS);
		} catch (final InterruptedException e) {
			this.setState(State.KILLED);
		}
	}

	private void parseInputs(List<InputInterface> inputs) {

		inputs.stream().forEach(e -> {
			switch (e.getInputType()) {
			case ADD_PLANT:
				this.model.putPlant(new Pair<Double,Double>(e.getX(),e.getY()),e.getPlant().get());
				break;
			case HARVEST_ENERGY:
				this.model.harvestEnergy(new Pair<Double,Double>(e.getX(),e.getY()));
				break;
			case REMOVE_PLANT:
				this.model.removePlant(new Pair<Double,Double>(e.getX(),e.getY()));
				break;

			}
		});

	}

}
