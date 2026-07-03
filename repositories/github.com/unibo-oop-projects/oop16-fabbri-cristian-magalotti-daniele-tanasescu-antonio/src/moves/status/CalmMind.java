package moves.status;

import battle_arena.BattleArena;
import pokemon.Pokemon;
import types.Psychic;

public class CalmMind extends StatusMove{

    public CalmMind() {
        super(  "Calm Mind",                                                                                                     //name
                "The user quietly focuses its mind and calms its spirit to raise its Sp. Atk and Sp. Def stats.",                //description
                new Psychic(),                                                                                                   //type
                999,                                                                                                             //accuracy
                20,                                                                                                              //PP                                                                                                                     
                0);                                                                                                              //priority  
        this.setSelfEffect(true);
    }

    @Override
    public void sideEffect(Pokemon user, Pokemon target, BattleArena battleArena) {
        user.setAlterationSpA(+1, true);
        user.setAlterationSpD(+1, true);
        
    }

}
