package controller;

/**
 * This class extends {@link Thread}, manages the update of model, refresh the
 * graphics present in view and call the method to print the viewport on screen.
 * All these methods will be call N times for seconds.
 * 
 * @author Luca
 */
public class ControllerThread extends Thread {

	private static final int FPS = 60;
	private static final int TIME_ON_FPS = 1000 / FPS;

	// exclusive access to variables.
	private volatile Controller controller;
	private volatile long start;
	private volatile long elapsed;
	private volatile long sleepTime;
	private volatile boolean stop;

	/**
	 * Build the controller passed in argument.
	 * 
	 * @param controller
	 *            {@link Controller} who have the methods to update model and view.
	 * @throws NullPointerException
	 *             if the controller is null.
	 */
	public ControllerThread(final Controller controller) throws NullPointerException {
		if (controller != null) {
			this.controller = controller;
		} else {
			throw new NullPointerException("ERROR! The controller is not initialized.");
		}
	}

	/**
	 * Set the state of this thread; true to stop the thread, otherwise false.
	 * 
	 * @param stop
	 *            boolean.
	 */
	public void setStop(final boolean stop) {
		this.stop = stop;
	}

	/**
	 * get the state of this thread.
	 * 
	 * @return a boolean, if false the thread is running otherwise the thread is
	 *         freeze.
	 */
	public boolean getStop() {
		return this.stop;
	}

	@Override
	public void run() {
		while (!stop) {
			this.start = System.nanoTime();

			this.controller.updateModel();
			this.controller.updateView();
			this.controller.printView();

			this.elapsed = System.nanoTime() - this.start;
			this.sleepTime = TIME_ON_FPS - this.elapsed / 1000000;
			if (this.sleepTime < 0) {
				this.sleepTime = TIME_ON_FPS;
			}
			try {
				Thread.sleep(this.sleepTime);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}
}
