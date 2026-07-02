package it.unibo.controller.impl;

import java.util.Set;

import edu.umd.cs.findbugs.annotations.SuppressFBWarnings;
import it.unibo.common.Pair;
import it.unibo.model.api.ComponentType;
import it.unibo.model.api.GamePerformance;
import it.unibo.model.impl.FixWindowsComponent;
import it.unibo.model.impl.GamePerformanceImpl;
import it.unibo.model.impl.ImmortalityComponent;
import it.unibo.model.impl.LivesComponent;
import it.unibo.model.impl.StopRalphComponent;
import it.unibo.model.impl.ThrowBrickComponent;
import javafx.scene.input.KeyCode;
import it.unibo.controller.api.Controller;
/**
 * Main controller of the game.
 */
public class GameController implements Controller {
    private final GamePerformance gamePerformance;
    private final Set<Controller> controllers;
    private int level;
    /**
     * Constructor for the GameController.
     */
    public GameController() {
        this.gamePerformance = new GamePerformanceImpl(this);
        final Controller ralphController = new RalphController(this.gamePerformance);
        final Controller felixController = new FelixController(this.gamePerformance);
        final Controller brickController = new BrickController(this.gamePerformance);
        final Controller windowsController = new WindowsController(gamePerformance);
        final Controller birdController = new BirdController(gamePerformance);
        final Controller cakeController = new CakeController(gamePerformance);
        final Controller collisionManager = new CollisionManager(this.gamePerformance);
        this.controllers = Set.of(ralphController, felixController, brickController, 
            windowsController, birdController, cakeController, collisionManager);
        this.level = 1; 
    }
    /**
     * {@inheritDoc}
     */
    @Override
    public void update() {
        this.controllers.forEach(Controller::update);
        ((ImmortalityComponent) this.getFelixController().getFelix()
                                    .getTheComponent(ComponentType.IMMORTALITY).get())
                                    .chekStopImmortality((LivesComponent) this.getFelixController().getFelix()
                                                         .getTheComponent(ComponentType.LIFE).get());
        ((StopRalphComponent) this.getRalphController().getRalph()
                                    .getTheComponent(ComponentType.STOPRALPH).get())
                                    .checkUnlockRalph((ThrowBrickComponent) this.getRalphController().getRalph()
                                                        .getTheComponent(ComponentType.THROWBRICK).get());
    }
    /**
     * Controls if the game is over.
     * @return true if the game is over, false otherwise.
     */
    public boolean gameIsNotOver() {
        return this.getFelixController().isAlive();
    }
    /**
     * Getter for the Ralph controller.
     * @return the Ralph controller.
     */
    public RalphController getRalphController() {
        return this.controllers.stream()
                               .filter(c -> c instanceof RalphController)
                               .map(c -> (RalphController) c)
                               .findFirst()
                               .get();
    } 
    /**
     * Getter for the Felix controller.
     * @return the Felix controller.
     */
    public FelixController getFelixController() {
        return this.controllers.stream()
                               .filter(c -> c instanceof FelixController)
                               .map(c -> (FelixController) c)
                               .findFirst()
                               .get();
    }
    /**
     * Getter for the Brick controller.
     * @return the Brick controller.
     */
    public BrickController getBrickController() {
        return this.controllers.stream()
                               .filter(c -> c instanceof BrickController)
                               .map(c -> (BrickController) c)
                               .findFirst()
                               .get();
    }
    /**
     * Getter for the Windows controller.
     * @return the Windows controller.
     */
    public WindowsController getWindowsController() {
        return this.controllers.stream()
                               .filter(c -> c instanceof WindowsController)
                               .map(c -> (WindowsController) c)
                               .findFirst()
                               .get();
    }
    /**
     * Getter for the Bird controller.
     * @return the Bird Controller.
     */
    public BirdController getBirdController() {
        return this.controllers.stream()
                               .filter(c -> c instanceof BirdController)
                               .map(c -> (BirdController) c)
                               .findFirst()
                               .get();
    }
    /**
     * Getter for the Cake controller.
     * @return the Cake controller.
     */
    public CakeController getCakeController() {
        return this.controllers.stream()
                               .filter(c -> c instanceof CakeController)
                               .map(c -> (CakeController) c)
                               .findFirst()
                               .get();
    }
    /**
     * Getter for the level.
     * @return the level.
     */
    public int getLevel() {
        return this.level;
    }
    /**
     * Getter for the level.
     * @return the level.
     */
    @SuppressFBWarnings(value = "EI_EXPOSE_REP", justification = "We need the original object")
    public GamePerformance getGamePerformance() {
        return this.gamePerformance;
    }
    /**
     * Setter for the level.
     * @param level the new level.
     */
    public void setLevel(final int level) {
        this.level = level;
    }
    /**
     * Getter for the winning condition.
     * @return true if the game is won, false otherwise.
     */
    public boolean isWin() {
        return this.getWindowsController().won();
    }
    /**
     * Method to move the player after receiving keyboard input "down".
     * @param e the keyCode
     */
    public void moveFelixDown(final KeyCode e) {
        this.getFelixController().moveDown();
        this.gamePerformance.addKey(e);
    }
    /**
     * Method to move the player after receiving keyboard input "left".
     * @param e the keyCode
     */
    public void moveFelixLeft(final KeyCode e) {
        this.getFelixController().moveLeft();
        this.gamePerformance.addKey(e);
    }
    /**
     * Method to move the player after receiving keyboard input "right".
     * @param e the KeyCode
     */
    public void moveFelixRight(final KeyCode e) { 
        this.getFelixController().moveRight();
        this.gamePerformance.addKey(e);
    }
    /**
     * Method to move the player after receiving keyboard input "up".
     * @param e the KeyCode
     */
    public void moveFelixUp(final KeyCode e) {
        this.getFelixController().moveUp();
        this.gamePerformance.addKey(e);
    }
    /**
     * Method to fix a window calling the hiboxcomponent of felix to know the position.
     * @param e the KeyCode
     * @param pos the position of the window to fix.
     */
    public void fixWindows(final KeyCode e, final Pair<Double, Double> pos) {
        final FixWindowsComponent fixComp = (FixWindowsComponent) this.getFelixController().getFelix()
                                                                        .getTheComponent(ComponentType.FIXWINDOWS).get();
        fixComp.fixing(pos, this.gamePerformance);
        this.gamePerformance.addKey(e);
    }
}
