package moves.status;

import battle_arena.BattleArena;
import pokemon.Pokemon;
import status_condition.Paralysis;
import types.Electric;;

public class ThunderWave extends StatusMove{

    public ThunderWave() {
        super(  "Thunder Wave",                                                                                                    //name
                "The user launches a weak jolt of electricity that paralyzes the target.",          				               //description
                new Electric(),                                                                                                    //type
                0.90,                                                                                                              //accuracy
                20,                                                                                                                //PP                                                                                                                     
                0);                                                                                                                //priority  
    }

    @Override
    public void sideEffect(Pokemon user, Pokemon target, BattleArena battleArena) {
            new Paralysis().setPokemonStatusCondition(target, battleArena);
        }  
}
