package moves.damagingmove.special;

import java.util.Random;

import battle_arena.BattleArena;
import pokemon.Pokemon;
import types.Psychic;

public class LusterPurge extends SpecialDamagingMove{

    public LusterPurge() {
        super(  "Luster Purge",                                                                                         //name
                "The user lets loose a damaging burst of light. This may also lower the target's Sp. Def stat.",        //description
                70,                                                                                                     //base power
                new Psychic(),                                                                                          //type
                1,                                                                                                      //accuracy
                critRange1,                                                                                             //crit range 
                5,                                                                                                      //PP
                0);                                                                                                     //Priority
        this.sideEffect = true;
    }

    @Override
    public void sideEffect(Pokemon user, Pokemon target, BattleArena battleArena) {
        Random random = new Random();
        if(random.nextDouble() < 0.5){
            target.setAlterationSpD(-1, false);
        }
        
    }

}
