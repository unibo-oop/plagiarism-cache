package moves.damagingmove.physical;

import battle_arena.BattleArena;
import pokemon.Pokemon;
import types.Normal;

public class CrushClaw extends PhysicalDamagingMove{

    public CrushClaw() {
        super(  "Crush Claw",                                                                                                   //name
                "The user slashes the target with hard and sharp claws.\n"+                                                     //description
                "This may also lower the target's Defense.",      
                75,                                                                                                             //base power
                new Normal(),                                                                                                   //type
                0.95,                                                                                                           //accuracy
                critRange1,                                                                                                     //crit range 
                10,                                                                                                             //PP
                0);                                                                                                             //Priority
    }

    @Override
    public void sideEffect(Pokemon user, Pokemon target, BattleArena battleArena) {
        // TODO Auto-generated method stub
        
    }

}
