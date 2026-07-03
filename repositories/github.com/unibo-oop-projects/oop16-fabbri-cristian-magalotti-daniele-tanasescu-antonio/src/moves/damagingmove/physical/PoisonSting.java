package moves.damagingmove.physical;

import java.util.Random;

import battle_arena.BattleArena;
import pokemon.Pokemon;
import types.Poison;

public class PoisonSting extends PhysicalDamagingMove{

    public PoisonSting() {
        super(  "Poison Sting",                                                                                         //name
                "The user stabs the target with a poisonous stinger. This may also poison the target.",                 //description
                15,                                                                                                     //base power
                new Poison(),                                                                                           //type
                1,                                                                                                      //accuracy
                critRange1,                                                                                             //crit range 
                35,                                                                                                     //PP
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
            }
        }
        
    }

}
