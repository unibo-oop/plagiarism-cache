package moves.status;

import battle_arena.BattleArena;
import pokemon.Pokemon;
import types.Psychic;

public class CosmicPower extends StatusMove{

    public CosmicPower() {
        super(  "Cosmic Power",                                                                                                  //name
                "The user absorbs a mystical power from space to raise its Defense and Sp. Def stats.",                          //description
                new Psychic(),                                                                                                   //type
                999,                                                                                                             //accuracy
                20,                                                                                                              //PP                                                                                                                     
                0);                                                                                                              //priority  
        this.setSelfEffect(true);
    }

    @Override
    public void sideEffect(Pokemon user, Pokemon target, BattleArena battleArena) {
        user.setAlterationDef(+1, true);
        user.setAlterationSpD(+1, true);
    }
}
