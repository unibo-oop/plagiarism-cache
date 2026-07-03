package moves.damagingmove.special;

import java.util.Random;

import battle_arena.BattleArena;
import pokemon.Pokemon;
import status_condition.volatile_status.Flinch;
import types.Flying;

public class AirSlash extends SpecialDamagingMove{

    public AirSlash() {
        super(  "Air Slash",                                                                                          //name
                "The user attacks with a blade of air that slices even the sky.\n"+                                   //description
                "This may also make the target flinch.",                
                75,                                                                                                   //base power
                new Flying(),                                                                                         //type
                0.95,                                                                                                 //accuracy
                critRange1,                                                                                           //crit range 
                15,                                                                                                   //PP
                0);                                                                                                   //Priority
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
