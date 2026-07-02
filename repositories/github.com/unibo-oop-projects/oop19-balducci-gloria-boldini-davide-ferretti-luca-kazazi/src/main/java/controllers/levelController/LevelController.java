package controllers.levelController;

import java.util.Objects;
import controllers.camera.Camera;
import controllers.thread.ThreadManager;
import controllers.timer.GameTime;
import model.handler.Handler;
import model.updateLevel.UpdateLevel;
import model.updateLevel.loadEntity.LoadEntities;
import view.render.RenderLevel;

public class LevelController implements Runnable, LevelControllerInterface {

    private int level;
    private final Handler handler;
    private Thread th;
    private final ThreadManager thManager;
    private boolean isRunning;
    private final Camera camera;
    private final GameTime time;
    private UpdateLevel updateLevel;
    private RenderLevel renderLevel;

    /**
     * Constructor for LevelController.
     */
    public LevelController() {
        this.time = new GameTime();
        this.isRunning = false;
        this.thManager = new ThreadManager();
        this.handler = new Handler();
        this.camera = new Camera(0, 0);
    }

    @Override
    public void initLevel(final int level) {
        this.level = Objects.requireNonNull(level);
        this.handler.getGameObjectList().clear();
        new LoadEntities(handler, level).createEntities();
        handler.getPlayer().setTimer(time);
        this.updateLevel = new UpdateLevel(camera, handler);
        this.renderLevel = new RenderLevel(handler, level, camera, time);
        time.setLevel(level, camera);
    }

    @Override
    public void start() {
        this.th = new Thread(this, "Level Thread");
        this.isRunning = true;
        this.thManager.addThread(th);
        this.th.start();
        time.start();
        this.startActiveStaticObject();
    }

    @Override
    public synchronized void stopLevel() {
        this.thManager.removeThread("Level Thread");
        this.isRunning = false;
        time.pause();
        this.pauseActiveStaticObject();
        this.resetPlayerMovement();
    }

    @Override
    public synchronized void resumeLevel() {
        this.thManager.addThread(th);
        this.isRunning = true;
        this.start();
    }

    @Override
    public synchronized void interruptLevel() {
        time.reset();
        this.isRunning = false;
        this.th.interrupt();
    }

    @Override
    public ThreadManager getThreadManager() {
        return this.thManager;
    }

    @Override
    public GameTime getTime() {
        return time;
    }

    @Override
    public int getLevel() {
        return this.level;
    }

    @Override
    public RenderLevel getRendering() {
        return this.renderLevel;
    }

    @Override
    public void run() {
        long initialTime = System.nanoTime(); // Variabile per misurare il timer del sistema in nanosecondi
        final double numFPS = 60.0; // Numero di FPS con cui vogliamo aggiornare il gioco -> numero di
                                    // aggiornamenti al secondo
        final double ns = 1000000000 / numFPS; // Numero di nanosecondi di durata di un frame
        double delta = 0; // Variabile per calcolare il tempo trascorso

        while (isRunning) {
            final long now = System.nanoTime();
            delta += (now - initialTime) / ns; // Differenza di tempo da quando si è entrati nel metodo run()
            initialTime = now;
            if (delta >= 1) { // Verificare se il tempo trascorso tra initialTime e now è maggiore/uguale a 1
                              // // secondo
                updateLevel.tick();
                renderLevel.render();
                delta = 0; // Per ripristinare il valore di delta ad un valore minore di 1
            }
        }
        stopLevel();
    }

    private void pauseActiveStaticObject() {
        if (this.handler.getPlayer().getActivePowerUpDebuff() != null) {
            this.handler.getPlayer().getActivePowerUpDebuff().getTimer().pause();
        }
    }

    private void startActiveStaticObject() {
        if (this.handler.getPlayer().getActivePowerUpDebuff() != null) {
            this.handler.getPlayer().getActivePowerUpDebuff().getTimer().start();
        }
    }

    private void resetPlayerMovement() {
        this.handler.getPlayer().getMovement().reset();
    }

}
