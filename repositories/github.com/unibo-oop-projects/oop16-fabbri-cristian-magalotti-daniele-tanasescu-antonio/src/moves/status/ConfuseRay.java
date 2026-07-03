package moves.status;

import battle_arena.BattleArena;
import pokemon.Pokemon;
import types.Ghost;
import status_condition.volatile_status.Confusion;

public class ConfuseRay extends StatusMove{

    public ConfuseRay() {
        super(  "Confuse Ray",                                                                                                   //name
                "The target is exposed to a sinister ray that triggers confusion.",                                              //description
                new Ghost(),                                                                                                     //type
                1,                                                                                                               //accuracy
                10,                                                                                                              //PP                                                                                                                     
                0);                                                                                                              //priority  
    }

    @Override
    public void sideEffect(Pokemon user, Pokemon target, BattleArena battleArena) {
    	 Confusion confusion = new Confusion();
         if(!confusion.isContained(target.volatileStatusConditions)){
             confusion.addVolatile(target, target.volatileStatusConditions);
             //message
         }
         else{
             //message
         }
    }

}
