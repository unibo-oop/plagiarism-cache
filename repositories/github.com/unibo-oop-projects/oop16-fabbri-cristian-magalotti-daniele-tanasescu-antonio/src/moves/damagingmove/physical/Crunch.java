package moves.damagingmove.physical;

import java.util.Random;

import battle_arena.BattleArena;
import pokemon.Pokemon;
import types.Dark;

public class Crunch extends PhysicalDamagingMove{

    public Crunch() {
        super(  "Crunch",                                                                                        //name
                "The user crunches up the target with sharp fangs.\n"+                                           //description
                "This may also lower the target's Defense stat.",        
                80,                                                                                              //base power
                new Dark(),                                                                                      //type
                1,                                                                                               //accuracy
                critRange1,                                                                                      //crit range 
                15,                                                                                              //PP
                0);                                                                                              //Priority
        this.sideEffect = true;
    }

    @Override
    public void sideEffect(Pokemon user, Pokemon target, BattleArena battleArena) {
        Random random = new Random();
        if(random.nextDouble() < 0.2){
            target.setAlterationDef(-1, false);
        }
        
    }

}
