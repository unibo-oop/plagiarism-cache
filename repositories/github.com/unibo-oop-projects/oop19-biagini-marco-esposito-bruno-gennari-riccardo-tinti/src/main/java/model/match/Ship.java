package model.match;

import model.enums.Orientation;
import model.enums.ShipType;

/**
 * Represents a generic ship of the game
 * 
 */
public class Ship implements GameObject {

    private final int size;
    private int damage;
    private boolean destroyed;
    private final ShipType shipType;
    private Orientation orientation;

    public Ship(final ShipType shipType) {
        this.shipType = shipType;
        this.size = shipType.getSize();
        this.damage = 0;
        this.destroyed = false;
        this.orientation = Orientation.HORIZONTAL;
    }

    @Override
    public int getSize() {
        return this.size;
    }

    @Override
    public boolean hit() {

        if (this.damage == this.size) {
            this.destroyed = true;
            return this.destroyed;
        } else {
            this.damage++;
            if (this.damage == this.size) {
                this.destroyed = true;
                return this.destroyed;
            }
            return this.destroyed;
        }

    }

    public int getDamage() {
        return this.damage;
    }

    public boolean isDestroyed() {
        return this.destroyed;
    }

    public ShipType getShipType() {
        return this.shipType;
    }

    public Orientation getOrientation() {
        return this.orientation;
    }

    public void setOrientation(final Orientation orientation) {
        this.orientation = orientation;
    }

}
