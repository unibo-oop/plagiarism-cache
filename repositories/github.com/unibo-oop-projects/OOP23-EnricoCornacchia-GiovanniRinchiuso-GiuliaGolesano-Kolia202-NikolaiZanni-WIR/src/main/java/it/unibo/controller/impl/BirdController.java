package it.unibo.controller.impl;

import java.util.Set;

import edu.umd.cs.findbugs.annotations.SuppressFBWarnings;
import it.unibo.common.Pair;
import it.unibo.controller.api.Controller;
import it.unibo.model.api.Component;
import it.unibo.model.api.ComponentType;
import it.unibo.model.api.Entity;
import it.unibo.model.api.GamePerformance;
import it.unibo.model.impl.BirdPositionComponent;
import it.unibo.model.impl.EntityFactoryImpl;
import it.unibo.model.impl.MovementComponent;
import it.unibo.utilities.Constants;

/**
 * Class to manage a bird power up.
 */
public class BirdController implements Controller {

    private long lastCreationTime;

    private final GamePerformance gamePerformance;

    /**
     * The BirdController class represents a controller for the bird in the game.
     * It is responsible for controlling the bird's movements and interactions with
     * the game environment.
     * 
     * @param gamePerformance the game performance.
     */
    @SuppressFBWarnings(value = "EI_EXPOSE_REP2", justification = "We need the original object")
    public BirdController(final GamePerformance gamePerformance) {
        this.gamePerformance = gamePerformance;
    }

    /**
     * Retrieves the set of birds in the game.
     *
     * @return the set of birds in the game
     */
    public Set<Entity> getBirds() {
        return this.gamePerformance.getBirds();
    }

    private void createBird() {
        final BirdPositionComponent birdPositionComponent = new BirdPositionComponent();
        final Pair<Double, Double> position = birdPositionComponent.randomPosition();
        final Entity bird = new EntityFactoryImpl(this.gamePerformance).createBird(position);
        this.gamePerformance.addEntity(bird);
    }

    /**
     * Moves the bird entities in the game.
     * This method checks the direction of the bird and moves it accordingly.
     */
    public void moveBird() {
        this.checkDirection();
        for (final Entity bird : this.gamePerformance.getBirds()) {
            for (final Component component : bird.getComponents()) {
                if (component.getComponent() == ComponentType.MOVEMENT) {
                    ((MovementComponent) component).move(1.0, 0.0, bird);
                }
            }
        }
    }

    private void checkDirection() {
        for (final Entity bird : this.gamePerformance.getBirds()) {
            for (final Component component : bird.getComponents()) {
                if (component.getComponent() == ComponentType.MOVEMENT
                        && !((MovementComponent) component).canMove(1.0, 0.0, bird)) {
                    this.gamePerformance.removeEntity(bird);
                }
            }
        }
    }

    private long getTimeByLevel() {
        switch (this.gamePerformance.getLevel()) {
            case 1:
                return Constants.Bird.CREATION_INTERVA_1_B;
            case 2:
                return Constants.Bird.CREATION_INTERVA_2_B;
            case 3:
                return Constants.Bird.CREATION_INTERVA_3_B;
            default:
        }
        return 0;
    }

    /**
     * Updates the state of the bird controller.
     * This method is responsible for creating new birds at regular intervals and
     * moving the existing birds.
     */
    @Override
    public void update() {
        final long currentTime = System.currentTimeMillis();
        if (currentTime - lastCreationTime >= getTimeByLevel()) {
            createBird();
            lastCreationTime = currentTime;
        }
        moveBird();
    }
}
