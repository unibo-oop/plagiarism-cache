package moves.damagingmove.physical.multistrike.twotofive;

import battle_arena.BattleArena;
import pokemon.Pokemon;
import types.Fight;

public class ArmThrust extends TwoToFiveMultiStrikePhysicalDamagingMove{

    public ArmThrust() {
        super(  "Arm Thrust",                                                                           //name
                "The user strikes the target with a hard bone two to five times in a row.",             //description
                20,                                                                                     //base power
                new Fight(),                                                                            //type
                1,                                                                                      //accuracy
                critRange1,                                                                             //crit range 
                15,                                                                                     //PP
                0);                                                                                     //Priority
    }

    @Override
    public void sideEffect(Pokemon user, Pokemon target, BattleArena battleArena) {
        // TODO Auto-generated method stub
        
    }

}
