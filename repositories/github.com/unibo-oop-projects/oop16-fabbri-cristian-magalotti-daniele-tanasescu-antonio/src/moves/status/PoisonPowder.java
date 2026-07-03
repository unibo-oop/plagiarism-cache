package moves.status;

import battle_arena.BattleArena;
import pokemon.Pokemon;
import types.Poison;

public class PoisonPowder extends StatusMove{

    public PoisonPowder() {
        super(  "Poison Powder",                                                                                                 //name
                "The user scatters a cloud of poisonous dust that poisons the target.",                                          //description
                new Poison(),                                                                                                    //type
                0.75,                                                                                                            //accuracy
                35,                                                                                                              //PP                                                                                                                     
                0);                                                                                                              //priority  
    }

    @Override
    public void sideEffect(Pokemon user, Pokemon target, BattleArena battleArena) {
        if(target.statusCondition == null){
        	new status_condition.Poison().setPokemonStatusCondition(target, battleArena);
        }
        
    }

}
