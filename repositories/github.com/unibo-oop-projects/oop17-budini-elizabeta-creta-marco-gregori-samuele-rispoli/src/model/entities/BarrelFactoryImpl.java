package model.entities;

import java.awt.Dimension;
/**
 * 
 * An implementation of {@link BarrelFactory}
 *
 */
public class BarrelFactoryImpl implements BarrelFactory {
    
    @Override
    public AbstractBarrel createStandardBarrel(final Double x, final Double y, final Dimension dim) {
        return new StandardBarrel(x, y, dim);
    }
    
    @Override
    public AbstractBarrel createClimbingBarrel(final Double x, final Double y, final Dimension dim) {
        return new ClimbingBarrel(x, y, dim);
    }


}
