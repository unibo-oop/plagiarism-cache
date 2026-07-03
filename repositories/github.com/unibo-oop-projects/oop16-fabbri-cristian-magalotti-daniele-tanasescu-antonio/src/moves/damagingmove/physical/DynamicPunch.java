package moves.damagingmove.physical;

import battle_arena.BattleArena;
import pokemon.Pokemon;
import status_condition.volatile_status.Confusion;
import types.Fight;

public class DynamicPunch extends PhysicalDamagingMove{

    public DynamicPunch() {
        super(  "Dynamic Punch",                                                                                //name
                "The user punches the target with full, concentrated power.\n"+                                 //description
                "This confuses the target if it hits.",                    
                100,                                                                                            //base power
                new Fight(),                                                                                    //type
                0.5,                                                                                            //accuracy
                critRange1,                                                                                     //crit range 
                5,                                                                                              //PP
                0);                                                                                             //Priority
        this.sideEffect = true;
    }

    @Override
    public void sideEffect(Pokemon user, Pokemon target, BattleArena battleArena) {
        Confusion confusion = new Confusion();
        if(!confusion.isContained(target.volatileStatusConditions)){
            confusion.addVolatile(target, target.volatileStatusConditions);
        }
        
    }

}
