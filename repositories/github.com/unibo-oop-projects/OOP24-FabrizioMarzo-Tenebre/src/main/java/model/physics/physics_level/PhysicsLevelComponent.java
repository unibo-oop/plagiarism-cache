package model.physics.physics_level;

import model.level.types.Level;

/**
 * Interface representing the physics update component for a game level.
 * Provides a method to update the physics state of the level over time.
 */
public interface PhysicsLevelComponent {

        /**
         * Updates the physics state of the given level based on the elapsed time.
         *
         * @param lv the Level instance to update
         * @param dt the elapsed time in milliseconds since the last update
         */
        public void updateLevel(final Level lv, final int dt);

}
