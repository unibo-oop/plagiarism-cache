package moves.damagingmove.special;

import battle_arena.BattleArena;
import pokemon.Pokemon;
import types.Flying;

public class AirCutter extends SpecialDamagingMove{

    public AirCutter() {
        super(  "Air Cutter",                                                                                         //name
                "The user launches razor-like wind to slash the opposing Pokémon.\n"+                                 //description
                "Critical hits land more easily.",                
                60,                                                                                                   //base power
                new Flying(),                                                                                         //type
                0.95,                                                                                                 //accuracy
                critRange2,                                                                                           //crit range 
                25,                                                                                                   //PP
                0);                                                                                                   //Priority
    }

    @Override
    public void sideEffect(Pokemon user, Pokemon target, BattleArena battleArena) {
        // TODO Auto-generated method stub
        
    }

}
