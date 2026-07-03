package moves.status;

import battle_arena.BattleArena;
import pokemon.Pokemon;
import status_condition.Sleep;
import types.Grass;

public class SleepPowder extends StatusMove{

    public SleepPowder() {
        super(  "Sleep Powder",                                                                                                 //name
                "The user scatters a big cloud of sleep-inducing dust around the target.",                                      //description
                new Grass(),                                                                                                    //type
                0.75,                                                                                                           //accuracy
                15,                                                                                                             //PP                                                                                                                     
                0);                                                                                                             //priority  
    }

    @Override
    public void sideEffect(Pokemon user, Pokemon target, BattleArena battleArena) {
        if(target.statusCondition == null){
        	new Sleep().setPokemonStatusCondition(target, battleArena);
        }
        
    }

}
