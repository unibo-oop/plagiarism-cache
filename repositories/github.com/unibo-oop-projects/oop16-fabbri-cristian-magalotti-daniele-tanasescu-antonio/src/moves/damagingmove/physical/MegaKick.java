package moves.damagingmove.physical;

import battle_arena.BattleArena;
import pokemon.Pokemon;
import types.Normal;

public class MegaKick extends PhysicalDamagingMove{

    public MegaKick() {
        super(  "Mega Kick",                                                                                              //name
                "The target is attacked by a kick launched with muscle-packed power.",                                    //description
                120,                                                                                                      //base power
                new Normal(),                                                                                             //type
                0.75,                                                                                                     //accuracy
                critRange1,                                                                                               //crit range 
                5,                                                                                                        //PP
                0);                                                                                                       //Priority
    }

    @Override
    public void sideEffect(Pokemon user, Pokemon target, BattleArena battleArena) {
        // TODO Auto-generated method stub
        
    }

}
