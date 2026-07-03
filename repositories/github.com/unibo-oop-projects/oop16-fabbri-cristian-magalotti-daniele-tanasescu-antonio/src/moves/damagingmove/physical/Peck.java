package moves.damagingmove.physical;

import battle_arena.BattleArena;
import pokemon.Pokemon;
import types.Flying;

public class Peck extends PhysicalDamagingMove{

    public Peck() {
        super(  "Peck",                                                                                                 //name
                "The target is jabbed with a sharply pointed beak or horn.",                                           //description
                35,                                                                                                     //base power
                new Flying(),                                                                                           //type
                1,                                                                                                      //accuracy
                critRange1,                                                                                             //crit range 
                35,                                                                                                     //PP
                0);                                                                                                     //Priority
    }

    @Override
    public void sideEffect(Pokemon user, Pokemon target, BattleArena battleArena) {
        // TODO Auto-generated method stub
        
    }

}
