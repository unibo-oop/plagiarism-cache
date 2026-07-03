package moves.damagingmove.special;

import java.util.Random;

import battle_arena.BattleArena;
import pokemon.Pokemon;
import types.Psychic;

public class Confusion extends SpecialDamagingMove{

    public Confusion() {
        super(  "Confusion",                                                                                                  //name
                "The target is hit by a weak telekinetic force. This may also confuse the target.",                           //description
                50,                                                                                                           //base power
                new Psychic(),                                                                                                //type
                1,                                                                                                            //accuracy
                critRange1,                                                                                                   //crit range 
                25,                                                                                                           //PP
                0);                                                                                                           //Priority
        this.sideEffect = true;
    }

    @Override
    public void sideEffect(Pokemon user, Pokemon target, BattleArena battleArena) {
        Random random = new Random();
        if(random.nextDouble() < 0.1){
        	status_condition.volatile_status.Confusion confusion = new status_condition.volatile_status.Confusion();
            if(!confusion.isContained(target.volatileStatusConditions)){
                confusion.addVolatile(target, target.volatileStatusConditions);    
            }
            else{
                //already confused message
            }
        }
    }

}
