
package model.entity;
/**
 * 
 * the basic entity core.
 * 
 *
 */
public abstract class AbstractEntity implements Entity {
    /**
     * the x of the entity.
     */
    private int x;
    /**
     * the x of the entity.
     */
    private int y;
    /**
     * 
     * @param x              the x of the entity
     * @param y              the y of the entity
     */
    protected AbstractEntity(final int x, final int y) {
        this.x = x;
        this.y = y;
    }

    /**
     * @return the x
     */
    public int getX() {
        return this.x;
    }

    /**
     * @return the y
     */
    public int getY() {
        return this.y;
    }
    /**
     * @param x the x to set
     */
    protected void setX(final int x) {
        this.x = x;
    }

    /**
     * @param y the y to set
     */
    protected void setY(final int y) {
        this.y = y;
    }

    /**
     * @return the entityTypeName
     */
    public abstract EntityTypeNameEnum getEntityType();
}
