package moves.damagingmove.special;

import java.util.Random;

import battle_arena.BattleArena;
import pokemon.Pokemon;
import types.Ghost;

public class ShadowBall extends SpecialDamagingMove{

    public ShadowBall() {
        super(  "Shadow Ball",                                                                                         //name
                "The user hurls a shadowy blob at the target. This may also lower the target's Sp. Def stat.",         //description
                80,                                                                                                    //base power
                new Ghost(),                                                                                           //type
                1,                                                                                                     //accuracy
                critRange1,                                                                                            //crit range 
                15,                                                                                                    //PP
                0);                                                                                                    //Priority
        this.sideEffect = true;
    }

    @Override
    public void sideEffect(Pokemon user, Pokemon target, BattleArena battleArena) {
        Random random = new Random();
        if(random.nextDouble() < 0.2){
            target.setAlterationSpD(-1, false);
        }
        
    }

}
