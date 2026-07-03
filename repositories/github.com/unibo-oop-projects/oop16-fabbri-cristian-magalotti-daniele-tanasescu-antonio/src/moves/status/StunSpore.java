package moves.status;

import battle_arena.BattleArena;
import pokemon.Pokemon;
import status_condition.Paralysis;
import types.Grass;

public class StunSpore extends StatusMove{

    public StunSpore() {
        super(  "Stun Spore",                                                                                                 //name
                "The user scatters a cloud of numbing powder that paralyzes the target.",                                      //description
                new Grass(),                                                                                                    //type
                0.75,                                                                                                           //accuracy
                30,                                                                                                             //PP                                                                                                                     
                0);                                                                                                             //priority  
    }

    @Override
    public void sideEffect(Pokemon user, Pokemon target, BattleArena battleArena) {
    	if(target.statusCondition == null){
        	new Paralysis().setPokemonStatusCondition(target, battleArena);
        }
        
    }

}
