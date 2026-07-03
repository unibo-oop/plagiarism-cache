package moves.status;

import battle_arena.BattleArena;
import pokemon.Pokemon;
import status_condition.volatile_status.Confusion;
import types.Normal;

public class Supersonic extends StatusMove{

    public Supersonic() {
        super(  "Supersonic",                                                                                                    //name
                "The user generates odd sound waves from its body that confuse the target.",                                     //description
                new Normal(),                                                                                                    //type
                0.55,                                                                                                               //accuracy
                20,                                                                                                              //PP                                                                                                                     
                0);                                                                                                              //priority  
    }

    @Override
    public void sideEffect(Pokemon user, Pokemon target, BattleArena battleArena) {
    	Confusion confusion = new Confusion();
        if(!confusion.isContained(target.volatileStatusConditions)){
            confusion.addVolatile(target, target.volatileStatusConditions);
        }
        
    }

}
