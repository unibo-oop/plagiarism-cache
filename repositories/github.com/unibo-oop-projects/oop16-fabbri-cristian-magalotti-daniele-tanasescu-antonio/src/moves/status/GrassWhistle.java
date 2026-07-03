package moves.status;

import battle_arena.BattleArena;
import pokemon.Pokemon;
import status_condition.Sleep;
import types.Grass;

public class GrassWhistle extends StatusMove{

    public GrassWhistle() {
        super(  "Grass Whistle",                                                                                                 //name
                "The user plays a pleasant melody that lulls the target into a deep sleep.",                                     //description
                new Grass(),                                                                                                     //type
                0.55,                                                                                                            //accuracy
                15,                                                                                                              //PP                                                                                                                     
                0);                                                                                                              //priority  
    }

    @Override
    public void sideEffect(Pokemon user, Pokemon target, BattleArena battleArena) {
        new Sleep().setPokemonStatusCondition(target, battleArena);
        
    }

}
