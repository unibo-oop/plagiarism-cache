package moves.status;

import battle_arena.BattleArena;
import pokemon.Pokemon;
import types.Psychic;

public class Agility extends StatusMove{

    public Agility() {
        super(  "Agility",                                                                                                       //name
                "The user relaxes and lightens its body to move faster.\n"
                + "This sharply raises the Speed stat.",                   														 //description
                new Psychic(),                                                                                                   //type
                999,                                                                                                             //accuracy
                30,                                                                                                              //PP                                                                                                                     
                0);                                                                                                              //priority  
        this.setSelfEffect(true);
    }

    @Override
    public void sideEffect(Pokemon user, Pokemon target, BattleArena battleArena) {
    	user.setAlterationSpe(+2, true);
        
    }

}
