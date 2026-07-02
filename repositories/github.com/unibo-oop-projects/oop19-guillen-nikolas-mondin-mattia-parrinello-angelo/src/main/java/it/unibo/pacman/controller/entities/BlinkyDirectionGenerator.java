package it.unibo.pacman.controller.entities;

import it.unibo.pacman.model.utilities.Direction;
/**
 * Creates a Direction every SLEEP ms, that Blinky could follow.
 */
public class BlinkyDirectionGenerator extends Thread {
    private boolean isRunning;
    private final BlinkyController blinky;
    private static final int SLEEP = 250;
    /**
     * Create a simple {@link BlinkyDirectionGenerator}.
     * @param blinky a {@link BlinkyController}
     */
    public BlinkyDirectionGenerator(final BlinkyController blinky) {
        this.blinky = blinky;
        this.start();
    }
    @Override
    public final void run() {
            while (this.isRunning) {
                Direction preferred;
                final Direction oppDir = Direction.getOppositeDirection(this.blinky.getActualDirection());
                final Direction actDir = this.blinky.getActualDirection();
                do {
                preferred = Direction.getRandomDirection();
                this.blinky.setPreferredDirection(preferred);
                } while (oppDir.equals(preferred) || actDir.equals(preferred));
                try {
                    Thread.sleep(SLEEP);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
    }
    /**
     * Used to stop MyThread.
     */
    public final void stopMyThread() {
        this.isRunning = false;
    }
    /**
     * Used to start MyThread.
     */
    public final void startMyThread() {
        this.isRunning = true;
    }
}
