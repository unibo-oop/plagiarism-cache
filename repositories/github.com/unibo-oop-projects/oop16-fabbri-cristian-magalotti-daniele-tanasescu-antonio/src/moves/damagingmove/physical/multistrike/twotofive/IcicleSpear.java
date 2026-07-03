package moves.damagingmove.physical.multistrike.twotofive;

import battle_arena.BattleArena;
import pokemon.Pokemon;
import types.Ice;

public class IcicleSpear extends TwoToFiveMultiStrikePhysicalDamagingMove{

    public IcicleSpear() {
        super(  "Icicle Spear",                                                                           //name
                "The user launches sharp icicles at the target two to five times in a row.",              //description
                25,                                                                                       //base power
                new Ice(),                                                                                //type
                1,                                                                                        //accuracy
                critRange1,                                                                               //crit range 
                30,                                                                                       //PP
                0);                                                                                       //Priority                          
    }

    @Override
    public void sideEffect(Pokemon user, Pokemon target, BattleArena battleArena) {
        // TODO Auto-generated method stub
        
    }

}
