package moves.damagingmove.physical;

import java.util.Random;

import battle_arena.BattleArena;
import pokemon.Pokemon;
import status_condition.Paralysis;
import types.Ghost;

public class Lick extends PhysicalDamagingMove{

    public Lick() {
        super(  "Lick",                                                                                    //name
                "The target is licked with a tongue, causing damage. This may also leave the target with paralysis.",  //description
                30,                                                                                              //base power
                new Ghost(),                                                                                      //type
                1,                                                                                               //accuracy
                critRange1,                                                                                      //crit range 
                30,                                                                                              //PP
                0);                                                                                              //Priority
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
