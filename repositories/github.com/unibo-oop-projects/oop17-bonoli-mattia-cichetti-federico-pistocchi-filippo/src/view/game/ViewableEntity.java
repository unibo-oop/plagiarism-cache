package view.game;

import utilities.Direction;

/**
 * This class makes it easier and more readable to send information from the controller 
 * to the main Game View.
 */
public class ViewableEntity {

    private final double position;
    private final double width;
    private final String texture;
    private final int laneNum;
    private final Direction dir;

    /**
     * Simple constructor for an EntityView element.
     * @param position the position of the entity center in its lane.
     * @param width how "long" is the entity.
     * @param texture the texture of this entity.
     * @param laneNum the number of the lane this entity is currently on.
     * @param dir the direction this entity is facing.
     */
    public ViewableEntity(final double position, final double width, 
                            final String texture, final int laneNum,
                            final Direction dir) {
        this.position = position;
        this.width = width;
        this.texture = texture;
        this.laneNum = laneNum;
        this.dir = dir;
    }

    /**
     * Getter for the position of the entity.
     * @return position
     */
    public double getPosition() {
        return position;
    }

    /**
     * getter for the width of this entity.
     * @return width
     */
    public double getWidth() {
        return width;
    }

    /**
     * getter for the texture of this entity.
     * @return texture
     */
    public String getTexture() {
        return texture;
    }

    /**
     * Getter for the lane number of this entity.
     * @return laneNum
     */
    public int getLaneNum() {
        return laneNum;
    }

    /**
     * Getter for the direction this entity is facing.
     * @return direction
     */
    public Direction getDir() {
        return this.dir;
    }

    /**
     * 
     */
    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        long temp;
        temp = Double.doubleToLongBits(position);
        result = prime * result + (int) (temp ^ (temp >>> 32));
        result = prime * result + laneNum;
        result = prime * result + ((texture == null) ? 0 : texture.hashCode());
        return result;
    }

    /**
     * 
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
        ViewableEntity other = (ViewableEntity) obj;
        if (Double.doubleToLongBits(position) != Double.doubleToLongBits(other.position)) {
            return false;
        }
        if (laneNum != other.laneNum) {
            return false;
        }
        if (texture == null) {
            if (other.texture != null) {
                return false;
            }
        } else if (!texture.equals(other.texture)) {
            return false;
        }
        return true;
    }
}
