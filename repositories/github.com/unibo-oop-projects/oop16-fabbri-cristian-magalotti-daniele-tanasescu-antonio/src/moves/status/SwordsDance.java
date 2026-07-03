package moves.status;

import battle_arena.BattleArena;
import pokemon.Pokemon;
import types.Normal;

public class SwordsDance extends StatusMove{

    public SwordsDance() {
        super(  "Swords Dance",                                                                                                 //name
                "A frenetic dance to uplift the fighting spirit.\n"
                + "This sharply raises the user's Attack stat.",           				   								        //description
                new Normal(),                                                                                                   //type
                1,                                                                                                              //accuracy
                20,                                                                                                             //PP                                                                                                                     
                0);                                                                                                             //priority 
        this.setSelfEffect(true);
    }

    @Override
    public void sideEffect(Pokemon user, Pokemon target, BattleArena battleArena) {
        user.setAlterationAtk(+2, true);
    }

}
