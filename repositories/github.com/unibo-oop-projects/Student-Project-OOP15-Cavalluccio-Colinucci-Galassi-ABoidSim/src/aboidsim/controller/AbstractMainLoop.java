package aboidsim.controller;

/**
 * Abstract class.
 *
 */
abstract class AbstractMainLoop extends Thread {

	/**
	 * LoopStatus enumeration.
	 */
	enum LoopStatus {

		RUNNING, PAUSED, KILLED;
	}

	private LoopStatus status;
	private long fps;

	/**
	 * Constructor.
	 *
	 * @param desiredFps
	 *            the desired fps.
	 * @throws IllegalArgumentException
	 *             if fps < 1
	 */
	AbstractMainLoop(final long desiredFps) throws IllegalArgumentException {
		this.status = LoopStatus.RUNNING;
		this.fps = desiredFps;
		if (this.fps <= 0) {
			throw new IllegalArgumentException("FPS must be >0");
		}
	}

	/**
	 * Setter. This method sets a new status.
	 *
	 * @param newStatus
	 *            the new status
	 */
	public void setStatus(final LoopStatus newStatus) {
		this.status = newStatus;
	}

	/**
	 * Getter. This method return the current status.
	 *
	 * @return the current status
	 *
	 */
	public LoopStatus getStatus() {
		return this.status;
	}

	/**
	 * Setter. This method sets the new fps.
	 *
	 * @param newFps
	 *            the new fps
	 */
	public void setFPS(final long newFps) {
		this.fps = newFps;
	}

	/**
	 * Getter. This method return the current fps.
	 *
	 * @return the current fps
	 *
	 */
	public long getFPS() {
		return this.fps;
	}

	@Override
	public abstract void run();

	/**
	 * Abstract method. This method stops the loop.
	 */
	public abstract void abortLoop();

	/**
	 * Abstract method. This method pauses the loop.
	 */
	public abstract void pauseLoop();

	/**
	 * Abstract method. This method resumes the loop.
	 */
	public abstract void resumeLoop();
}
