package moves.damagingmove.special;

import java.util.Random;

import battle_arena.BattleArena;
import pokemon.Pokemon;
import status_condition.volatile_status.Flinch;
import types.Dragon;

public class Twister extends SpecialDamagingMove{

    public Twister() {
        super(  "Twister",                                                                                             //name
                "The user whips up a vicious tornado to tear at the opposing Pokémon."+                                //description
                "This may also make them flinch.",              
                40,                                                                                                    //base power
                new Dragon(),                                                                                          //type
                1,                                                                                                     //accuracy
                critRange1,                                                                                            //crit range 
                20,                                                                                                    //PP
                0);                                                                                                    //Priority
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
