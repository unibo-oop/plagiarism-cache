package moves.damagingmove.special.hpdependent;

import battle_arena.BattleArena;
import pokemon.Pokemon;
import types.Water;

public class WaterSprout extends HPDependentSpecialDamagingMove{

    public WaterSprout() {
        super(  "Water Sprout",                                                                                               //name
                "The user spouts water to damage opposing Pokémon.\n" +                                                       //description
                "The lower the user's HP, the lower the move's power.",    
                150,                                                                                                          //base power
                new Water(),                                                                                                  //type
                1,                                                                                                            //accuracy
                critRange1,                                                                                                   //crit range 
                5,                                                                                                            //PP
                0);                                                                                                           //Priority
    }

    @Override
    public void sideEffect(Pokemon user, Pokemon target, BattleArena battleArena) {
        // TODO Auto-generated method stub
        
    }

}
