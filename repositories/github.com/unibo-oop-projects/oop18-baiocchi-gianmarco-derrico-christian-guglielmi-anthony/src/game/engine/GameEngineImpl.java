package game.engine;

/**
 * This realizes the Game Loop, implementing {@link GameEngine}.
 *
 */
public class GameEngineImpl extends Thread implements GameEngine {
    /**
     * Current game frames per second.
     */
    public static final int FPS = 60;
    private static final int SECOND = 1000;
    private static final int PERIOD = SECOND / FPS;
    private boolean running;
    private Thread thread;

    /**
     * {@inheritDoc}.
     */
    @Override
    public void startLoop() {
        if (!this.running) {
            this.running = true;
            this.thread = new Thread(this);
            this.thread.start();
        }
    }
    /**
     * {@inheritDoc}.
     */
    @Override
    public void stopLoop() {
        if (this.running) {
            this.running = false;
            try {
                this.thread.join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
    /**
     * {@inheritDoc}
     */
    @Override
    public void run() {
        long elapsed;
        long currentFrameTime;
        long lastFrameTime = System.currentTimeMillis();
        while (running) {
            currentFrameTime = System.currentTimeMillis();
            elapsed = lastFrameTime - currentFrameTime;
            lastFrameTime = currentFrameTime;
            this.processInput();
            this.update(elapsed);
            this.render();
            final long dTime = System.currentTimeMillis();
            this.waitNextFrame(dTime);
        }
        this.stopLoop();
    }

    private void processInput() {
        //to be implemented
    }

    private void update(final long elapsed) {
        System.out.println("Elapsed: " + elapsed);
        //to be implemented
    }

    private void render() {
        //to be implemented
    }

    private void waitNextFrame(final long deltaTime) {
        long sleepTime;
        long remainingTime;
        remainingTime = PERIOD - deltaTime;
        if (remainingTime < 0) {
            sleepTime = PERIOD;
        } else {
            sleepTime = remainingTime;
        }
        try {
            Thread.sleep(sleepTime);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

}
