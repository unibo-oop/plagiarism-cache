package moves.damagingmove.physical;

import java.util.Random;

import battle_arena.BattleArena;
import pokemon.Pokemon;
import status_condition.volatile_status.Flinch;
import types.Ghost;

public class Astonish extends PhysicalDamagingMove{

    public Astonish() {
        super(  "Astonish",                                                                                                     //name
                "The user attacks the target while shouting in a startling fashion.\n"+                                         //description  
                "This may also make the target flinch.",                                                           
                30,                                                                                                             //base power
                new Ghost(),                                                                                                    //type
                1,                                                                                                              //accuracy
                critRange1,                                                                                                     //crit range 
                15,                                                                                                             //PP
                0);                                                                                                             //Priority
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
