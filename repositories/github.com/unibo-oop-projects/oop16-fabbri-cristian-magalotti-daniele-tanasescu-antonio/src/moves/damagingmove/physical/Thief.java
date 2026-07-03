package moves.damagingmove.physical;

import battle_arena.BattleArena;
import pokemon.Pokemon;
import types.Dark;

public class Thief extends PhysicalDamagingMove{

    public Thief() {
        super(  "Thief",                                                                                         //name
                "The user attacks and steals the target's held item simultaneously.\n"+                          //description
                "The user can't steal anything if it already holds an item.",        
                60,                                                                                              //base power
                new Dark(),                                                                                      //type
                1,                                                                                               //accuracy
                critRange1,                                                                                      //crit range 
                25,                                                                                              //PP
                0);                                                                                              //Priority
    }

    @Override
    public void sideEffect(Pokemon user, Pokemon target, BattleArena battleArena) {
        // TODO Auto-generated method stub
        
    }

}
