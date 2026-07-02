package it.unibo.controller.impl;
import java.util.Set;

import edu.umd.cs.findbugs.annotations.SuppressFBWarnings;
import it.unibo.controller.api.Controller;
import it.unibo.model.api.Component;
import it.unibo.model.api.ComponentType;
import it.unibo.model.api.Entity;
import it.unibo.model.api.GamePerformance;
import it.unibo.model.impl.MovementComponent;
import it.unibo.utilities.Constants.Brick;


/**
 * Controller for the bricks.
 */
public class BrickController implements Controller {

    private final GamePerformance gamePerformance;

    /**
     * Constructor for the BrickController.
     * @param gamePerformance the game performance, where every entity is stored.
     */
    @SuppressFBWarnings(value = "EI_EXPOSE_REP2", justification = "We need the original object")
    public BrickController(final GamePerformance gamePerformance) {
        this.gamePerformance = gamePerformance;
    }
    /**
     * Getter for the bricks.
     * @return the set of bricks.
     */
    public Set<Entity> getBricks() {
        return this.gamePerformance.getBricks();
    }
    /**
     * make the bricks fall.
     */
    @Override
    public void update() {
        this.checkBricks();
        for (final Entity brick : this.getBricks()) {
            for (final Component component : brick.getComponents()) {
                if (component.getComponent() == ComponentType.MOVEMENT) {
                    ((MovementComponent) component).move(0.0, this.getBrickSpeedByLevel(), brick);
                }
            }
        }
    }
    /**
     * Check if the bricks are still in the game. 
     * If not, remove them by the set of bricks and also by the set of entities in the gamePerformance.
     */
    private void checkBricks() {
        for (final Entity brick : this.getBricks()) {
            for (final Component component : brick.getComponents()) {
                if (component.getComponent() == ComponentType.MOVEMENT 
                && !((MovementComponent) component).canMove(0.0, this.getBrickSpeedByLevel(), brick)) {
                    //System.out.println(this.gamePerformance.getBricks().size());
                    this.gamePerformance.removeEntity(brick);
                    //System.out.println("Brick removed");
                }
            }
        }
    }

    private double getBrickSpeedByLevel() {
        switch (this.gamePerformance.getLevel()) {
            case 1:
                return Brick.BRICK_SPEED_LEVEL_1;
            case 2:
                return Brick.BRICK_SPEED_LEVEL_2;
            case 3:
                return Brick.BRICK_SPEED_LEVEL_3;
            default:
        }
        return 0;
    }
    /**
     * Add a brick to the game, used for testing.
     * @param brick the brick to add.
     */
    public void addBrick(final Entity brick) {
        this.gamePerformance.addEntity(brick);
    }
}
