package moves.damagingmove.special;

import java.util.Random;

import abilities.statisticsalterationondemand.*;
import battle_arena.BattleArena;
import pokemon.Pokemon;
import types.Ice;

public class AuroraBeam extends SpecialDamagingMove{

    public AuroraBeam() {
        super(  "Aurora Beam",                                                                                                //name
                "The target is hit with a rainbow-colored beam. This may also lower the target's Attack stat.",               //description
                65,                                                                                                           //base power
                new Ice(),                                                                                                    //type
                1,                                                                                                            //accuracy
                critRange1,                                                                                                   //crit range 
                20,                                                                                                           //PP
                0);                                                                                                           //Priority
        this.sideEffect = true;
    }

    @Override
    public void sideEffect(Pokemon user, Pokemon target, BattleArena battleArena) {
        Random random = new Random();
        if(random.nextDouble() < 0.1){
            target.setAlterationAtk(-1, false);                        
        }
    }
}