package moves.damagingmove.physical.multistrike.two;

import battle_arena.BattleArena;
import pokemon.Pokemon;
import types.Fight;

public class DoubleKick extends TwoMultiStrikePhysicalDamagingMove{

    public DoubleKick() {
       super(   "Double Kick",                                                                                  //name
                "The target is quickly kicked twice in succession using both feet. ",                           //description
                30,                                                                                             //base power
                new Fight(),                                                                                    //type
                1,                                                                                              //accuracy
                critRange1,                                                                                     //crit range 
                30,                                                                                             //PP
                0);                                                                                             //Priority
    }

    @Override
    public void sideEffect(Pokemon user, Pokemon target, BattleArena battleArena) {
        // TODO Auto-generated method stub
        
    }

}
