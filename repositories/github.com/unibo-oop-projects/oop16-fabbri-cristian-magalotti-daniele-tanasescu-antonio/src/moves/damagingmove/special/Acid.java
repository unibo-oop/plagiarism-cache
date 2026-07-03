package moves.damagingmove.special;

import java.util.Random;

import abilities.statisticsalterationondemand.*;
import battle_arena.BattleArena;
import pokemon.Pokemon;
import types.Poison;


public class Acid extends SpecialDamagingMove{

    public Acid() {
        super(  "Acid",                                                                 //name
                "The opposing Pokemon are attacked with a spray of harsh acid.\n" +     //description
                "This may also lower their Sp. Def stat.",              
                40,                                                                     //base power
                new Poison(),                                                           //type
                1,                                                                      //accuracy
                critRange1,                                                             //crit range 
                30,                                                                     //PP
                0);                                                                     //Priority
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