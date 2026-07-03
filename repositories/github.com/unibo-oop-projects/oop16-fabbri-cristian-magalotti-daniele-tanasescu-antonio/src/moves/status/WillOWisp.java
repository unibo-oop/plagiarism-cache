package moves.status;

import battle_arena.BattleArena;
import pokemon.Pokemon;
import status_condition.Burn;
import types.Fire;

public class WillOWisp extends StatusMove{

    public WillOWisp() {
        super(  "Will-O-Wisp",                                                                                                  //name
                "The user shoots a sinister, bluish-white flame at the target to inflict a burn.",                              //description                                       
                new Fire(),                                                                                                     //type
                0.85,                                                                                                           //accuracy
                15,                                                                                                             //PP                                                                                                                     
                0);                                                                                                             //priority  
    }

    @Override
    public void sideEffect(Pokemon user, Pokemon target, BattleArena battleArena) {
        new Burn().setPokemonStatusCondition(target, battleArena);      
    }

}
