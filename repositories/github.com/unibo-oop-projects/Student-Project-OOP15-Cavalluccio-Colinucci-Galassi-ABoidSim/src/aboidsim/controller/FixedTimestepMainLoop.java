package aboidsim.controller;

import java.util.ConcurrentModificationException;

import aboidsim.model.Model;
import aboidsim.util.Input;
import aboidsim.view.View;

/**
 * Main loop implementation. This loop runs with a fixed timestep. This means
 * that it always cycle after the same amount of time.
 *
 */
class FixedTimestepMainLoop extends AbstractMainLoop {

	private final long msPerFrame;
	private final Model model;
	private final View view;
	private final Controller controller;
	private final InputResolver inputResolver;

	/**
	 * Constructor.
	 *
	 * @param m
	 *            the Model.
	 * @param v
	 *            the View.
	 * @param c
	 *            the Controller.
	 * @param desiredFps
	 *            the desired frames per second.
	 */
	FixedTimestepMainLoop(final Model m, final View v, final Controller c, final long desiredFps) {
		super(desiredFps);
		this.msPerFrame = 1000 / this.getFPS();
		this.model = m;
		this.controller = c;
		this.view = v;
		this.inputResolver = i -> {
			if (i.getInput().equals(Input.CREATE_BOID)) {
				this.model.getSimulation().createBoid(i.getPosition(), i.getNumber().intValue());
			} else if (i.getInput().equals(Input.DESTROY_BOID)) {
				this.model.getSimulation().destroyBoid(i.getPosition());
			} else if (i.getInput().equals(Input.TOGGLE_RULE)) {
				this.model.getSimulation().toggleRule(i.getNumber().intValue());
			} else if (i.getInput().equals(Input.LOAD_ENV)) {
				this.model.getSimulation().loadDefaultEnvironment(i.getNumber().intValue());
			} else if (i.getInput().equals(Input.PAUSE)) {
				this.pauseLoop();
			} else if (i.getInput().equals(Input.RESUME)) {
				this.resumeLoop();
			} else if (i.getInput().equals(Input.CLOSE)) {
				this.abortLoop();
			}
		};
	}

	@Override
	public void run() {
		final int wait = 3;
		try {
			// We wait a couple of seconds
			Thread.sleep(wait * 1000);
		} catch (final InterruptedException e) {
			System.out.println("Sleep exception");
			this.abortLoop();
		}
		// This flag controls how many times we have to check the flocks
		int checkFlockFlag = 0;
		while (!this.getStatus().equals(LoopStatus.KILLED)) {
			final long lastTime = System.currentTimeMillis();
			this.inputResolver.resolveInputList(this.view.getInputs());
			// This thread will be used to speed up the application
			final Thread viewThread = new Thread() {
				@Override
				public void run() {
					try {
						FixedTimestepMainLoop.this.view.drawEntities(
								FixedTimestepMainLoop.this.model.getSimulation().getSimulationComponents());
					} catch (final ConcurrentModificationException e) {
					}
					/*
					 * The above exception is caused by the fact that the
					 * checkNearBoids() method is not called every frame
					 */
				}
			};
			viewThread.start();
			if (this.getStatus().equals(LoopStatus.RUNNING)) {
				if (checkFlockFlag % 3 == 0) {
					this.model.getSimulation().checkNearBoids();
				}
				this.model.getSimulation().updateEnvironment();
				checkFlockFlag++;
			}
			try {
				viewThread.join();
			} catch (final InterruptedException e) {
				System.out.println("Error in viewThread join");
			}
			final long timePassed = System.currentTimeMillis() - lastTime;
			if (timePassed < this.msPerFrame) {
				try {
					Thread.sleep(this.msPerFrame - timePassed);
				} catch (final InterruptedException e) {
					System.out.println("Sleep exception");
					this.abortLoop();
				}
			}
		}
	}

	@Override
	public void abortLoop() {
		this.setStatus(LoopStatus.KILLED);
		this.controller.close();
	}

	@Override
	public void pauseLoop() {
		if (this.getStatus().equals(LoopStatus.RUNNING)) {
			this.setStatus(LoopStatus.PAUSED);
		}
	}

	@Override
	public void resumeLoop() {
		if (this.getStatus().equals(LoopStatus.PAUSED)) {
			this.setStatus(LoopStatus.RUNNING);
		}
	}

}
