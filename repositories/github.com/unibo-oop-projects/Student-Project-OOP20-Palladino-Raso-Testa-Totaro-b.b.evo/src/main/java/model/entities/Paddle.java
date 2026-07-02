package model.entities;

import java.util.HashMap;
import java.util.Map;

import controller.input.ControllerInput;
import controller.input.PaddleComponentInput;
import model.physics.PaddleComponentPhysics;
import model.utilities.Boundaries;
import model.utilities.DirVector;
import model.utilities.Position;
import view.graphics.AdapterGraphics;
import view.graphics.PaddleComponentGraphics;

public final class Paddle extends GameObjectImpl {

    private static final long serialVersionUID = -854724973379080675L;
    private static final double PADDLE_SPEED = 0.4;
    private final Map<GameObject, Boundaries> hit = new HashMap<>();
    private final String tPath;

    private  Paddle(final Position pos, final int height, final int width, final String tPath) {
        super(pos, new DirVector(0, 0), PADDLE_SPEED, height, width, new PaddleComponentPhysics(), 
                new PaddleComponentInput(), new PaddleComponentGraphics(tPath));
        this.tPath = tPath;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void updatePhysics(final int timeElapsed, final GameBoardImpl world) {
        super.getComponentPhysics().update(timeElapsed, this, world);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void updateInput(final ControllerInput controller) {
        super.getComponentInput().update(this, controller);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void updateGraphics(final AdapterGraphics graphicsAdapter) {
        this.getComponentGraphics().update(this, graphicsAdapter);
    }

    /**
     * 
     * @return a map containing the info of the ball in the game to 
     * facilitate the bounce with the paddle
     */
    public Map<GameObject, Boundaries> getHit() {
        return this.hit;
    }

    /**
     * 
     * @return texture path
     */
    public String getTexturePath() {
        return tPath;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((hit == null) ? 0 : hit.hashCode());
        result = prime * result + ((tPath == null) ? 0 : tPath.hashCode());
        return result;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean equals(final Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Paddle other = (Paddle) obj;
        if (hit == null) {
            if (other.hit != null) {
                return false;
            }
        } else if (!hit.equals(other.hit)) {
            return false;
        }
        if (tPath == null) {
            if (other.tPath != null) {
                return false;
            }
        } else if (!tPath.equals(other.tPath)) {
            return false;
        }
        return true;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String toString() {
        return "Paddle [hit=" + hit + ", tPath=" + tPath + "]";
    }

    public static final class Builder {

        private int height;
        private int width;
        private Position pos;
        private String tPath;

        /**
         * Set the new paddle width.
         * @param width width to set
         * @return himself
         */
        public Builder width(final int width) {
            this.width = width;
            return this;
        }

        /**
         * Set the new paddle height.
         * @param height height to set
         * @return himself
         */
        public Builder height(final int height) {
            this.height = height;
            return this;
        }

        /**
         * Set the new paddle position.
         * @param pos position to set
         * @return himself
         */
        public Builder position(final Position pos) {
            this.pos = pos;
            return this;
        }

        /**
         * Set the new paddle texture path.
         * @param tPath texture path to set
         * @return himself
         */
        public Builder texturePath(final String tPath) {
            this.tPath = tPath;
            return this;
        }

        /**
         * Builds the paddle if the characteristics are valid.
         * @return the new Paddle object
         */
        public Paddle build() {
            if (this.height <= 0 || this.width <= 0 || this.pos == null || this.tPath == null) {
                throw new IllegalStateException();
            }
            return new Paddle(this.pos, this.height, this.width, tPath);
        }
    }
}
