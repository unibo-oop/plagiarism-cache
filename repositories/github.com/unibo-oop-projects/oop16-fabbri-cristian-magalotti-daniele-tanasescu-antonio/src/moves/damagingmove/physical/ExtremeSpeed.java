package moves.damagingmove.physical;

import battle_arena.BattleArena;
import pokemon.Pokemon;
import types.Normal;

public class ExtremeSpeed extends PhysicalDamagingMove{

    public ExtremeSpeed() {
        super(  "Extreme Speed",                                                                                        //name
                "The user charges the target at blinding speed. This move always goes first.",                          //description                      
                80,                                                                                                     //base power
                new Normal(),                                                                                           //type
                1,                                                                                                      //accuracy
                critRange1,                                                                                             //crit range 
                5,                                                                                                      //PP
                +2);                                                                                                    //Priority
    }

    @Override
    public void sideEffect(Pokemon user, Pokemon target, BattleArena battleArena) {
        // TODO Auto-generated method stub
        
    }

}
