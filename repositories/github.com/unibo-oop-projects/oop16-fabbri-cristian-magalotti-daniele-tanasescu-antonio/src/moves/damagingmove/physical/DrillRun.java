package moves.damagingmove.physical;

import battle_arena.BattleArena;
import pokemon.Pokemon;
import types.Ground;

public class DrillRun extends PhysicalDamagingMove{

    public DrillRun() {
        super(  "Drill Run",                                                                                             //name
                "The user crashes into its target while rotating its body like a drill."
                + "Critical hits land more easily.",                              									    //description
                80,                                                                                                     //base power
                new Ground(),                                                                                           //type
                0.95,                                                                                                   //accuracy
                critRange2,                                                                                             //crit range 
                10,                                                                                                     //PP
                0);                                                                                                     //Priority
    }

    @Override
    public void sideEffect(Pokemon user, Pokemon target, BattleArena battleArena) {
        // TODO Auto-generated method stub
        
    }

}
