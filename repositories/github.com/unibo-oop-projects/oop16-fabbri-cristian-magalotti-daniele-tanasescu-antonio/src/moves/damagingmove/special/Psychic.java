package moves.damagingmove.special;

import java.util.Random;

import battle_arena.BattleArena;
import pokemon.Pokemon;

public class Psychic extends SpecialDamagingMove{

    public Psychic() {
        super(  "Psychic",                                                                                              //name
                "The target is hit by a strong telekinetic force. This may also lower the target's Sp. Def stat.",      //description
                90,                                                                                                     //base power
                new types.Psychic(),                                                                                    //type
                1,                                                                                                      //accuracy
                critRange1,                                                                                             //crit range 
                10,                                                                                                     //PP
                0);                                                                                                     //Priority
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
