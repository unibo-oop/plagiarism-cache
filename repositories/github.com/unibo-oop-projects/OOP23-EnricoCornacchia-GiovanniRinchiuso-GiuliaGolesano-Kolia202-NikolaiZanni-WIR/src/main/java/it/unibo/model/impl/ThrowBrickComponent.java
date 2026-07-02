package it.unibo.model.impl;

import it.unibo.model.api.ComponentType;
import it.unibo.model.api.Entity;
import it.unibo.model.api.GamePerformance;
import it.unibo.utilities.Constants;

import java.util.Set;

import edu.umd.cs.findbugs.annotations.SuppressFBWarnings;
import it.unibo.common.Pair;

/**
 * Component for the brick throwing.
 */
public class ThrowBrickComponent extends AbstractComponent {

        private final EntityFactoryImpl entityFactoryImpl;
        private final GamePerformance gamePerformance;
        private boolean blocked;
        /**
         * Constructor for the ThrowBrickComponent.
         * @param gamePerformance the game performance.
         */
        @SuppressFBWarnings(value = "EI_EXPOSE_REP2", justification = "We need the original object")
        public ThrowBrickComponent(final GamePerformance gamePerformance) {
            this.gamePerformance = gamePerformance;
            this.entityFactoryImpl = new EntityFactoryImpl(this.gamePerformance);
            blocked = false;
        }
        /**
         * Create a new brick in the position passed as parameter and add it to the set of bricks.
         * @param bricks the set of bricks.
         * @param position  the position where the brick will be created.
         */
        public void addBrickToThrow(final Set<Entity> bricks, final Pair<Double, Double> position) {
                if (!this.blocked && position.getY() < Constants.GameEdges.DOWN_WALL_1 
                && position.getY() > Constants.GameEdges.UP_WALL 
                && position.getX() < Constants.GameEdges.RIGHT_WALL 
                && position.getX() > Constants.GameEdges.LEFT_WALL) {
                    gamePerformance.addEntity(entityFactoryImpl.createBrick(position));
            }
        }
        /**
         * Set the component as blocked.
         */
        public void setBlocked() {
            this.blocked = true;
        }
        /**
         * Set the component as unblocked.
         */
        public void setUnblocked() {
            this.blocked = false;
        }
        /**
         * Getter for the component status.
         * @return the component status.
         */
        public boolean isBlocked() {
            return this.blocked;
        }
        /**
         * Getter for the component type.
         * @return the component type.
         */
        @Override
        public ComponentType getComponent() {
            return ComponentType.THROWBRICK;
        }
}
