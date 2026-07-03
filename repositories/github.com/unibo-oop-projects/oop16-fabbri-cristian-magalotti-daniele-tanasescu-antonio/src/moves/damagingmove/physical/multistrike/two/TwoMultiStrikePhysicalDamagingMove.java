package moves.damagingmove.physical.multistrike.two;

import moves.damagingmove.physical.multistrike.MultiStrikePhysicalDamagingMove;
import types.Type;

public abstract class TwoMultiStrikePhysicalDamagingMove extends MultiStrikePhysicalDamagingMove{

    public TwoMultiStrikePhysicalDamagingMove(String name, String description, int moveBasePower, Type moveType, double moveAccuracy,
                                              double actualCritRange, int minPP, int priority) {
        super(name, description, moveBasePower, moveType, moveAccuracy, actualCritRange, minPP, priority, 2);
    }
    
    @Override
    public void extractNumOfHits() {
        this.numHits = 2;        
    }

}
