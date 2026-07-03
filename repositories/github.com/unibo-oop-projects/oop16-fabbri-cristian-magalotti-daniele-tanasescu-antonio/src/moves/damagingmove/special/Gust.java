package moves.damagingmove.special;

import battle_arena.BattleArena;
import pokemon.Pokemon;
import types.Flying;

public class Gust extends SpecialDamagingMove{

    public Gust() {
        super(  "Gust",                                                                                               //name
                "A gust of wind is whipped up by wings and launched at the target to inflict damage.",                //description
                40,                                                                                                   //base power
                new Flying(),                                                                                         //type
                1,                                                                                                    //accuracy
                critRange1,                                                                                           //crit range 
                35,                                                                                                   //PP
                0);                                                                                                   //Priority
    }

    @Override
    public void sideEffect(Pokemon user, Pokemon target, BattleArena battleArena) {
        // TODO Auto-generated method stub
        
    }

}
