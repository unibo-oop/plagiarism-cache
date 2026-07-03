package moves.status;

import battle_arena.BattleArena;
import pokemon.Pokemon;
import status_condition.Sleep;
import types.Normal;

public class Sing extends StatusMove{

    public Sing() {
        super(  "Sing",                                                                                                          //name
                "A soothing lullaby is sung in a calming voice that puts the target into a deep slumber.",                       //description
                new Normal(),                                                                                                    //type
                0.55,                                                                                                            //accuracy
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
