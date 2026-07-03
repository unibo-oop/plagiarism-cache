package moves.status;

import battle_arena.BattleArena;
import pokemon.Pokemon;
import status_condition.volatile_status.Confusion;
import types.Fairy;

public class SweetKiss extends StatusMove{

    public SweetKiss() {
        super(  "Sweet Kiss",                                                                                                    //name
                "The user kisses the target with a sweet, angelic cuteness that causes confusion.",                              //description
                new Fairy(),                                                                                                     //type
                0.75,                                                                                                            //accuracy
                10,                                                                                                              //PP                                                                                                                     
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
