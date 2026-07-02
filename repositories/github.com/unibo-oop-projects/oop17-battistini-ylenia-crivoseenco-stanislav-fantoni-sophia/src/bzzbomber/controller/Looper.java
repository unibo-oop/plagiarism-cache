package bzzbomber.controller;

/**
 * Represents an abstract "Engine" which loops the whole game 60 times per
 * second.
 */
public class Looper implements Runnable {

    private static final int FPS = 60;
    private static final double TIME_PER_UPDATE = 1000000000 / FPS; // 10^9 nanosecs in 1 sec
    private static final long NANOSECS_IN_ONE_SEC = 1000000000;

    private boolean running;
    private final Controller controller;

    /**
     * @param c
     *            Reference of Controller
     */
    public Looper(final Controller c) {
        this.controller = c;
    }

    /**
     * All the model updating logic starts from here.
     */
    private void update() {
        if (this.controller != null) {
            this.controller.updateFrame();
        }
    }

    @Override
    public final void run() {

        double delta = 0;
        long now;
        long lastTime = System.nanoTime();
        long timer = 0;

        while (this.isRunning()) {
            now = System.nanoTime();
            delta += (now - lastTime) / TIME_PER_UPDATE;
            timer += now - lastTime;
            lastTime = now;

            if (delta >= 1) {
                update();
                delta--;
            }

            if (timer >= NANOSECS_IN_ONE_SEC) {
                this.controller.secPassed();
                timer = 0;
            }
        }
    }

    /**
     * Sets this looper to run, if it's not running already.
     */
    public synchronized void setOn() {
        if (this.isRunning()) {
            return;
        }
        this.setRunning(true);
    }

    /**
     * Sets this looper to stop, if it's not stoped already.
     */
    public synchronized void setOff() {
        if (!this.isRunning()) {
            return;
        }
        this.setRunning(false);
    }

    /**
     * @return Running state of the looper.
     */
    public boolean isRunning() {
        return running;
    }

    /**
     * This method set running true or false .
     * 
     * @param running
     *            it's a boolean .
     */
    private void setRunning(final boolean running) {
        this.running = running;
    }

}
