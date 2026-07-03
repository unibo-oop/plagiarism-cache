package moves.damagingmove.physical;

import java.util.Random;

import battle_arena.BattleArena;
import pokemon.Pokemon;
import status_condition.volatile_status.Confusion;
import types.Normal;

public class ChirpChirpPunch extends PhysicalDamagingMove{

    public ChirpChirpPunch() {
        super(  "Chirp Chirp Punch",                                                                                            //name
                "The target is hit with rhythmically launched punches.\n" +                                         //description
                "This may also leave the target confused.",                        
                70,                                                                                                     //base power
                new Normal(),                                                                                           //type
                1,                                                                                                      //accuracy
                critRange1,                                                                                             //crit range 
                10,                                                                                                     //PP
                0);                                                                                                     //Priority
        this.sideEffect = true;
    }

    @Override
    public void sideEffect(Pokemon user, Pokemon target, BattleArena battleArena) {
        Random random = new Random();
        if(random.nextDouble() < 0.2){
        	Confusion confusion = new Confusion();
            if(!confusion.isContained(target.volatileStatusConditions)){
                confusion.addVolatile(target, target.volatileStatusConditions);
            }
            
        }

    }
}
