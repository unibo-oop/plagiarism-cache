package moves.damagingmove.special;

import java.util.Random;

import battle_arena.BattleArena;
import pokemon.Pokemon;
import status_condition.volatile_status.Flinch;
import types.Psychic;

public class Extrasensory extends SpecialDamagingMove{

    public Extrasensory() {
        super(  "Extrasensory",                                                                                         //name
                "The user attacks with an odd, unseeable power.\n"+                                                     //description
                "This may also make the target flinch.",                 
                80,                                                                                                     //base power
                new Psychic(),                                                                                          //type
                1,                                                                                                      //accuracy
                critRange1,                                                                                             //crit range 
                20,                                                                                                     //PP
                0);                                                                                                     //Priority
        this.sideEffect = true;
    }

    @Override
    public void sideEffect(Pokemon user, Pokemon target, BattleArena battleArena) {
        Random random = new Random();
        if(random.nextDouble() < 0.1){
            new Flinch().addVolatile(target, target.volatileStatusConditions);
        }
        
    }

}
