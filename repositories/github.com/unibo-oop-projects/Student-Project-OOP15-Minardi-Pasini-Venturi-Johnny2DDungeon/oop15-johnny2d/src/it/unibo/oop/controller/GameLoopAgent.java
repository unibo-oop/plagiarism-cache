package it.unibo.oop.controller;

import java.util.Arrays;
import java.util.List;
import java.util.function.Consumer;

import it.unibo.oop.model.GameState;
import it.unibo.oop.model.GameStateImpl;
import it.unibo.oop.utilities.Action;
import it.unibo.oop.utilities.Direction;
import it.unibo.oop.view.ESource;
import it.unibo.oop.view.View;
import it.unibo.oop.view.ViewImpl;

/**
 * Agent used by {@link ControllerImpl} to perform the game loop.
 */
public class GameLoopAgent implements AgentInterface, ESource<StateObserver> {

    private static final double FPS = 30;
    private static final int TO_SECONDS = 1000;
    private static final int SLEEPING_TIME = (int) (1 / FPS * TO_SECONDS);
    private final GameState gameState = GameStateImpl.getInstance();
    private final View view = ViewImpl.getInstance();
    private final List<StateObserver> stateObs;
    private volatile Direction mainCharDir;
    private volatile boolean isMainCharShooting;
    private volatile boolean pause;
    private volatile boolean gameOver;

    /**
     * Class's constructor.
     */
    public GameLoopAgent() {
        this.stateObs = Arrays.asList(new StateObserverImpl(this.view));
    }

    @Override
    public synchronized void play() {
        this.pause = false;
        this.gameOver = false;
        this.notifyAll();
    }

    @Override
    public synchronized void run() {

        /* GAME LOOP */
        while (true) {
            while (this.pause || this.gameOver) {
                try {
                    this.doAction(e -> e.stateAction(this.pause ? AppState.PAUSE : AppState.GAME_OVER));
                    this.wait();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }

            /* ACQUISIZIONE TASTI PREMUTI */
            this.processEvents();
//            this.dbgKeysMan(); /* per debugging */

            /* AGGIORNAMENTO GAMESTATE */
            this.gameState.updatePositions(this.mainCharDir, this.isMainCharShooting);

            /* CHECK GIOCO FINITO */
            this.gameOver = this.gameState.isGameEnded();

            /* AGGIORNAMENTO E PRINTING DEL FRAME */
            this.view.getLevelView().updateLevel();

            try {
                Thread.sleep(SLEEPING_TIME);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    private void processEvents() {
        final Action action = this.view.getAction();
        this.pause = action == Action.PAUSE;
        this.isMainCharShooting = action == Action.SHOOT;
        this.mainCharDir = this.view.getMovement();
    }

//    /* per debugging */
//    private void dbgKeysMan() {
//        if (this.mainCharDir != Direction.NONE) {
//            System.out.println("Dir : " + this.mainCharDir);
//        }
//        System.out.println(this.isMainCharShooting ? "SHOOT!" : "");
//    }

    @Override
    public void addObserver(final StateObserver obs) {
        this.stateObs.add(obs);
    }

    @Override
    public void doAction(final Consumer<StateObserver> action) {
        this.stateObs.forEach(action);
    }
}