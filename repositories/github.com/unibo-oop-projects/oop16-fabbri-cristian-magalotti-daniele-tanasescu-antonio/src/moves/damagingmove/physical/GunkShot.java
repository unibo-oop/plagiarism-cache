package moves.damagingmove.physical;

import java.util.Random;

import battle_arena.BattleArena;
import pokemon.Pokemon;
import types.Poison;

public class GunkShot extends PhysicalDamagingMove{

    public GunkShot() {
        super(  "Gunk Shot",                                                                          	//name
                "The user shoots filthy garbage at the target to attack\n"+                             //description
                "This may also poison the target.",       
                120,                                                                                    //base power
                new Poison(),                                                                           //type
                1,                                                                                      //accuracy
                critRange1,                                                                             //crit range 
                5,                                                                                      //PP
                0);                                                                                     //Priority
        this.sideEffect = true;
    }

    @Override
    public void sideEffect(Pokemon user, Pokemon target, BattleArena battleArena) {
        Random random = new Random();
        if(random.nextDouble() < 0.3){
            new status_condition.Poison().setPokemonStatusCondition(target, battleArena);
        }
        
    }

}
