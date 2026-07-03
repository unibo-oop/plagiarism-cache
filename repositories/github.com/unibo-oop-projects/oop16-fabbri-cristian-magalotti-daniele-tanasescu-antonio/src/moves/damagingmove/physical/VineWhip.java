package moves.damagingmove.physical;

import battle_arena.BattleArena;
import pokemon.Pokemon;
import types.Grass;

public class VineWhip extends PhysicalDamagingMove{

    public VineWhip() {
        super(  "Vine Whip",                                                                                            //name
                "The target is struck with slender, whiplike vines to inflict damage.",                                 //description
                45,                                                                                                     //base power
                new Grass(),                                                                                            //type
                1,                                                                                                      //accuracy
                critRange1,                                                                                             //crit range 
                25,                                                                                                     //PP
                0);                                                                                                     //Priority
    }

    @Override
    public void sideEffect(Pokemon user, Pokemon target, BattleArena battleArena) {
        // TODO Auto-generated method stub
        
    }

}
