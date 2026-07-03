package moves.status;

import battle_arena.BattleArena;
import pokemon.Pokemon;
import types.Steel;

public class MetalSound extends StatusMove{

    public MetalSound() {
        super(  "Metal Sound",                                                                                                   //name
                "A horrible sound like scraping metal harshly lowers the target's Sp. Def stat.",                                //description
                new Steel(),                                                                                                     //type
                0.85,                                                                                                            //accuracy
                40,                                                                                                              //PP                                                                                                                     
                0);                                                                                                              //priority  
    }

    @Override
    public void sideEffect(Pokemon user, Pokemon target, BattleArena battleArena) {
        target.setAlterationSpD(-2, true);
        
    }

}
