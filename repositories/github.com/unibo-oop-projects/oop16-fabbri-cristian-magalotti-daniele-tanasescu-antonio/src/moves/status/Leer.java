package moves.status;

import abilities.statisticsalterationondemand.BigPecks;
import abilities.statisticsalterationondemand.ClearBody;
import abilities.statisticsalterationondemand.WhiteSmoke;
import battle_arena.BattleArena;
import pokemon.Pokemon;
import types.Normal;

public class Leer extends StatusMove{

    public Leer() {
        super(  "Leer",                                                                                                          //name
                "The user gives opposing Pokemon an intimidating leer that lowers the Defense stat.",                            //description
                new Normal(),                                                                                                    //type
                1,                                                                                                               //accuracy
                30,                                                                                                              //PP                                                                                                                     
                0);                                                                                                              //priority  
    }

    @Override
    public void sideEffect(Pokemon user, Pokemon target, BattleArena battleArena) {
        target.setAlterationDef(-1, false);                        
    }
}
