package moves.status;

import battle_arena.BattleArena;
import pokemon.Pokemon;
import types.Normal;

public class Tickle extends StatusMove{

    public Tickle() {
            super(  "Tickle",                                                                                                         //name
                    "The user tickles the target into laughing, reducing its Attack and Defense stats.",                              //description
                    new Normal(),                                                                                                     //type
                    1,                                                                                                                //accuracy
                    20,                                                                                                               //PP                                                                                                                     
                    0);                                                                                                               //priority     
    }

    @Override
    public void sideEffect(Pokemon user, Pokemon target, BattleArena battleArena) {
        target.setAlterationAtk(-1, false);
        target.setAlterationDef(-1, false);
        
    }

}
