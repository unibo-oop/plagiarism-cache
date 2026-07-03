package moves.damagingmove.physical;

import battle_arena.BattleArena;
import pokemon.Pokemon;
import types.Fight;

public class CrossChop extends PhysicalDamagingMove{

    public CrossChop() {
        super(  "Cross Chop",                                                                                   //name
                "The user delivers a double chop with its forearms crossed.\n"+                                 //description
                "Critical hits land more easily.",                    
                100,                                                                                            //base power
                new Fight(),                                                                                    //type
                0.8,                                                                                            //accuracy
                critRange2,                                                                                     //crit range 
                5,                                                                                              //PP
                0);                                                                                             //Priority
    }

    @Override
    public void sideEffect(Pokemon user, Pokemon target, BattleArena battleArena) {
        // TODO Auto-generated method stub
        
    }

}
