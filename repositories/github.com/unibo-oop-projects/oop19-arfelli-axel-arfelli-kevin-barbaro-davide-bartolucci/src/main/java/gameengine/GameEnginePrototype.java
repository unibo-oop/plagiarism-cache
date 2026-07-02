package gameengine;

import java.util.Date;
import java.util.Optional;
import java.util.Set;
import java.util.concurrent.atomic.AtomicBoolean;

import gameengine.GameLogger.OutputLevel;
import virtualworld.Stage;

public class GameEnginePrototype implements GameEngine {

    private final AtomicBoolean alive = new AtomicBoolean();
    private final AtomicBoolean paused = new AtomicBoolean();
    private static final int STANDARDTICK = 20;
    private int tick;

    private Stage stage;
    private EntitiesUpdater entities;
    private Set<Thread> threads;

    private GameLogger logger;
    private final Object lock = new Object();


    public GameEnginePrototype() {
        this(STANDARDTICK);
    }

    public GameEnginePrototype(final int tick) {
        this(tick, new GameLogger() {

            @Override
            public void logLine(final String line, final OutputLevel level) {
                System.out.println(String.format("%tD %tT %s", new Date(), new Date(), level.format(line)));
            }
        });
    }

    /**
     * @param tick
     * @param logger
     */
    public GameEnginePrototype(final int tick, final GameLogger logger) {
        this.tick = tick;
        this.logger = logger;
    }

    @Override
    public final  void start(final Stage stage) {
        synchronized (lock) {
            if (!isStarted()) {
                this.alive.set(true);
                this.stage = stage;
                this.stage.setlock(lock);
                this.entities = new EntitiesUpdater(logger, tick, this.lock);
                this.stage.setSpawner(this.entities.getSpawner());

                this.threads = Set.of(new Thread(entities), new Thread(stage));
                this.threads.forEach(Thread::start);
                logger.logLine("GameEngine Started!", OutputLevel.LOG);
            }
        }
    }

    public final synchronized void stop() {
        synchronized (lock) {
            if (isStarted()) {
                this.alive.set(false);
                resume();
                this.stage.stop();
                this.entities.stop();
                logger.logLine("GameEngine Stopped!", OutputLevel.LOG);
            }
        }
    }

    @Override
    public final synchronized boolean isStarted() {
        synchronized (lock) {
            return this.alive.get();
        }
    }

    @Override
    public final synchronized boolean isPaused() {
        synchronized (lock) {
            return this.alive.get() && this.paused.get();
        }
    }

    @Override
    public final synchronized void pause() {
        synchronized (lock) {
            if (!isPaused() && isStarted()) {
                this.paused.set(true);
                this.stage.pause();
                this.entities.pause();
                logger.logLine("GameEngine paused", OutputLevel.DEBUG);
            }
        }
    }

    @Override
    public final synchronized void resume() {
        synchronized (lock) {
            if (isPaused()) {
                this.paused.set(false);
                this.stage.resume();
                this.entities.resume();
                logger.logLine("GameEngine resume", OutputLevel.DEBUG);
            }
        }
    }

    @Override
    public final Optional<Stage> getStage() {
        synchronized (lock) {
        return Optional.ofNullable(this.stage);
        }
    }
}
