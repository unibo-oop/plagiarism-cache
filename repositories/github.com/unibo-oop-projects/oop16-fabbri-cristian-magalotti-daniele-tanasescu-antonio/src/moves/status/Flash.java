package moves.status;

import abilities.statisticsalterationondemand.*;
import battle_arena.BattleArena;
import pokemon.Pokemon;
import types.Normal;

public class Flash extends StatusMove{

    public Flash() {
        super(  "Flash",                                                                                                          //name
                "The user flashes a bright light that cuts the target's accuracy.",                            //description
                new Normal(),                                                                                                    //type
                1,                                                                                                               //accuracy
                20,                                                                                                              //PP                                                                                                                     
                0);                                                                                                              //priority  
    }

    @Override
    public void sideEffect(Pokemon user, Pokemon target, BattleArena battleArena) {
        target.setAlterationAccuracy(-1, false);                        
    }
}
