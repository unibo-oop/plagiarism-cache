package moves.status;

import battle_arena.BattleArena;
import pokemon.Pokemon;
import types.Flying;

public class FeatherDance extends StatusMove{

    public FeatherDance() {
        super(  "Feather Dance",                                                                                                 //name
                "The user covers the target's body with a mass of down that harshly lowers its Attack stat.",                    //description
                new Flying(),                                                                                                    //type
                1,                                                                                                               //accuracy
                15,                                                                                                              //PP                                                                                                                     
                0);                                                                                                              //priority  
    }

    @Override
    public void sideEffect(Pokemon user, Pokemon target, BattleArena battleArena) {
        target.setAlterationAtk(-2, false);  
    }

}
