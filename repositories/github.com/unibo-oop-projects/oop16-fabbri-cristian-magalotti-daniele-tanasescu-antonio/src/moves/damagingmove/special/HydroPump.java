package moves.damagingmove.special;

import battle_arena.BattleArena;
import pokemon.Pokemon;
import types.Water;

public class HydroPump extends SpecialDamagingMove{

    public HydroPump() {
        super(  "Hydro Pump",                                                                                  //name
                "The target is blasted by a huge volume of water launched under great pressure",               //description
                100,                                                                                           //base power
                new Water(),                                                                                   //type
                0.8,                                                                                           //accuracy
                critRange1,                                                                                    //crit range 
                5,                                                                                             //PP
                0);                                                                                            //Priority
    }

    @Override
    public void sideEffect(Pokemon user, Pokemon target, BattleArena battleArena) {
        // TODO Auto-generated method stub
        
    }

}
