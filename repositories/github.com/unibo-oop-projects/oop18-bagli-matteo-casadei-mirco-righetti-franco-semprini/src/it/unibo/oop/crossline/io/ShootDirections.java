package it.unibo.oop.crossline.io;

import com.badlogic.gdx.math.Vector2;

/**
 * Shooting directions.
 */
public enum ShootDirections {
    /**
     * Up.
     */
    UP {
        @Override
        public Vector2 getDirection() {
            return new Vector2(0, 1);
        }

        @Override
        public void print() {
            System.out.println(this.name());
        }

        @Override
        public Vector2 setDirection(final float x, final float y) {
            return new Vector2(x, y);
        }
    },
    /**
     * Left.
     */
    LEFT {
        @Override
        public Vector2 getDirection() {
            return new Vector2(-1, 0);
        }

        @Override
        public void print() {
            System.out.println(this.name());
        }

        @Override
        public Vector2 setDirection(final float x, final float y) {
            return new Vector2(x, y);
        }
    },
    /**
     * Down.
     */
    DOWN {
        @Override
        public Vector2 getDirection() {
            return new Vector2(0, -1);
        }

        @Override
        public void print() {
            System.out.println(this.name());
        }

        @Override
        public Vector2 setDirection(final float x, final float y) {
            return new Vector2(x, y);
        }
    },
    /**
     * Right.
     */
    RIGHT {
        @Override
        public Vector2 getDirection() {
            return new Vector2(1, 0);
        }

        @Override
        public void print() {
            System.out.println(this.name());
        }

        @Override
        public Vector2 setDirection(final float x, final float y) {
            return new Vector2(x, y);
        }
    },
    /**
     * Up left.
     */
    UP_LEFT {
        @Override
        public Vector2 getDirection() {
            return new Vector2(-1, 1);
        }

        @Override
        public void print() {
            System.out.println(this.name());
        }

        @Override
        public Vector2 setDirection(final float x, final float y) {
            return new Vector2(x, y);
        }
    },
    /**
     * Up right.
     */
    UP_RIGHT {
        @Override
        public Vector2 getDirection() {
            return new Vector2(1, 1);
        }

        @Override
        public void print() {
            System.out.println(this.name());
        }

        @Override
        public Vector2 setDirection(final float x, final float y) {
            return new Vector2(x, y);
        }
    },
    /**
     * Down left.
     */
    DOWN_LEFT {
        @Override
        public Vector2 getDirection() {
            return new Vector2(-1, -1);
        }

        @Override
        public void print() {
            System.out.println(this.name());
        }

        @Override
        public Vector2 setDirection(final float x, final float y) {
            return new Vector2(x, y);
        }
    },
    /**
     * Down right.
     */
    DOWN_RIGHT {
        @Override
        public Vector2 getDirection() {
            return new Vector2(1, -1);
        }

        @Override
        public void print() {
            System.out.println(this.name());
        }

        @Override
        public Vector2 setDirection(final float x, final float y) {
            return new Vector2(x, y);
        }
    },
    /**
     * Xy.
     */
    XY {
        @Override
        public Vector2 getDirection() {
            return new Vector2(0, 0);
        }

        @Override
        public Vector2 setDirection(final float x, final float y) {
            return new Vector2(x, y);
        }

        @Override
        public void print() {
            System.out.println(this.name());
        }
    };

    /**
     * @return the direction of shoot vector.
     */
    public abstract Vector2 getDirection();

    /**
     * Set the direction of shoot vector.
     * 
     * @param x the x coordinate
     * @param y the y coordinate
     * @return the direction of shoot vector
     */
    public abstract Vector2 setDirection(float x, float y);

    /**
     * Print name of action.
     */
    public abstract void print();
}
