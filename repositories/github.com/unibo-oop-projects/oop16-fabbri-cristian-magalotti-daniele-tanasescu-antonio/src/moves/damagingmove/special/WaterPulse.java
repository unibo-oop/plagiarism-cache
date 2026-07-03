package moves.damagingmove.special;

import java.util.Random;

import battle_arena.BattleArena;
import pokemon.Pokemon;
import types.Water;

public class WaterPulse extends SpecialDamagingMove{

    public WaterPulse() {
        super(  "Water Pulse",                                                                                                 //name
                "The user attacks the target with a pulsing blast of water.\n" +                                              //description
                "This may also confuse the target.",    
                60,                                                                                                           //base power
                new Water(),                                                                                                  //type
                1,                                                                                                            //accuracy
                critRange1,                                                                                                   //crit range 
                20,                                                                                                           //PP
                0);                                                                                                           //Priority
        this.sideEffect = true;
    }

    @Override
    public void sideEffect(Pokemon user, Pokemon target, BattleArena battleArena) {
        Random random = new Random();
        if(random.nextDouble() < 0.2){
            status_condition.volatile_status.Confusion confusion = new status_condition.volatile_status.Confusion();
            if(!confusion.isContained(target.volatileStatusConditions)){
                confusion.addVolatile(target, target.volatileStatusConditions);    
                //message
            }
            else{
                //already confused
            }
        }
        
    }

}
