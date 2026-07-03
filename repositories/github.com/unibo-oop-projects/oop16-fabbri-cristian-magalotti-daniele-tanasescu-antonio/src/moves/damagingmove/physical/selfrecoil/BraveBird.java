package moves.damagingmove.physical.selfrecoil;

import battle_arena.BattleArena;
import pokemon.Pokemon;
import types.Flying;

public class BraveBird extends SelfRecoilPhysicalDamagingMove{

    public BraveBird() {
        super(  "Brave Bird",                                                                                                     //name
                "The user tucks in its wings and charges from a low altitude. This also damages the user quite a lot",             //description
                120,                                                                                                              //base power
                new Flying(),                                                                                                     //type
                1,                                                                                                                //accuracy
                critRange1,                                                                                                       //crit range                                                                                                      
                15,                                                                                                               //PP
                0,                                                                                                                //priority
                0.33,                                                                                                             //recoil percentage
                false);                                                                                                           //recoil after fail?
    }

    @Override
    public void sideEffect(Pokemon user, Pokemon target, BattleArena battleArena) {
        // TODO Auto-generated method stub
        
    }

}
