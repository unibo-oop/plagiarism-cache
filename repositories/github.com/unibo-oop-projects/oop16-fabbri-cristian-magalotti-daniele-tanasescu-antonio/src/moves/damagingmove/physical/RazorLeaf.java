package moves.damagingmove.physical;

import battle_arena.BattleArena;
import pokemon.Pokemon;
import types.Grass;

public class RazorLeaf extends PhysicalDamagingMove{

    public RazorLeaf() {
        super(  "Razor Leaf",                                                                                           //name
                "Sharp-edged leaves are launched to slash at the opposing Pokémon. Critical hits land more easily.",    //description
                55,                                                                                                     //base power
                new Grass(),                                                                                            //type
                0.95,                                                                                                   //accuracy
                critRange2,                                                                                             //crit range 
                25,                                                                                                     //PP
                0);                                                                                                     //Priority
    }

    @Override
    public void sideEffect(Pokemon user, Pokemon target, BattleArena battleArena) {
        // TODO Auto-generated method stub
        
    }

}
