package moves.damagingmove.physical;

import java.util.Random;

import battle_arena.BattleArena;
import pokemon.Pokemon;
import status_condition.Paralysis;
import types.Electric;

public class Spark extends PhysicalDamagingMove{

    public Spark() {
        super(  "Spark",                                                                                         //name
                "The user throws an electrically charged tackle at the target.\n"+                               //description
                "This may also leave the target with paralysis.",          
                65,                                                                                              //base power
                new Electric(),                                                                                      //type
                1,                                                                                               //accuracy
                critRange1,                                                                                      //crit range 
                20,                                                                                              //PP
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
