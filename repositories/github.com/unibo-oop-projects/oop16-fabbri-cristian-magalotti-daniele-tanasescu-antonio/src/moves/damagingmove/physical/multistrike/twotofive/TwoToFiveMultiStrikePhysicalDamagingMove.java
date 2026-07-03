package moves.damagingmove.physical.multistrike.twotofive;

import java.util.Random;

import moves.damagingmove.physical.multistrike.MultiStrikePhysicalDamagingMove;
import types.Type;

public abstract class TwoToFiveMultiStrikePhysicalDamagingMove extends MultiStrikePhysicalDamagingMove{

    public TwoToFiveMultiStrikePhysicalDamagingMove(String name, String description, int moveBasePower, Type moveType, double moveAccuracy,
            double actualCritRange, int minPP, int priority) {
        super(name, description, moveBasePower, moveType, moveAccuracy, actualCritRange, minPP, priority, 5);
    }

    @Override
    public void extractNumOfHits() {
        Random random = new Random();
        int probability = random.nextInt(1000);
        if(probability < 333){          //33.3%
            this.numHits = 2;
        }
        else if(probability < 666){     //other 33.3%
            this.numHits = 3;
        }
        else if(probability < 833){     //other 16.7%
            this.numHits = 4;
        }
        else{                           //other 16.7%
            this.numHits = 5;
        }
        
    }
    
}
