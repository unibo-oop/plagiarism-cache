package moves.damagingmove.physical;

import battle_arena.BattleArena;
import pokemon.Pokemon;
import types.Flying;

public class WingAttack extends PhysicalDamagingMove{

    public WingAttack() {
        super(  "Wing Attack",                                                                                          //name
                "The target is struck with large, imposing wings spread wide to inflict damage.",                       //description
                60,                                                                                                     //base power
                new Flying(),                                                                                           //type
                1,                                                                                                      //accuracy
                critRange1,                                                                                             //crit range 
                35,                                                                                                     //PP
                0);                                                                                                     //Priority
    }

    @Override
    public void sideEffect(Pokemon user, Pokemon target, BattleArena battleArena) {
        // TODO Auto-generated method stub
        
    }

}
