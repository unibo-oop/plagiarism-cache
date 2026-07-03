package moves.damagingmove.special;

import java.util.Random;

import battle_arena.BattleArena;
import pokemon.Pokemon;
import types.Psychic;
import status_condition.volatile_status.Confusion;

public class Psybeam extends SpecialDamagingMove{

    public Psybeam() {
        super(  "Psybeam",                                                                                              //name
                "The target is attacked with a peculiar ray. This may also leave the target confused.",                 //description
                65,                                                                                                     //base power
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
            if(target.statusCondition == null){
            	Confusion confusion = new Confusion();
                if(!confusion.isContained(target.volatileStatusConditions)){
                    confusion.addVolatile(target, target.volatileStatusConditions);
                }
                else{
                    //message
                }
            }
        }
        
    }

}
