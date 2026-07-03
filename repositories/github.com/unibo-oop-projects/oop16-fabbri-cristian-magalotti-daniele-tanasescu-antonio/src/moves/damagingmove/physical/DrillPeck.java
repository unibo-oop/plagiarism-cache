package moves.damagingmove.physical;

import battle_arena.BattleArena;
import pokemon.Pokemon;
import types.Flying;

public class DrillPeck extends PhysicalDamagingMove{

    public DrillPeck() {
        super(  "Drill Peck",                                                                                           //name
                "A corkscrewing attack with a sharp beak acting as a drill.",                                           //description
                80,                                                                                                     //base power
                new Flying(),                                                                                           //type
                1,                                                                                                      //accuracy
                critRange1,                                                                                             //crit range 
                20,                                                                                                     //PP
                0);                                                                                                     //Priority
    }

    @Override
    public void sideEffect(Pokemon user, Pokemon target, BattleArena battleArena) {
        // TODO Auto-generated method stub
        
    }

}
