package moves.damagingmove.special;

import battle_arena.BattleArena;
import pokemon.Pokemon;
import types.Normal;

public class Swift extends SpecialDamagingMove{

    public Swift() {
        super(  "Swift",                                                                                                      //name
                "Star-shaped rays are shot at the opposing Pokémon. This attack never misses.",                               //description
                60,                                                                                                           //base power
                new Normal(),                                                                                                 //type
                999,                                                                                                          //accuracy
                critRange1,                                                                                                   //crit range 
                20,                                                                                                           //PP
                0);                                                                                                           //Priority
    }

    @Override
    public void sideEffect(Pokemon user, Pokemon target, BattleArena battleArena) {
        // TODO Auto-generated method stub
        
    }

}
