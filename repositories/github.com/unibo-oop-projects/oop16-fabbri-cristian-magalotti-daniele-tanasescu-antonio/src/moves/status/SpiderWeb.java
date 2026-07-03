package moves.status;

import battle_arena.BattleArena;
import pokemon.Pokemon;
import types.Bug;

public class SpiderWeb extends StatusMove{

    public SpiderWeb() {
        super(  "Spider Web",                                                                                                    //name
                "The user ensnares the target with thin, gooey silk so it can't flee from battle.",                              //description
                new Bug(),                                                                                                       //type
                999,                                                                                                             //accuracy
                10,                                                                                                              //PP                                                                                                                     
                0);                                                                                                              //priority  
    }

    @Override
    public void sideEffect(Pokemon user, Pokemon target, BattleArena battleArena) {
        target.canSwitch = false;
        
    }

}
