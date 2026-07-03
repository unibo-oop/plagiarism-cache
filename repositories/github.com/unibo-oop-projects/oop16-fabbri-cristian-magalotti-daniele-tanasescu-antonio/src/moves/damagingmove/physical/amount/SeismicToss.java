package moves.damagingmove.physical.amount;

import battle_arena.BattleArena;
import pokemon.Pokemon;
import types.Fight;

public class SeismicToss extends AmountPhysicalDamagingMove{

    public SeismicToss(Pokemon user) {          //it needs to know user's level, which will never change in this game
        super(  "Seismic Toss",                                                                                              //name                                                                                                                                                                                                                    
                "The target is thrown using the power of gravity. It inflicts damage equal to the user's level.",            //description
                new Fight(),                                                                                                 //type
                1,                                                                                                           //accuracy
                20,                                                                                                          //PP
                0,                                                                                                           //priority 
                user.getLevel());                                                                                            //amount damage
    }

    @Override
    public void sideEffect(Pokemon user, Pokemon target, BattleArena battleArena) {
        // TODO Auto-generated method stub
        
    }

}
