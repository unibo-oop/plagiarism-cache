package moves.damagingmove.physical;

import java.util.Random;

import battle_arena.BattleArena;
import pokemon.Pokemon;
import status_condition.volatile_status.Flinch;
import types.Dragon;

public class DragonRush extends PhysicalDamagingMove{

    public DragonRush() {
        super(  "Dragon Rush",                                                                                             	//name
                "The user tackles the target while exhibiting overwhelming menace.\n"+                     					//description
                "This may also make the target flinch.",                        
                100,                                                                                                     	//base power
                new Dragon(),                                                                                           	//type
                0.75,                                                                                                      	//accuracy
                critRange1,                                                                                             	//crit range 
                15,                                                                                                     	//PP
                0);                                                                                                     	//Priority
        this.sideEffect = true;
    }

    @Override
    public void sideEffect(Pokemon user, Pokemon target, BattleArena battleArena) {
        Random random = new Random();
        if(random.nextDouble() < 0.2){
            new Flinch().addVolatile(target, target.volatileStatusConditions);
        }
        
    }

}
