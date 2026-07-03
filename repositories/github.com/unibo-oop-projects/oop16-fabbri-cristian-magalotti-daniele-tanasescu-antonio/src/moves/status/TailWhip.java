package moves.status;

import abilities.statisticsalterationondemand.*;
import battle_arena.BattleArena;
import pokemon.Pokemon;
import types.Normal;

public class TailWhip extends StatusMove{

    public TailWhip() {
        super(  "Tail Whip",                                                                                                      //name
                "The user wags its tail cutely, making opposing Pokemon less wary and lowering their Defense stat.",              //description
                new Normal(),                                                                                                     //type
                1,                                                                                                                //accuracy
                30,                                                                                                               //PP                                                                                                                     
                0);                                                                                                               //priority     
    }

    @Override
    public void sideEffect(Pokemon user, Pokemon target, BattleArena battleArena) {
        target.setAlterationDef(-1, false);                        
    }
}