package moves.damagingmove.physical;

import java.util.Random;

import battle_arena.BattleArena;
import pokemon.Pokemon;
import status_condition.volatile_status.Flinch;
import types.Steel;

public class IronHead extends PhysicalDamagingMove{

    public IronHead() {
        super(  "Iron Head      ",                                                                                      //name
                "The user slams the target with its steel-hard head.\n"+                                                //description
                "This may also make the target flinch.",                        
                80,                                                                                                     //base power
                new Steel(),                                                                                            //type
                1,                                                                                                      //accuracy
                critRange1,                                                                                             //crit range 
                15,                                                                                                     //PP
                0);                                                                                                     //Priority
    }

    @Override
    public void sideEffect(Pokemon user, Pokemon target, BattleArena battleArena) {
        Random random = new Random();
        if(random.nextDouble() < 0.3){
            new Flinch().addVolatile(target, target.volatileStatusConditions);
        }
                
    }

}
