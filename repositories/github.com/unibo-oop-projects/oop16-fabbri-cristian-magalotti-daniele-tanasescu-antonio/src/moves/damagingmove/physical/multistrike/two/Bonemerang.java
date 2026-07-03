package moves.damagingmove.physical.multistrike.two;

import battle_arena.BattleArena;
import pokemon.Pokemon;
import types.Ground;

public class Bonemerang extends TwoMultiStrikePhysicalDamagingMove{

    public Bonemerang() {
        super(  "Bonemerang",                                                                                                   //name
                "The user throws the bone it holds. The bone loops around to hit the target twice—coming and going.",           //description
                50,                                                                                                             //base power
                new Ground(),                                                                                                   //type
                0.9,                                                                                                            //accuracy
                critRange1,                                                                                                     //crit range 
                10,                                                                                                             //PP
                0);                                                                                                             //Priority
    }

    @Override
    public void sideEffect(Pokemon user, Pokemon target, BattleArena battleArena) {
        // TODO Auto-generated method stub
        
    }


}
