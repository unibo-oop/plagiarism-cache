package moves.damagingmove.physical;

import battle_arena.BattleArena;
import pokemon.Pokemon;
import types.Normal;

public class Slam extends PhysicalDamagingMove{

    public Slam() {
        super(  "Slam",                                                                                                 //name
                "The target is slammed with a long tail, vines, or the like to inflict damage.",                        //description
                80,                                                                                                     //base power
                new Normal(),                                                                                           //type
                0.75,                                                                                                   //accuracy
                critRange1,                                                                                             //crit range 
                20,                                                                                                     //PP
                0);                                                                                                     //Priority)
    }

    @Override
    public void sideEffect(Pokemon user, Pokemon target, BattleArena battleArena) {
        // TODO Auto-generated method stub
        
    }

}
