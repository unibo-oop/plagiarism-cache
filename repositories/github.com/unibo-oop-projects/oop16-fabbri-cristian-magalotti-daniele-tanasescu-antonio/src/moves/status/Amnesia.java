package moves.status;

import battle_arena.BattleArena;
import pokemon.Pokemon;
import types.Psychic;

public class Amnesia extends StatusMove{

    public Amnesia() {
        super(  "Amnesia",                                                                                                       //name
                "The user temporarily empties its mind to forget its concerns.\n"+                                               //description       
                "This sharply raises the user's Sp. Def stat.",    
                new Psychic(),                                                                                                   //type
                999,                                                                                                             //accuracy
                20,                                                                                                              //PP                                                                                                                     
                0);                                                                                                              //priority  
        this.setSelfEffect(true);
    }

    @Override
    public void sideEffect(Pokemon user, Pokemon target, BattleArena battleArena) {
    	user.setAlterationSpD(+2, true);
    	}
}
