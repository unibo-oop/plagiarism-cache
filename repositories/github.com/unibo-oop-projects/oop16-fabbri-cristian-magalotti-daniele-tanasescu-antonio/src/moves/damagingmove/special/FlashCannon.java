package moves.damagingmove.special;

import java.util.Random;

import battle_arena.BattleArena;
import pokemon.Pokemon;

import types.Steel;

public class FlashCannon extends SpecialDamagingMove{

    public FlashCannon() {
        super(  "Flash Cannon",                                                                                               //name
                "The user gathers all its light energy and releases it all at once.\n"+                                       //description
                "This may also lower the target's Sp. Def stat.",               
                80,                                                                                                           //base power
                new Steel(),                                                                                                  //type
                1,                                                                                                            //accuracy
                critRange1,                                                                                                   //crit range 
                10,                                                                                                           //PP
                0);                                                                                                           //Priority
        this.sideEffect = true;
    }

    @Override
    public void sideEffect(Pokemon user, Pokemon target, BattleArena battleArena) {
        Random random = new Random();
        if(random.nextDouble() < 0.1){
            target.setAlterationSpD(-1, false);
        }
        
    }

}
