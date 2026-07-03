package moves.damagingmove.physical;

import java.util.Random;

import battle_arena.BattleArena;
import pokemon.Pokemon;
import status_condition.volatile_status.Flinch;
import types.Psychic;

public class ZenHeadbutt extends PhysicalDamagingMove{

    public ZenHeadbutt() {
        super(  "Zen Headbutt",                                                                                         //name
                "The user focuses its willpower to its head and attacks the target\n"+          		        //description
                "This may also make the target flinch.",                        
                80,                                                                                                     //base power
                new Psychic(),                                                                                          //type
                0.9,                                                                                                    //accuracy
                critRange1,                                                                                             //crit range 
                15,                                                                                                     //PP
                0);                                                                                                     //Priority
        this.sideEffect = true;
    }

    @Override
    public void sideEffect(Pokemon user, Pokemon target, BattleArena battleArena) {
        Random random = new Random();
        if(random.nextDouble() < 0.2){
            new Flinch().addVolatile(target, target.volatileStatusConditions);
        }
        
    }

}
