package moves.damagingmove.physical;

import java.util.Random;

import battle_arena.BattleArena;
import pokemon.Pokemon;
import status_condition.Burn;
import types.Fire;

public class FlameWheel extends PhysicalDamagingMove{

    public FlameWheel() {
        super(  "Flame Wheel",                                                                                                          //name
                "The user cloaks itself in fire and charges at the target. This may also leave the target with a burn.",                //description
                65,                                                                                                                     //base power
                new Fire(),                                                                                                             //type
                1,                                                                                                                      //accuracy
                critRange2,                                                                                                             //crit range 
                25,                                                                                                                     //PP
                0);                                                                                                                     //Priority
        this.sideEffect = true;
    }

    @Override
    public void sideEffect(Pokemon user, Pokemon target, BattleArena battleArena) {
        Random random = new Random();
        if(random.nextDouble() < 0.1){
            new Burn().setPokemonStatusCondition(target, battleArena);
        }
        
    }

}
