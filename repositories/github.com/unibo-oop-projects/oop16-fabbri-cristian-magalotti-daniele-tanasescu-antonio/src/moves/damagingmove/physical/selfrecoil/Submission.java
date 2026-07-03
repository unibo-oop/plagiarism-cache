package moves.damagingmove.physical.selfrecoil;

import battle_arena.BattleArena;
import pokemon.Pokemon;
import types.Fight;

public class Submission extends SelfRecoilPhysicalDamagingMove{

    public Submission() {
        super(  "Submission",                                                                                                     //name
                "The user grabs the target and recklessly dives for the ground. This also damages the user a little.",            //description
                80,                                                                                                               //base power
                new Fight (),                                                                                                     //type
                0.80,                                                                                                             //accuracy
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
