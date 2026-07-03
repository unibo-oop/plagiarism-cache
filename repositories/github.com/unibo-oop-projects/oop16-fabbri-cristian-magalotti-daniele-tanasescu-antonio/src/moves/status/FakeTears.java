package moves.status;

import battle_arena.BattleArena;
import pokemon.Pokemon;
import types.Dark;

public class FakeTears extends StatusMove{

    public FakeTears() {
        super(  "Fake Tears",                                                                                                    //name
                "The user feigns crying to fluster the target, harshly lowering its Sp. Def stat.",                              //description
                new Dark(),                                                                                                      //type
                1,                                                                                                               //accuracy
                20,                                                                                                              //PP                                                                                                                     
                0);                                                                                                              //priority  
    }

    @Override
    public void sideEffect(Pokemon user, Pokemon target, BattleArena battleArena) {
        target.setAlterationSpD(-2, false);
        
    }

}
