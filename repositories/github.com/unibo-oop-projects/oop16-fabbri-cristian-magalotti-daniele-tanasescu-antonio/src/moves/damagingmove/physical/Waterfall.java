package moves.damagingmove.physical;

import java.util.Random;

import battle_arena.BattleArena;
import pokemon.Pokemon;
import status_condition.volatile_status.Flinch;
import types.Water;

public class Waterfall extends PhysicalDamagingMove{

    public Waterfall() {
        super(  "Waterfall",                                                                                            //name
                "The user charges at the target and may make it flinch.",                                               //description
                80,                                                                                                     //base power
                new Water(),                                                                                            //type
                1,                                                                                                      //accuracy
                critRange1,                                                                                             //crit range 
                15,                                                                                                     //PP
                0);                                                                                                     //Priority
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
