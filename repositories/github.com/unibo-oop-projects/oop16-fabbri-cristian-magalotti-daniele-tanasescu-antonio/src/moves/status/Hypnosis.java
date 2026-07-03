package moves.status;

import battle_arena.BattleArena;
import pokemon.Pokemon;
import status_condition.Sleep;
import types.Psychic;

public class Hypnosis extends StatusMove{

    public Hypnosis() {
        super(  "Hypnosis",                                                                                                      //name
                "The user employs hypnotic suggestion to make the target fall into a deep sleep",                                //description
                new Psychic(),                                                                                                   //type
                0.60,                                                                                                            //accuracy
                20,                                                                                                              //PP                                                                                                                     
                0);                                                                                                              //priority  
    }

    @Override
    public void sideEffect(Pokemon user, Pokemon target, BattleArena battleArena) {
        if(target.statusCondition == null){
        	 new Sleep().setPokemonStatusCondition(target, battleArena);
        }
        
    }

}
