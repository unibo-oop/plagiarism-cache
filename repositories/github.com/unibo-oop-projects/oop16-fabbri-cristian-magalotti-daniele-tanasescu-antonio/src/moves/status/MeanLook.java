package moves.status;

import battle_arena.BattleArena;
import pokemon.Pokemon;
import types.Normal;

public class MeanLook extends StatusMove{

    public MeanLook() {
        super(  "Mean Look",                                                                                                     //name
                "The user pins the target with a dark, arresting look. The target becomes unable to flee.",                      //description
                new Normal(),                                                                                                    //type
                999,                                                                                                             //accuracy
                5,                                                                                                               //PP                                                                                                                     
                0);                                                                                                              //priority  
    }

    @Override
    public void sideEffect(Pokemon user, Pokemon target, BattleArena battleArena) {
        target.canSwitch = false;
        
    }

}
