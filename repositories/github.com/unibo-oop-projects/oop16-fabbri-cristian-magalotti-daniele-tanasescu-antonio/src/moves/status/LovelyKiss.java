package moves.status;

import battle_arena.BattleArena;
import pokemon.Pokemon;
import status_condition.Sleep;
import types.Normal;

public class LovelyKiss extends StatusMove{

    public LovelyKiss(){
        super(  "Lovely Kiss",                                                                                                   //name
                "With a scary face, the user tries to force a kiss on the target. If it succeeds, the target falls asleep.",     //description
                new Normal(),                                                                                                   //type
                0.75,                                                                                                            //accuracy
                10,                                                                                                              //PP                                                                                                                     
                0);                                                                                                              //priority  
    }

    @Override
    public void sideEffect(Pokemon user, Pokemon target, BattleArena battleArena) {
        if(target.statusCondition == null){
        	 new Sleep().setPokemonStatusCondition(target, battleArena);
        }
        
    }

}
