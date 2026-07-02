package main.java.com.controller;

import java.awt.event.WindowEvent;
import java.util.Queue;

import java.util.concurrent.ArrayBlockingQueue;

import main.java.com.model.GameModel;

import main.java.com.model.Model;
import main.java.com.view.View;
import main.java.com.view.GameView;

/**
 * This class represents the controller's entry point. This class' purpose is to
 * connect together the view and the model of the application and make them
 * interact correctly. Here is implemented the method used to run the
 * application, which is mainLoop().
 *
 */
public class GameController implements Controller {

    private static final long PERIOD = 85;

    private final Model model;
    private final View view;
    private final ScoreManager sm;
    private final CollisionManager cm;
    private final Queue<Command> cmdQueue;
    private boolean isPaused;
    private boolean isStarted;
    private boolean quit;

    /**
     * Constructor for the Controller class. Initializes the {@link Model}, the
     * {@link View}, the {@link ScoreManager} and the {@link CollisionManager} and
     * also creates command queue. Paints the the view for the first time.
     */
    public GameController() {
        model = new GameModel();
        view = new GameView(model.getGameMap().getXMapSize(), model.getGameMap().getYMapSize());
        sm = new ScoreManagerImpl(view, model);
        cm = new CollisionManagerImpl(sm);
        view.setObserver(this);
        view.getMapView().addKeyListener(new KeyNotifier(this));
        cmdQueue = new ArrayBlockingQueue<>(100);
        view.getMapView().getAppleView().setPosition(model.getApple().getPosition());
        view.getMapView().getSnakeView().setBody(model.getSnake().getBodyPosition());
        view.show();
        view.getMapView().populateCells();
        sm.showHiScore();
    }

    /** {@inheritDoc} */
    public void mainLoop() {
        while (!quit) {
            final long current = System.currentTimeMillis();
            if (!isPaused && isStarted) {
                processInput();
                view.getMapView().getAppleView().setPosition(model.getApple().getPosition());
                view.getMapView().getSnakeView().setBody(model.getSnake().getBodyPosition());
                cm.manageAppleCollision(view, model);
                cm.manageWallOrBodyCollision(view, model);
                model.getSnake().move();
                view.updateView();
            }
            waitForNextFrame(current);
        }
    }

    /** {@inheritDoc} */
    @Override
    public void resetGame() {
        model.resetGame();
        sm.updateScore();
        if (isPaused) {
            isPaused = false;
        }
        view.getFrame().setEnabled(true);
        view.getMapView().requestFocusInWindow();
    }

    /** {@inheritDoc} */
    @Override
    public void pauseGame() {
        isPaused = !isPaused;
        if (!isPaused) {
            view.getMapView().requestFocusInWindow();
            view.enableButtons();
        }
    }

    /** {@inheritDoc} */
    @Override
    public void quit() {
        quit = true;
        view.getFrame().dispatchEvent(new WindowEvent(view.getFrame(), WindowEvent.WINDOW_CLOSING));
    }

    /** {@inheritDoc} */
    public void start() {
        view.enableButtons();
        view.getMapView().setFocusable(true);
        view.getMapView().requestFocusInWindow();
        isStarted = true;
    }

    /** {@inheritDoc} */
    @Override
    public void notifyCommand(final Command cmd) {
        if (!cmd.getDir().equals(model.getSnake().getDirection())) {
            cmdQueue.add(cmd);
        }
    }

    private void processInput() {
        final Command cmd = cmdQueue.poll();
        if (cmd != null) {
            cmd.execute(model);
        }
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
}
