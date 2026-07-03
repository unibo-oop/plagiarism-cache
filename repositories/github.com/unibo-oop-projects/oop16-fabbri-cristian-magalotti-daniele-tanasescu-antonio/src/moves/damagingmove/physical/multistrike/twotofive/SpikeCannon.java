package moves.damagingmove.physical.multistrike.twotofive;

import battle_arena.BattleArena;
import pokemon.Pokemon;
import types.Normal;

public class SpikeCannon extends TwoToFiveMultiStrikePhysicalDamagingMove{

    public SpikeCannon() {
        super(  "Spike Cannon",                                                                                         //name
                "Sharp spikes are shot at the target in rapid succession. They hit two to five times in a row.",        //description
                20,                                                                                                     //base power
                new Normal(),                                                                                           //type
                1,                                                                                                      //accuracy
                critRange1,                                                                                             //crit range 
                15,                                                                                                     //PP
                0);                                                                                                     //Priority    
    }

    @Override
    public void sideEffect(Pokemon user, Pokemon target, BattleArena battleArena) {
        // TODO Auto-generated method stub
        
    }

}
