package moves.damagingmove.physical;

import battle_arena.BattleArena;
import pokemon.Pokemon;
import types.Normal;

public class Rage extends PhysicalDamagingMove{

    public Rage() {
        super(  "Rage",                                                                                                 //name
                "As long as this move is in use,\n"+                                                                    //description
                "the power of rage raises the Attack stat each time the user is hit in battle.",      
                20,                                                                                                     //base power
                new Normal(),                                                                                           //type
                1,                                                                                                      //accuracy
                critRange1,                                                                                             //crit range 
                20,                                                                                                     //PP
                0);                                                                                                     //Priority
    }

    @Override
    public void sideEffect(Pokemon user, Pokemon target, BattleArena battleArena) {
        //user <- volatile status condition add "rage", if hitten altAtk++    last 1 turn
    }

}
