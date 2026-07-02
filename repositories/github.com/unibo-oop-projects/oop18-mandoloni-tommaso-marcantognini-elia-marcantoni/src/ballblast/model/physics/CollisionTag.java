package ballblast.model.physics;

/**
 * Represents a value used to decide if an object type can collide with another.
 */
public enum CollisionTag {

    /**
     * Collision tag for the player object.
     */
    PLAYER {
        @Override
        public boolean canCollideWith(final CollisionTag other) {
            return other == WALL || other == BALL || other == POWERUP;
        }
    },

    /**
     * Collision tag for all balls.
     */
    BALL {
        @Override
        public boolean canCollideWith(final CollisionTag other) {
            return other == WALL || other == PLAYER || other == BULLET;
        }
    },

    /**
     * Collision tag for all walls.
     */
    WALL {
        @Override
        public boolean canCollideWith(final CollisionTag other) {
            return other != WALL;
        }
    },

    /**
     * Collision tag for all powerups.
     */
    POWERUP {
        @Override
        public boolean canCollideWith(final CollisionTag other) {
            return other == WALL || other == PLAYER;
        }
    },

    /**
     * Collision tag for all bullets.
     */
    BULLET {
        @Override
        public boolean canCollideWith(final CollisionTag other) {
            return other == WALL || other == BALL;
        }
    };

    /**
     * Returns true if objects of this type can collide with objects of the given
     * one.
     * 
     * @param other the other type.
     * @return true if the objects can collide.
     */
    public boolean canCollideWith(final CollisionTag other) {
        return true;
    }

}
