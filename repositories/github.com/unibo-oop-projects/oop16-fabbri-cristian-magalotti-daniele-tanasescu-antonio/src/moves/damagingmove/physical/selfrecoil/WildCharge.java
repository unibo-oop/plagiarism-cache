package moves.damagingmove.physical.selfrecoil;

import battle_arena.BattleArena;
import pokemon.Pokemon;
import types.Electric;

public class WildCharge extends SelfRecoilPhysicalDamagingMove{

    public WildCharge() {
        super(  "Wild Charge",                                                                                                    //name
                "The user shrouds itself in electricity and smashes into its target.\n"+                                          //description  
                "This also damages the user a little.",
                90,                                                                                                               //base power
                new Electric(),                                                                                                     //type
                1,                                                                                                                //accuracy
                critRange1,                                                                                                       //crit range                                                                                                      
                15,                                                                                                               //PP
                0,                                                                                                                //priority
                0.25,                                                                                                             //recoil percentage
                false);                                                                                                           //recoil after fail?
    }

    @Override
    public void sideEffect(Pokemon user, Pokemon target, BattleArena battleArena) {
        // TODO Auto-generated method stub
        
    }

}
