package moves.damagingmove.physical.selfrecoil;

import battle_arena.BattleArena;
import pokemon.Pokemon;
import types.Electric;
import types.Grass;

public class WoodHammer extends SelfRecoilPhysicalDamagingMove{

    public WoodHammer() {
        super(  "Wood Hammer",                                                                                                    //name
                "The user slams its rugged body into the target to attack.\n"+                                                    //description  
                "This also damages the user quite a lot.",
                120,                                                                                                              //base power
                new Grass(),                                                                                                      //type
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
