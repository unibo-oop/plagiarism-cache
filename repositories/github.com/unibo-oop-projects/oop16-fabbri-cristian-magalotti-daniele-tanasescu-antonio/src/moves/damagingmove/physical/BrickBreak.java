package moves.damagingmove.physical;

import battle_arena.BattleArena;
import pokemon.Pokemon;
import types.Fight;

public class BrickBreak extends PhysicalDamagingMove{

    public BrickBreak() {
        super(  "Brick Break",                                                                                  //name
                "The user attacks with a swift chop.\n"+                                                        //description
                "It can also break barriers, such as Light Screen and Reflect.",                    
                75,                                                                                            //base power
                new Fight(),                                                                                    //type
                1,                                                                                              //accuracy
                critRange1,                                                                                     //crit range 
                15,                                                                                             //PP
                0);                                                                                             //Priority
    }

    @Override
    public void sideEffect(Pokemon user, Pokemon target, BattleArena battleArena) {
        // TODO Auto-generated method stub
        
    }

}
