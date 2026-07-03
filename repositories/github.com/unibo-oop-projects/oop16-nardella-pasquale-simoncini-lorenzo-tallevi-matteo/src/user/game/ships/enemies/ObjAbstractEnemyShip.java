package user.game.ships.enemies;

import user.enums.Objects;
import user.game.ships.ObjIAShip;

/**
 * This class represents a generic type of enemy.
 */
public abstract class ObjAbstractEnemyShip extends ObjIAShip {

    // the rank of the ship
    private int rank;

    @Override
    protected void chooseTarget() {
        this.setTarget(z().instanceNearest(this.getX(), this.getY(), Objects.PLAYER_SHIP.getValue(),
                Objects.ALLY_SHIP.getValue()));
    }

    /**
     * This method returns the rank of the ship.
     * 
     * @return the rank of the ship
     */
    protected int getRank() {
        return rank;
    }

    /**
     * This method sets the rank of the ship.
     * 
     * @param rank
     *            the rank to set
     */
    protected void setRank(final int rank) {
        this.rank = rank;
    }

    /**
     * This method sets the type of the enemy ship.
     * 
     * @param type
     *            the type to set
     */
    public abstract void setType(int type);
}
