package moves.damagingmove.physical;

import battle_arena.BattleArena;
import pokemon.Pokemon;
import status_condition.volatile_status.Confusion;
import types.Dragon;

public class Outrage extends PhysicalDamagingMove{

    public Outrage() {
        super(  "Outrage",                                                                                              //name
                "The user rampages and attacks outrageously, then becomes confused.\n",                                 //description                        
                120,                                                                                                     //base power
                new Dragon(),                                                                                           //type
                1,                                                                                                      //accuracy
                critRange1,                                                                                             //crit range 
                10,                                                                                                     //PP
                0);                                                                                                     //Priority
        this.sideEffect = true;
    }

    @Override
    public void sideEffect(Pokemon user, Pokemon target, BattleArena battleArena) {
    	Confusion confusion = new Confusion();
    	if(!confusion.isContained(user.volatileStatusConditions)){
    		confusion.addVolatile(user, user.volatileStatusConditions);
    	}

    }

}

