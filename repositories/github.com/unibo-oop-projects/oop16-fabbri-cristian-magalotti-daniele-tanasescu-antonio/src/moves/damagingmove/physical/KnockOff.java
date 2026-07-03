package moves.damagingmove.physical;

import battle_arena.BattleArena;
import pokemon.Pokemon;
import types.Dark;

public class KnockOff extends PhysicalDamagingMove{

    public KnockOff() {
        super(  "Knock Off",                                                                                     //name
                "The user slaps down the target's held item, and that item can't be used in that battle.\n" +    //description
                "The move does more damage if the target has a held item.",        
                95,                                                                                              //base power
                new Dark(),                                                                                      //type
                1,                                                                                               //accuracy
                critRange1,                                                                                      //crit range 
                20,                                                                                              //PP
                0);                                                                                              //Priority
    }

    @Override
    public void sideEffect(Pokemon user, Pokemon target, BattleArena battleArena) {
        // TODO Auto-generated method stub
        
    }

}
