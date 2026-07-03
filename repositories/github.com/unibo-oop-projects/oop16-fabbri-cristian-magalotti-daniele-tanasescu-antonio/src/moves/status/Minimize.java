package moves.status;

import battle_arena.BattleArena;
import pokemon.Pokemon;
import types.Normal;

public class Minimize extends StatusMove{

    public Minimize() {
        super(  "Minimize",                                                                                                      //name
                "The user compresses its body to make itself look smaller, which sharply raises its evasiveness.",               //description
                new Normal(),                                                                                                    //type
                999,                                                                                                             //accuracy
                10,                                                                                                              //PP                                                                                                                     
                0);                                                                                                              //priority  
        this.setSelfEffect(true);
    }

    @Override
    public void sideEffect(Pokemon user, Pokemon target, BattleArena battleArena) {
    	user.setAlterationElusion(+2, true);
        
    }

}
