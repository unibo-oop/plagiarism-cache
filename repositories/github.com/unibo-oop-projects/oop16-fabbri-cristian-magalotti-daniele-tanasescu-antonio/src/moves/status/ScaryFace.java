package moves.status;

import battle_arena.BattleArena;
import pokemon.Pokemon;
import types.Normal;

public class ScaryFace extends StatusMove{

    public ScaryFace() {
        super(  "Scary Face",                                                                                                    //name
                "The user frightens the target with a scary face to harshly lower its Speed stat.",                              //description
                new Normal(),                                                                                                    //type
                1,                                                                                                               //accuracy
                10,                                                                                                              //PP                                                                                                                     
                0);                                                                                                              //priority  
    }

    @Override
    public void sideEffect(Pokemon user, Pokemon target, BattleArena battleArena) {
        target.setAlterationSpe(-2, false);
        
    }

}
