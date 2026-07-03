package moves.damagingmove.special;

import java.util.Random;

import battle_arena.BattleArena;
import pokemon.Pokemon;
import types.Fairy;

public class Moonblast extends SpecialDamagingMove{

    public Moonblast() {
        super(  "Moonblast",                                                                                                              //name
                "Borrowing the power of the moon, the user attacks the target. This may also lower the target's Sp. Atk stat.",           //description
                95,                                                                                                                       //base power
                new Fairy(),                                                                                                              //type
                1,                                                                                                                        //accuracy
                critRange1,                                                                                                               //crit range 
                15,                                                                                                                       //PP
                0);                                                                                                                       //Priority
        this.sideEffect = true;
    }

    @Override
    public void sideEffect(Pokemon user, Pokemon target, BattleArena battleArena) {
        Random random = new Random();
        if(random.nextDouble() < 0.3){
            target.setAlterationSpA(-1, false);
        }
        
    }

}
