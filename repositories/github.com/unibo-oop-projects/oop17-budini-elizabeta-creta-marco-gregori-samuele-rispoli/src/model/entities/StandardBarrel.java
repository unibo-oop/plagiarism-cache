package model.entities;

import java.awt.Dimension;

/**
 * 
 * A standard barrel that will fall while it's not on a floor.
 *
 */

public class StandardBarrel extends AbstractBarrelImpl{

    public StandardBarrel(Double x, Double y, Dimension dim) {
        super(x, y, dim);
    }

    @Override
    protected void checkDirection() {
        if(getStatus() == EntityStatus.Falling && this.getPrevStatus() == EntityStatus.OnTheFloor) {
            changeDir();
        }
        else if(getStatus() == EntityStatus.OnTheFloor){
            this.addMovement(getCurrentDirection());
        }
        setPrevStatus(getStatus());
    }
    
    protected void changeDir() {
        if(getCurrentDirection() == Movement.RIGHT) {
            this.addMovement(Movement.LEFT);
            setDirection(Movement.LEFT);
        }
        else if(getCurrentDirection() == Movement.LEFT) {
            this.addMovement(Movement.RIGHT);
            setDirection(Movement.RIGHT);
        }
    }
}
