package moves.damagingmove.physical.multistrike.twotofive;

import battle_arena.BattleArena;
import pokemon.Pokemon;
import types.Bug;

public class PinMissile extends TwoToFiveMultiStrikePhysicalDamagingMove{

    public PinMissile() {
        super(  "Pin Missile",                                                                                          //name
                "Sharp spikes are shot at the target in rapid succession. They hit two to five times in a row.",        //description
                25,                                                                                                     //base power
                new Bug(),                                                                                              //type
                0.95,                                                                                                   //accuracy
                critRange1,                                                                                             //crit range 
                20,                                                                                                     //PP
                0);                                                                                                     //Priority          
    }

    @Override
    public void sideEffect(Pokemon user, Pokemon target, BattleArena battleArena) {
        // TODO Auto-generated method stub
        
    }

}
