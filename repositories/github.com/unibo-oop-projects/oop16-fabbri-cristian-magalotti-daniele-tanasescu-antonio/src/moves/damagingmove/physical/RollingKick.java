package moves.damagingmove.physical;

import java.util.Random;

import battle_arena.BattleArena;
import pokemon.Pokemon;
import status_condition.volatile_status.Flinch;
import types.Fight;

public class RollingKick extends PhysicalDamagingMove{

    public RollingKick() {
        super(  "Rolling Kick",                                                                                          //name
                "The user lashes out with a quick, spinning kick. This may also make the target flinch.",                //description
                60,                                                                                                      //base power
                new Fight(),                                                                                             //type
                0.85,                                                                                                    //accuracy
                critRange1,                                                                                              //crit range 
                15,                                                                                                      //PP
                0);                                                                                                      //Priority
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
