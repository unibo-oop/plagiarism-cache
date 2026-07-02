package controller.core;

import java.util.HashMap;
import java.util.Map;

import javafx.animation.AnimationTimer;
import javafx.scene.control.Label;
import javafx.util.Pair;
import model.world_state.World;
import model.world_state.WorldImpl;
import view.GameBoard;
import view.SceneManager;
import view.Scenes;

public class GameEngineImpl implements GameEngine {

    private static final double RANGE = 26.0;
    private static final double POS = RANGE / 2;
    private static final long SLEEP = 2000;
    private static final Pair<Double, Double> UNI_DEFAULT = new Pair<>(POS, 100.0);

    private World world;
    private AnimationTimer animTimerRender;
    private Thread timerThread;
    private Thread scoreThread;

    public GameEngineImpl() {
        this.world = new WorldImpl();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public World getWorld() {
        return world;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void setWorld(final World world) {
        this.world = world;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void mainLoop(final Label score, final Label timer, final GameBoard gameBoard) {

        timerThread = new Thread(world.getTimer());
        timerThread.start();
        scoreThread = new Thread(world.getScore());
        scoreThread.start();

        animTimerRender = new AnimationTimer() {

            @Override
            public void handle(final long now) {

                render(score, timer, gameBoard);
                if (world.isWordSetPause()) {
                    try {
                        Thread.sleep(SLEEP);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    world.setWordSetPause(false);
                }
                world.update();

                if (world.getGameState().isPauseState()) {
                    SceneManager.showScene(Scenes.PAUSE.getNewScene());
                    animTimerRender.stop();
                    timerThread.interrupt();
                    scoreThread.interrupt();
                }

                if (world.getGameState().isGameOver()) {
                    SceneManager.showScene(Scenes.GAMEOVER.getNewScene());
                    animTimerRender.stop();
                    timerThread.interrupt();
                    scoreThread.interrupt();
                }
            }
        };
        animTimerRender.start();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void restart() {
        world.getGameState().setPause(false);
        world.getUnicorn().setLocation(UNI_DEFAULT);
        animTimerRender.start();
        timerThread = new Thread(world.getTimer());
        timerThread.start();
        scoreThread = new Thread(world.getScore());
        scoreThread.start();
    }

    /**
     * This method draw the game in order to show what changed.
     */
    private void render(final Label score, final Label timer, final GameBoard gameBoard) {
        world.getUnicorn().getLocation();
        score.setText(world.getScore().toString());
        timer.setText(world.getTimer().toString());
        gameBoard.refresh(this.mappingSet(), world.getUnicorn().getLocation(), RANGE, 100.0);
    }

    /**
     * 
     * @return a map containing : in the key the position of the word Pair<double,
     *         double> , in the value a Pair<String,Boolean> the word string and the
     *         boolean saying if the word is active or not.
     */
    private Map<Pair<Double, Double>, Pair<String, Boolean>> mappingSet() {
        final Map<Pair<Double, Double>, Pair<String, Boolean>> map = new HashMap<>();
        for (var i : world.getWordSet()) {
            map.put(i.getPosition(), new Pair<>(i.getWord(), i.isActive()));
        }
        return map;
    }

}