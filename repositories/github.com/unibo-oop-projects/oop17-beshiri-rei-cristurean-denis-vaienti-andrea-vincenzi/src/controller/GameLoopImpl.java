package controller;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Objects;

import controller.time.Time;
import controller.time.TimeAgent;
import controller.utility.LeaderboardComparator;
import controller.utility.Score;
import controller.utility.ScoreCalculator;
import controller.utility.ScoreImpl;
import model.World;
import model.animated.AbstractCharacter;
import model.utility.ModelUtility;
import model.worldevent.BossFightStarted;
import model.worldevent.PlayerDied;
import model.worldevent.PlayerHeartChange;
import model.worldevent.PlayerHitButton;
import model.worldevent.PlayerKillAllEnemy;
import model.worldevent.PlayerKillBoss;
import model.worldevent.PlayerKillEnemy;
import model.worldevent.PlayerScoreChange;
import model.worldevent.RoomChange;
import model.worldevent.WorldEvent;
import utility.Command;
import utility.Mode;
import utility.Statistic;
import utility.StatisticImpl;
import view.ViewImpl;

/**
 * Defines all the operation for update the model and pass the information of
 * model to the view for update the graphic.
 */
public class GameLoopImpl implements GameLoop, Runnable {

    private static final long SECONDMICRO = 1000000;
    private static final int SECONDNANO = 1000000000;
    private static final int FPS = 60;

    private final List<Command> movement = new ArrayList<>();
    private final List<Command> shot = new ArrayList<>();
    private boolean running;
    private Thread gameLoopThread;
    private int optimalTime;
    private long lastLoop;
    private final World world;
    private int point;
    private Thread timerThread;
    private TimeAgent timeAgent;
    private final Time time;
    private final String name;
    private final Mode mode;

    /**
     * The class constructor.
     * 
     * @param world
     *            The instance of the model
     * @param name
     *            Name of the player.
     * @param selectedMode
     *            Mode selected.
     */
    public GameLoopImpl(final World world, final String name, final Mode selectedMode) {
        this.world = world;
        this.name = name;
        time = new Time(0, 0);
        mode = selectedMode;
    }

    /**
     * Start the game loop.
     */
    @Override
    public void start() {
        if (!running) {
            this.running = true;
            optimalTime = GameLoopImpl.SECONDNANO / GameLoopImpl.FPS;
            this.lastLoop = System.nanoTime();
            gameLoopThread = new Thread(this);
            gameLoopThread.setDaemon(true);
            gameLoopThread.start();
            if (ModelUtility.pauseDuringRound()) {
                startTime();
            }
        }
    }

    /**
     * Stop the game loop.
     */
    @Override
    public void stop() {
        if (running) {
            interrupt();
            if (!Objects.isNull(timerThread) && timeAgent.isRunning()) {
                stopTime();
            }
        }
    }

    /**
     * Method that implement the game loop of the game. It updates world, manage the
     * world events and call the render.
     */
    @Override
    public void run() {
        while (running) {
            final long now = System.nanoTime();
            final long sleepTime;
            final double delta = (now - this.lastLoop) / ((double) GameLoopImpl.SECONDNANO / 60);
            lastLoop = now;

            update(delta);
            ViewImpl.get().refreshPlayerStats(createPlayerStats());
            ViewImpl.get().render(ModelUtility.getAnimatedObjects());
            checkEvent();

            sleepTime = (lastLoop - System.nanoTime() + optimalTime) / GameLoopImpl.SECONDMICRO;
            if (sleepTime > 0) {
                try {
                    Thread.sleep(sleepTime);
                } catch (Exception e) {
                    System.out.println("Sleep timer method on run errror: " + e.getMessage());
                }
            }
        }
    }

    /**
     * Return true if the game loop is running, false otherwise.
     */
    @Override
    public boolean isRunning() {
        return this.running;
    }

    /**
     * Add a shot to list.
     */
    @Override
    public void addShot(final Command d) {
        shot.add(d);
    }

    /**
     * Remove a shot from the list.
     */
    @Override
    public void removeShot(final Command d) {
        shot.remove(d);
    }

    /**
     * Add a movement to list.
     */
    @Override
    public void addMovement(final Command d) {
        if (!movement.contains(d)) {
            movement.add(d);
        }
    }

    /**
     * Remove a movement from the list.
     */
    @Override
    public void removeMovement(final Command d) {
        movement.remove(d);
    }

    /**
     * Get player's name.
     * 
     * @return the player's name.
     */
    public String getName() {
        return this.name;
    }

    /**
     * Interrupt event, that stop thread.
     */
    private void interrupt() {
        running = false;
    }

    /**
     * Call the the world's method update().
     * 
     * @param delta
     *            The time between a frame and the next.
     */
    private void update(final double delta) {
        world.update(delta, this.movement, this.shot);
    }

    /**
     * Check the world's events.
     */
    private void checkEvent() {
        final List<WorldEvent> worldEvent = Objects.isNull(ModelUtility.getWorldEventList()) ? Collections.emptyList()
                : new ArrayList<>(ModelUtility.getWorldEventList());
        worldEvent.forEach(x -> {
            if (x instanceof PlayerHitButton && (Objects.isNull(timerThread) || !timerThread.isAlive())) {
                startTime();
            } else if (x instanceof PlayerKillEnemy) {
                point += ((PlayerKillEnemy) x).getPoint();
            } else if (x instanceof PlayerScoreChange) {
                point -= ((PlayerScoreChange) x).getPoints();
            } else if (x instanceof PlayerKillAllEnemy) {
                stopTime();
            } else if (x instanceof BossFightStarted) {
                startTime();
            } else if (x instanceof PlayerKillBoss) {
                stopTime();
                point += bonusTime(time.getTimeInSeconds()); // BONUS TIME ONLY IF BOSS DIED.
                checkResultWithLeaderboard();
                GameEngineImpl.get().victory(point);
            } else if (x instanceof PlayerDied) {
                stopTime();
                checkResultWithLeaderboard();
                GameEngineImpl.get().gameOver(point);
            } else if (x instanceof PlayerHeartChange) {
                ViewImpl.get().playerLifeChanged(((PlayerHeartChange) x).getCurretLife());
            } else if (x instanceof RoomChange) {
                ViewImpl.get().roomChanged(((RoomChange) x).getNewRoom());
            }
        });
    }

    /**
     * 
     * @param timeElapsed
     *            The time the player took to finish the game.
     * @return Bonus point based on time.
     */
    private int bonusTime(final int timeElapsed) {
        return ScoreCalculator.bonusTime(timeElapsed);
    }

    /**
     * Start the time.
     */
    private void startTime() {
        timeAgent = new TimeAgent(time);
        if (!time.getListeners().contains(ViewImpl.get().getDrawerReference())) {
            time.addListener(ViewImpl.get().getDrawerReference());
        }
        timerThread = new Thread(timeAgent);
        timerThread.setDaemon(true);
        timerThread.start();
    }

    /**
     * Stop the time.
     */
    private void stopTime() {
        timeAgent.interrupt();
    }

    /**
     * Method used to check result with leader board results.
     */
    private void checkResultWithLeaderboard() {
        final Score score = new ScoreImpl(name, point, time, mode);
        final List<Score> leaderboard = GameEngineImpl.get().getLeaderboard();
        if (leaderboard.size() < 10) {
            leaderboard.add(score);
            leaderboard.sort(new LeaderboardComparator<>());
            GameEngineImpl.get().setLeaderboard(leaderboard);
        } else if (score.compareTo(leaderboard.get(leaderboard.size() - 1)) > 0) {
            leaderboard.remove(leaderboard.size() - 1);
            leaderboard.add(score);
            leaderboard.sort(new LeaderboardComparator<Score>());
            GameEngineImpl.get().setLeaderboard(leaderboard);
        }
    }

    private Statistic createPlayerStats() {
        final AbstractCharacter player = (AbstractCharacter) ModelUtility.getPlayer();
        return new StatisticImpl(point, player.getBulletDamage(), player.getVel(), player.getBulletRange());
    }
}
