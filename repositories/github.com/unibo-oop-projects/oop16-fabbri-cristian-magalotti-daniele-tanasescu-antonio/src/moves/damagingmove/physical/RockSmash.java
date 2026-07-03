package moves.damagingmove.physical;

import java.util.Random;

import battle_arena.BattleArena;
import pokemon.Pokemon;
import types.Fight;

public class RockSmash extends PhysicalDamagingMove{

    public RockSmash() {
        super(  "Rock Smash",                                                                                   //name
                "The user attacks with a punch. This may also lower the target's Defense stat.",                //description
                40,                                                                                             //base power
                new Fight(),                                                                                    //type
                1,                                                                                              //accuracy
                critRange1,                                                                                     //crit range 
                15,                                                                                             //PP
                0);                                                                                             //Priority
        this.sideEffect = true;
    }

    @Override
    public void sideEffect(Pokemon user, Pokemon target, BattleArena battleArena) {
        Random random = new Random();
        if(random.nextDouble() < 0.5){
            target.setAlterationDef(-1, false);
        }
        
    }

}
