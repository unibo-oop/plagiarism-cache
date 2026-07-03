package moves.damagingmove.physical;

import java.util.Random;

import battle_arena.BattleArena;
import pokemon.Pokemon;
import status_condition.volatile_status.Flinch;
import types.Grass;

public class NeedleArm extends PhysicalDamagingMove{

    public NeedleArm() {
        super(  "Needle Arm",                                                   //name
                "The user attacks by wildly swinging its thorny arms.\n"+       //description
                "This may also make the target flinch.",                    
                60,                                                             //base power
                new Grass(),                                                    //type
                1,                                                              //accuracy
                critRange1,                                                     //crit range 
                15,                                                             //PP
                0);                                                             //Priority
        this.sideEffect = true;
    }

    @Override
    public void sideEffect(Pokemon user, Pokemon target, BattleArena battleArena) {
        Random random = new Random();
        if(random.nextDouble() < 0.3){
            new Flinch().addVolatile(target, target.volatileStatusConditions);
        }
        
    }

}
