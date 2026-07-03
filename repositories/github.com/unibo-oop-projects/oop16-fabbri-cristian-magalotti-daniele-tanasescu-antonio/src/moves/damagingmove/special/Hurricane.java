package moves.damagingmove.special;

import java.util.Random;

import battle_arena.BattleArena;
import pokemon.Pokemon;
import types.Flying;

public class Hurricane extends SpecialDamagingMove{

    public Hurricane() {
        super(  "Hurricane",                                                                                          //name
                "A gust of wind is whipped up by wings and launched at the target to inflict damage.",                //description
                110,                                                                                                  //base power
                new Flying(),                                                                                         //type
                0.7,                                                                                                  //accuracy
                critRange1,                                                                                           //crit range 
                10,                                                                                                   //PP
                0);                                                                                                   //Priority
        this.sideEffect = true;
    }

    @Override
    public void sideEffect(Pokemon user, Pokemon target, BattleArena battleArena) {
        Random random = new Random();
        if(random.nextDouble() < 0.3){
            status_condition.volatile_status.Confusion confusion = new status_condition.volatile_status.Confusion();
            if(!confusion.isContained(target.volatileStatusConditions)){
                confusion.addVolatile(target, target.volatileStatusConditions);    
                //message
            }
            else{
                //already confused message
            }
        }
    }

}
