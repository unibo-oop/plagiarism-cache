package moves.damagingmove.physical.selfrecoil;

import battle_arena.BattleArena;
import pokemon.Pokemon;
import types.Normal;

public class TakeDown extends SelfRecoilPhysicalDamagingMove{

    public TakeDown() {
        super(  "Take Down",                                                                                                      //name
                "A reckless, full-body charge attack for slamming into the target. This also damages the user a little.",         //description
                90,                                                                                                               //base power
                new Normal(),                                                                                                     //type
                0.85,                                                                                                             //accuracy
                critRange1,                                                                                                       //crit range                                                                                                      
                20,                                                                                                               //PP
                0,                                                                                                                //priority
                0.25,                                                                                                             //recoil percentage
                false);                                                                                                           //recoil after fail?
    }

    @Override
    public void sideEffect(Pokemon user, Pokemon target, BattleArena battleArena) {
        // TODO Auto-generated method stub
        
    }

}
