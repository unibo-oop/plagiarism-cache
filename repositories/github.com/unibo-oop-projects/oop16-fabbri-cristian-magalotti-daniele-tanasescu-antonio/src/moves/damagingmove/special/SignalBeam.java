package moves.damagingmove.special;

import java.util.Random;

import battle_arena.BattleArena;
import pokemon.Pokemon;
import status_condition.volatile_status.Confusion;
import types.Bug;

public class SignalBeam extends SpecialDamagingMove{

    public SignalBeam() {
        super(  "Signal Beam",                                                                                           //name
                "The user attacks with a sinister beam of light.\n"+                                                     //description
                "This may also confuse the target.",                                   
                75,                                                                                                      //base power
                new Bug(),                                                                                            //type
                1,                                                                                                       //accuracy
                critRange1,                                                                                              //crit range 
                15,                                                                                                      //PP
                0);                                                                                                      //Priority
        this.sideEffect = true;
    }

    @Override
    public void sideEffect(Pokemon user, Pokemon target, BattleArena battleArena) {
        Random random = new Random();
        if(random.nextDouble() < 0.1){
            Confusion confusion = new Confusion();
            if(!confusion.isContained(target.volatileStatusConditions)){
                confusion.addVolatile(target, target.volatileStatusConditions);
            }
        }
        
    }

}