package moves.damagingmove.physical;

import battle_arena.BattleArena;
import pokemon.Pokemon;
import types.Dragon;

public class DragonClaw extends PhysicalDamagingMove{

    public DragonClaw() {
        super(  "Dragon Claw",                                                                                          //name
                "The user slashes the target with huge sharp claws.",                                                   //description                     
                80,                                                                                                     //base power
                new Dragon(),                                                                                           //type
                1,                                                                                                      //accuracy
                critRange1,                                                                                             //crit range 
                15,                                                                                                     //PP
                0);                                                                                                     //Priority
    }

    @Override
    public void sideEffect(Pokemon user, Pokemon target, BattleArena battleArena) {
        // TODO Auto-generated method stub
        
    }

}
