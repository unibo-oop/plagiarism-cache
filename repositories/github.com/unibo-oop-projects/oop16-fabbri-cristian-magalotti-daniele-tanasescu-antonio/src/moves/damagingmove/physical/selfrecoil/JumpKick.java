package moves.damagingmove.physical.selfrecoil;

import battle_arena.BattleArena;
import pokemon.Pokemon;
import types.Fight;

public class JumpKick extends SelfRecoilPhysicalDamagingMove{

    public JumpKick() {
        super(  "Jump Kick",                                                                                            //name
                "The user jumps up high, then strikes with a kick. If the kick misses, the user hurts itself.",         //description
                100,                                                                                                    //base power
                new Fight(),                                                                                            //type
                0.95,                                                                                                   //accuracy
                critRange1,                                                                                             //crit range                                                                                                      
                10,                                                                                                     //PP
                0,                                                                                                      //priority
                0.5,                                                                                                    //recoil percentage
                true);                                                                                                  //recoil after fail?
    }

    @Override
    public void sideEffect(Pokemon user, Pokemon target, BattleArena battleArena) {
        // TODO Auto-generated method stub
        
    }

}
