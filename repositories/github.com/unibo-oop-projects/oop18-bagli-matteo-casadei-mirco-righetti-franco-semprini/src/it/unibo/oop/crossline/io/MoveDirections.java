package it.unibo.oop.crossline.io;

import com.badlogic.gdx.math.Vector2;

/**
 * Directions for movement.
 */
public enum MoveDirections {
    /**
     * Forward.
     */
    FORWARD {
        @Override
        public Vector2 getDirection() {
            return new Vector2(0, SPEED);
        }

        @Override
        public void print() {
            System.out.println(this.name());
        }
    },
    /**
     * Left.
     */
    LEFT {
        @Override
        public Vector2 getDirection() {
            return new Vector2(-SPEED, 0);
        }

        @Override
        public void print() {
            System.out.println(this.name());
        }
    },
    /**
     * Backward.
     */
    BACKWARD {
        @Override
        public Vector2 getDirection() {
            return new Vector2(0, -SPEED);
        }

        @Override
        public void print() {
            System.out.println(this.name());
        }
    },
    /**
     * Right.
     */
    RIGHT {
        @Override
        public Vector2 getDirection() {
            return new Vector2(SPEED, 0);
        }

        @Override
        public void print() {
            System.out.println(this.name());
        }
    },
    /**
     * Forward left.
     */
    FORWARD_LEFT {
        @Override
        public Vector2 getDirection() {
            return new Vector2(-SPEED, SPEED);
        }

        @Override
        public void print() {
            System.out.println(this.name());
        }
    },
    /**
     * Forward right.
     */
    FORWARD_RIGHT {
        @Override
        public Vector2 getDirection() {
            return new Vector2(SPEED, SPEED);
        }

        @Override
        public void print() {
            System.out.println(this.name());
        }
    },
    /**
     * Backward left.
     */
    BACKWARD_LEFT {
        @Override
        public Vector2 getDirection() {
            return new Vector2(-SPEED, -SPEED);
        }

        @Override
        public void print() {
            System.out.println(this.name());
        }
    },
    /**
     * Backward right.
     */
    BACKWARD_RIGHT {
        @Override
        public Vector2 getDirection() {
            return new Vector2(SPEED, -SPEED);
        }

        @Override
        public void print() {
            System.out.println(this.name());
        }
    };
    /**
     * The movement speed.
     */
    private static final float SPEED = 3f;

    /**
     * @return the direction of action.
     */
    public abstract Vector2 getDirection();

    /**
     * Print name of action.
     */
    public abstract void print();
}
