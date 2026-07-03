package moves.damagingmove.special;

import java.util.Random;

import battle_arena.BattleArena;
import pokemon.Pokemon;
import types.Water;

public class Octazooka extends SpecialDamagingMove{

    public Octazooka() {
        super(  "Octazooka",                                                                                                               //name
                "The user attacks by spraying ink at the target's face or eyes. This may also lower the target's accuracy.",               //description
                65,                                                                                                                        //base power
                new Water(),                                                                                                               //type
                0.85,                                                                                                                      //accuracy
                critRange1,                                                                                                                //crit range 
                10,                                                                                                                        //PP
                0);                                                                                                                        //Priority
        this.sideEffect = true;
    }

    @Override
    public void sideEffect(Pokemon user, Pokemon target, BattleArena battleArena) {
        Random random = new Random();
        if(random.nextDouble() < 0.5){
            target.setAlterationAccuracy(-1, false);
        }
        
    }

}
