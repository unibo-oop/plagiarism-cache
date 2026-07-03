package moves.status;

import abilities.statisticsalterationondemand.*;
import battle_arena.BattleArena;
import pokemon.Pokemon;
import types.Normal;

public class Growl extends StatusMove{

    public Growl() {
        super(  "Growl",                                                                                                         //name
                "The user growls in an endearing way, making opposing Pokémon less wary. This lowers their Attack stat.",        //description
                new Normal(),                                                                                                    //type
                1,                                                                                                               //accuracy
                40,                                                                                                              //PP                                                                                                                     
                0);                                                                                                              //priority  
    }

    @Override
    public void sideEffect(Pokemon user, Pokemon target, BattleArena battleArena) {
            target.setAlterationAtk(-1, false);                        
    }
}
