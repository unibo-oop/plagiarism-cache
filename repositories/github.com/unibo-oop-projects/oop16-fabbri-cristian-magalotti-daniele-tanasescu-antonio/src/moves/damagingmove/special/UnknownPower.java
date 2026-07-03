package moves.damagingmove.special;

import java.util.Random;

import battle_arena.BattleArena;
import pokemon.Pokemon;
import types.Psychic;

public class UnknownPower extends SpecialDamagingMove{

    public UnknownPower() {
        super(  "Unknown Power",                                                                                              //name
                "An unknown power badly damages and confuses the target, at a life price.",                           		  //description
                100,                                                                                                          //base power
                new Psychic(),                                                                                                //type
                999,                                                                                                          //accuracy
                critRange1,                                                                                                   //crit range 
                10,                                                                                                           //PP
                0);                                                                                                           //Priority
        this.sideEffect = true;
    }

    @Override
    public void sideEffect(Pokemon user, Pokemon target, BattleArena battleArena) {
    	user.takeDamage(user.getMaxHp()/10, this.hasRecoil());
    	status_condition.volatile_status.Confusion confusion = new status_condition.volatile_status.Confusion();
        if(!confusion.isContained(target.volatileStatusConditions)){
            confusion.addVolatile(target, target.volatileStatusConditions);    
        }
        else{
            //already confused message
        }
    }
    

}
