package moves.damagingmove.physical;

import java.util.Random;

import battle_arena.BattleArena;
import pokemon.Pokemon;
import types.Poison;

public class PoisonTail extends PhysicalDamagingMove{

    public PoisonTail() {
        super(  "Poison Tail",                                                                          //name
                "The user hits the target with its tail.\n"+                                            //description
                "This may also poison the target. Critical hits land more easily.",       
                50,                                                                                     //base power
                new Poison(),                                                                           //type
                1,                                                                                      //accuracy
                critRange2,                                                                             //crit range 
                25,                                                                                     //PP
                0);                                                                                     //Priority
        this.sideEffect = true;
    }

    @Override
    public void sideEffect(Pokemon user, Pokemon target, BattleArena battleArena) {
        Random random = new Random();
        if(random.nextDouble() < 0.1){
            new status_condition.Poison().setPokemonStatusCondition(target, battleArena);
        }
        
    }

}
