package moves.status;

import abilities.statisticsalterationondemand.BigPecks;
import abilities.statisticsalterationondemand.ClearBody;
import abilities.statisticsalterationondemand.WhiteSmoke;
import battle_arena.BattleArena;
import pokemon.Pokemon;
import types.Normal;

public class Screech extends StatusMove{

    public Screech() {
        super(  "Screech",                                                                                                       //name
                "An earsplitting screech harshly lowers the target's Defense stat.",                                             //description
                new Normal(),                                                                                                    //type
                0.85,                                                                                                            //accuracy
                40,                                                                                                              //PP                                                                                                                     
                0);                                                                                                              //priority  
    }

    @Override
    public void sideEffect(Pokemon user, Pokemon target, BattleArena battleArena) {
        target.setAlterationDef(-2, false);                        

    }
}
