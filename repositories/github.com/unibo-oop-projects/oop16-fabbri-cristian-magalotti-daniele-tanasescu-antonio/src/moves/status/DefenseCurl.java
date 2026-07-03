package moves.status;

import battle_arena.BattleArena;
import pokemon.Pokemon;
import types.Normal;

public class DefenseCurl extends StatusMove{

    public DefenseCurl() {
        super(  "Defense Curl",                                                                                                  //name
                "The user curls up to conceal weak spots and raise its Defense stat.",                                           //description
                new Normal(),                                                                                                    //type
                999,                                                                                                             //accuracy
                40,                                                                                                              //PP                                                                                                                     
                0);                                                                                                              //priority  
        this.setSelfEffect(true);
    }

    @Override
    public void sideEffect(Pokemon user, Pokemon target, BattleArena battleArena) {
    	user.setAlterationDef(+1, true);
    }
}
