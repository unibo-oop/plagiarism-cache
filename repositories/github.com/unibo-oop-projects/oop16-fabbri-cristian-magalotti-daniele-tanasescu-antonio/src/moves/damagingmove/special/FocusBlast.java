package moves.damagingmove.special;

import java.util.Random;

import battle_arena.BattleArena;
import pokemon.Pokemon;
import types.Fight;

public class FocusBlast extends SpecialDamagingMove{

    public FocusBlast() {
        super(  "Focus Blast",                                                                                                //name
                "The user heightens its mental focus and unleashes its power.\n"+                                             //description
                "This may also lower the target's Sp. Def stat.",               
                120,                                                                                                          //base power
                new Fight(),                                                                                                  //type
                0.7,                                                                                                          //accuracy
                critRange1,                                                                                                   //crit range 
                5,                                                                                                            //PP
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
