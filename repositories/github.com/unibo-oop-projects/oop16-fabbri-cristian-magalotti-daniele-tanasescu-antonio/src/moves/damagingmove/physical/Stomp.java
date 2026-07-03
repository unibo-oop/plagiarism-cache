package moves.damagingmove.physical;

import java.util.Random;

import battle_arena.BattleArena;
import pokemon.Pokemon;
import status_condition.volatile_status.Flinch;
import types.Normal;

public class Stomp extends PhysicalDamagingMove{

    public Stomp() {
        super(  "Stomp",                                                                                                //name
                "The target is stomped with a big foot. This may also make the target flinch. ",                        //description
                65,                                                                                                     //base power
                new Normal(),                                                                                           //type
                1,                                                                                                      //accuracy
                critRange1,                                                                                             //crit range 
                35,                                                                                                     //PP
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
