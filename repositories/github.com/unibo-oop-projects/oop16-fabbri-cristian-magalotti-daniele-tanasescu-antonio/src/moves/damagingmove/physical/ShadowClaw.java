package moves.damagingmove.physical;

import battle_arena.BattleArena;
import pokemon.Pokemon;
import types.Ghost;

public class ShadowClaw extends PhysicalDamagingMove{

    public ShadowClaw() {
        super(  "Shadow Claw",                                                                                          //name
                "The user slashes with a sharp claw made from shadows."+                                                //description
                "Critical hits land more easily.",                                                                       
                70,                                                                                                     //base power
                new Ghost(),                                                                                            //type
                1,                                                                                                      //accuracy
                critRange2,                                                                                             //crit range 
                15,                                                                                                     //PP
                0);                                                                                                     //Priority
    }

    @Override
    public void sideEffect(Pokemon user, Pokemon target, BattleArena battleArena) {
        // TODO Auto-generated method stub
        
    }

}
