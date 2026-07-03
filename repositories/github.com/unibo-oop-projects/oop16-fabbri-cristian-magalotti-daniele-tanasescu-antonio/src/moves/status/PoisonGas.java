package moves.status;

import battle_arena.BattleArena;
import pokemon.Pokemon;
import types.Poison;

public class PoisonGas extends StatusMove{

    public PoisonGas() {
        super(  "Poison Gas",                                                                                                    //name
                "A cloud of poison gas is sprayed in the face of opposing Pokemon, poisoning those hit.",                        //description
                new Poison(),                                                                                                    //type
                0.90,                                                                                                            //accuracy
                40,                                                                                                              //PP                                                                                                                     
                0);                                                                                                              //priority  
    }

    @Override
    public void sideEffect(Pokemon user, Pokemon target, BattleArena battleArena) {
        if(target.statusCondition == null){
        	new status_condition.Poison().setPokemonStatusCondition(target, battleArena);
        }
    }
}
