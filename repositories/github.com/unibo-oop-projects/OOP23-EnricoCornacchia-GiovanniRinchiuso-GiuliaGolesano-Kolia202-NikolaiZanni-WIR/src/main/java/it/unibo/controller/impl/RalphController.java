package it.unibo.controller.impl;

import it.unibo.model.api.Component;
import it.unibo.model.api.ComponentType;
import it.unibo.model.api.Entity;
import it.unibo.model.api.EntityFactory;
import it.unibo.model.api.GamePerformance;
import it.unibo.model.impl.EntityFactoryImpl;
import it.unibo.model.impl.MovementComponent;
import it.unibo.model.impl.ThrowBrickComponent;
import it.unibo.utilities.Constants;

import edu.umd.cs.findbugs.annotations.SuppressFBWarnings;
import it.unibo.common.Pair;
import it.unibo.controller.api.Controller;


/**
 * Controller for Ralph.
 */
public class RalphController implements Controller {
    private final Entity ralph;
    private final GamePerformance gamePerformance;
    private long lastThrowTime;
    private double lastMov;
    private int count;
    /**
     * Constructor for the RalphController.
     * @param gamePerformance the game performance.
     */
    @SuppressFBWarnings(value = "EI_EXPOSE_REP2", justification = "We need the original object")
    public RalphController(final GamePerformance gamePerformance) {
        this.gamePerformance = gamePerformance;
        final EntityFactory entityFactory = new EntityFactoryImpl(this.gamePerformance);
        ralph = entityFactory.createRalph(Constants.Ralph.RALPH_START);
        gamePerformance.addEntity(ralph);
        lastThrowTime = System.currentTimeMillis();
    }
    /**
     * Move Ralph.
     */
    public void move() {
        if (count % 10 == 0) {
            lastMov = Math.random() > 0.5 ? 1 : -1;
        }
        count++;
        ((MovementComponent) this.ralph.getTheComponent(ComponentType.MOVEMENT).get()).move(lastMov, 0, ralph);
    }
    /**
     * Throw a brick with the left arm.
     */
    public void throwBrickLeftArm() {
        for (final Component c : ralph.getComponents()) {
            if (c.getComponent() == ComponentType.THROWBRICK) {
                final double x = Constants.Ralph.RALPH_LEFT_HAND.getX() + ralph.getPosition().getX();
                final double y = ralph.getPosition().getY() + Constants.Ralph.RALPH_LEFT_HAND.getY();
                final Pair<Double, Double> position = new Pair<>(x, y);
                ((ThrowBrickComponent) c).addBrickToThrow(gamePerformance.getBricks(), position);
            }
        }
    }
    /**
     * Throw a brick with the right arm.
     */
    public void throwBrickRightArm() {
        for (final Component c : ralph.getComponents()) {
            if (c.getComponent() == ComponentType.THROWBRICK) {
                final double x = Constants.Ralph.RALPH_RIGHT_HAND.getX() + ralph.getPosition().getX();
                final double y = ralph.getPosition().getY() + Constants.Ralph.RALPH_RIGHT_HAND.getY();
                final Pair<Double, Double> position = new Pair<>(x, y);
                ((ThrowBrickComponent) c).addBrickToThrow(gamePerformance.getBricks(), position);
            }
        }
    }
    /**
     * Getter for the ralph entity.
     * @return the ralph entity.
     */
    @SuppressFBWarnings(value = "EI_EXPOSE_REP", justification = "We need the original object")
    public Entity getRalph() {
        return ralph;
    }
    /**
     * Getter for the time to wait.
     * @return the time to wait.
     */
    private double getTimeToWait() {
        return Constants.Ralph.THROW_TIME / (gamePerformance.getLevel() / Constants.Ralph.LEVEL_DIVIDER);
    }
    /**
     * Update Ralph position, and make him throwing bricks.
     */
    @Override
    public void update() {
        this.move();
        if (System.currentTimeMillis() - lastThrowTime >= this.getTimeToWait()) {
            this.throwBricks();
            lastThrowTime = System.currentTimeMillis();
        }
    }
    /**
     * Make Ralph throw bricks.
     */
    private void throwBricks() {
           this.throwBrickLeftArm();
           this.throwBrickRightArm();
    }
}
