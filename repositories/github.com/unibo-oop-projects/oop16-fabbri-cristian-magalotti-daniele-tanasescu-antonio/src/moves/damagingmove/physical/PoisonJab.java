package moves.damagingmove.physical;

import java.util.Random;

import battle_arena.BattleArena;
import pokemon.Pokemon;
import types.Poison;

public class PoisonJab extends PhysicalDamagingMove{

    public PoisonJab() {
        super(  "Poison Jab",                                                                                           //name
                "The target is stabbed with a tentacle or arm steeped in poison.\n"+                                    //description
                "This may also poison the target.",                 
                80,                                                                                                     //base power
                new Poison(),                                                                                           //type
                1,                                                                                                      //accuracy
                critRange1,                                                                                             //crit range 
                20,                                                                                                     //PP
                0);                                                                                                     //Priority
        this.sideEffect = true;
        this.setMakingContact(false);
    }

    @Override
    public void sideEffect(Pokemon user, Pokemon target, BattleArena battleArena) {
        Random random = new Random();
        if(random.nextDouble() < 0.3){
            if(target.statusCondition == null){
                new status_condition.Poison().setPokemonStatusCondition(target, battleArena);
                //message
            }
        }
        
    }

}
