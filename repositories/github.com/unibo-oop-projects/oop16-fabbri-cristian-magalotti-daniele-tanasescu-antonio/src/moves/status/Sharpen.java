package moves.status;

import battle_arena.BattleArena;
import pokemon.Pokemon;
import types.Normal;

public class Sharpen extends StatusMove{

    public Sharpen() {
        super(  "Sharpen",                                                                                                       //name
                "The user makes its edges more jagged, which raises its Attack stat.",                                           //description
                new Normal(),                                                                                                    //type
                999,                                                                                                             //accuracy
                30,                                                                                                              //PP                                                                                                                     
                0);                                                                                                              //priority  
        this.setSelfEffect(true);
    }

    @Override
    public void sideEffect(Pokemon user, Pokemon target, BattleArena battleArena) {
        user.setAlterationAtk(+1, true);
    }

}
