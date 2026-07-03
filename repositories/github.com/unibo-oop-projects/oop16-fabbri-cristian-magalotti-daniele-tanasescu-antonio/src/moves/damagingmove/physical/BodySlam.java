package moves.damagingmove.physical;

import java.util.Random;

import battle_arena.BattleArena;
import pokemon.Pokemon;
import status_condition.Paralysis;
import types.Normal;

public class BodySlam extends PhysicalDamagingMove{

    public BodySlam() {
        super(  "Body Slam",                                                                                            //name
                "The user drops onto the target with its full body weigth.\n" +                                         //description
                "This may also leave the target with paralysis-",                        
                85,                                                                                                     //base power
                new Normal(),                                                                                           //type
                1,                                                                                                      //accuracy
                critRange1,                                                                                             //crit range 
                15,                                                                                                     //PP
                0);                                                                                                     //Priority
        this.sideEffect = true;
    }

    @Override
    public void sideEffect(Pokemon user, Pokemon target, BattleArena battleArena) {
        Random random = new Random();
        if(random.nextDouble() < 0.3){
            if(target.statusCondition == null){
                new Paralysis().setPokemonStatusCondition(target, battleArena);
            }
        }
    }   
}
