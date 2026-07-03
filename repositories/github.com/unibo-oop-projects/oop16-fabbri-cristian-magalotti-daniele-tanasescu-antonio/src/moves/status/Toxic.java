package moves.status;

import battle_arena.BattleArena;
import pokemon.Pokemon;
import status_condition.BadPoison;
import types.Poison;

public class Toxic extends StatusMove{

    public Toxic() {
        super(  "Toxic",                                                                                             	         //name
                "A move that leaves the target badly poisoned.\n"
                + "Its poison damage worsens every turn.",                	        											 //description
                new Poison(),                                                                                                    //type
                0.90,                                                                                                            //accuracy
                10,                                                                                                              //PP                                                                                                                     
                0);                                                                                                              //priority  
    }

    @Override
    public void sideEffect(Pokemon user, Pokemon target, BattleArena battleArena) {
        new BadPoison().setPokemonStatusCondition(target, battleArena);
    }
}
