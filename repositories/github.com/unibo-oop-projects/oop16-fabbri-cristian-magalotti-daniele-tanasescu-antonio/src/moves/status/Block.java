package moves.status;

import battle_arena.BattleArena;
import pokemon.Pokemon;
import types.Normal;

public class Block extends StatusMove{

    public Block() {
        super(  "Block",                                                                                                         //name
                "The user blocks the target's way with arms spread wide to prevent escape.",                                     //description
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
