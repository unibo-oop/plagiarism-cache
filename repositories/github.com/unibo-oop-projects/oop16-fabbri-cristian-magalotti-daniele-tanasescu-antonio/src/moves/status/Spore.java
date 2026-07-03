package moves.status;

import battle_arena.BattleArena;
import pokemon.Pokemon;
import status_condition.Sleep;
import types.Grass;

public class Spore extends StatusMove{

    public Spore() {
        super(  "Spore",                                                                                                         //name
                "The user scatters bursts of spores that induce sleep.",                                                         //description
                new Grass(),                                                                                                    //type
                1,                                                                                                               //accuracy
                15,                                                                                                              //PP                                                                                                                     
                0);                                                                                                              //priority  
    }

    @Override
    public void sideEffect(Pokemon user, Pokemon target, BattleArena battleArena) {
        if(target.statusCondition == null){
        	new Sleep().setPokemonStatusCondition(target, battleArena);
        }
        
    }

}
