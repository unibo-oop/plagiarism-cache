package controller;



import java.util.concurrent.TimeUnit;
import model.World;
import view.panels.GraphicEnvironment;

/**
 * @author Simone
 * The real core of application, it calls the update on every item.
 */
public class GameLoop implements Runnable {

    private static final long PERIOD = 10;
    private final World world;
    private final GraphicEnvironment view;

    /**
     * @param environment **the view of the world**
     * @param world **the world**
     */
    public GameLoop(final GraphicEnvironment environment, final World world) {
        super();
        this.view = environment;
        this.world = world;
    }

    @Override
    public final void run() {
        this.mainLoop();
    }

    private void mainLoop() {
        while (!this.world.isItGameOver()) {
            if (this.world.isWorldReset()) {
                try {
                    TimeUnit.SECONDS.sleep(1);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            final long current = System.currentTimeMillis();
            this.updateGame();
            this.updateView();
            this.waitForNextFrame(current);
        }
        this.view.close(this.world.getPairScore(), this.world.getComboSum());
    }

    private void waitForNextFrame(final long current) {
        final long dt = System.currentTimeMillis() - current;
        if (dt < PERIOD) {
            try {
                Thread.sleep(PERIOD - dt);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }


    private void updateGame() {
        this.world.updateState();
    }

    private void updateView() {
        this.view.render();
        this.view.updateScore(this.world.getPairScore());
    }

}
