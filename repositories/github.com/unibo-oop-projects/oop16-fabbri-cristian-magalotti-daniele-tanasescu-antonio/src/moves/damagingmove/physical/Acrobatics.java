package moves.damagingmove.physical;

import battle_arena.BattleArena;
import pokemon.Pokemon;
import types.Flying;

public class Acrobatics extends PhysicalDamagingMove{

    public Acrobatics() {
        super(  "Acrobatics",                                                                           //name
                "The user nimbly strikes the target.",                                                  //description
                110,                                                                                    //base power
                new Flying(),                                                                           //type
                1,                                                                                      //accuracy
                critRange1,                                                                             //crit range 
                15,                                                                                     //PP
                0);                                                                                     //Priority
    }

    @Override
    public void sideEffect(Pokemon user, Pokemon target, BattleArena battleArena) {
        // TODO Auto-generated method stub
        
    }

}
