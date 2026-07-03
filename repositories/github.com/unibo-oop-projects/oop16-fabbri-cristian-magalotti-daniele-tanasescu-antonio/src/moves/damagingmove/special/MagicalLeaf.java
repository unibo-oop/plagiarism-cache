package moves.damagingmove.special;

import battle_arena.BattleArena;
import pokemon.Pokemon;
import types.Grass;

public class MagicalLeaf extends SpecialDamagingMove{

    public MagicalLeaf() {
        super(  "Magical Leaf",                                                                                                                 //name
                "The user scatters curious leaves that chase the target. This attack never misses.",                                            //description
                60,                                                                                                                             //base power
                new Grass(),                                                                                                                    //type
                999,                                                                                                                            //accuracy
                critRange1,                                                                                                                     //crit range 
                20,                                                                                                                             //PP
                0);                                                                                                                             //Priority
    }

    @Override
    public void sideEffect(Pokemon user, Pokemon target, BattleArena battleArena) {
        // TODO Auto-generated method stub
        
    }

}
