package moves.status;

import battle_arena.BattleArena;
import pokemon.Pokemon;
import status_condition.Paralysis;
import types.Normal;

public class Glare extends StatusMove{

    public Glare() {
        super(  "Glare",                                                                                                         //name
                "The user intimidates the target with the pattern on its belly to cause paralysis.",                             //description
                new Normal(),                                                                                                    //type
                1,                                                                                                               //accuracy
                30,                                                                                                              //PP                                                                                                                     
                0);                                                                                                              //priority  
    }

    @Override
    public void sideEffect(Pokemon user, Pokemon target, BattleArena battleArena) {
        //immunities control
        if(target.statusCondition == null){
        	new Paralysis().setPokemonStatusCondition(target, battleArena);
        }
        
    }

}
