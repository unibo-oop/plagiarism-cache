package moves.damagingmove.physical.weightdependent;

import java.util.Random;

import battle_arena.BattleArena;
import pokemon.Pokemon;
import status_condition.volatile_status.Flinch;
import types.Fight;

public class LowKick extends WeightDependentPhysicalDamagingMove{

    public LowKick() {
        super(  "Low Kick",                                                                                             //name
                "A powerful low kick that makes the target fall over.\n"+                                               //description
                "The heavier the target, the greater the move's power.",                       
                new Fight(),                                                                                            //type
                1,                                                                                                      //accuracy
                critRange1,                                                                                             //crit range 
                20,                                                                                                     //PP
                0,                                                                                                      //Priority
                new int[]{10,25,50,100,200},                                                                            //weight ranges
                new int[]{20,40,60,80,100,120});                                                                        //weight-dep. damages
        this.sideEffect = true;
    }

    @Override
    public void sideEffect(Pokemon user, Pokemon target, BattleArena battleArena) {
        Random random = new Random();
        if(random.nextDouble() < 0.3){
        	new Flinch().addVolatile(target, target.volatileStatusConditions);
        }
        
    }

}
