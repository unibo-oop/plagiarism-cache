package moves.damagingmove.physical.multistrike.two;

import java.util.Random;

import battle_arena.BattleArena;
import pokemon.Pokemon;
import status_condition.Poison;
import types.Bug;

public class Twineedle extends TwoMultiStrikePhysicalDamagingMove{

    public Twineedle() {
        super(  "Twineedle",                                                                                    //name
                "The user damages the target twice in succession by jabbing it with two spikes.\n"+             //description
                "This may also poison the target. ",                          
                25,                                                                                             //base power
                new Bug(),                                                                                      //type
                1,                                                                                              //accuracy
                critRange1,                                                                                     //crit range 
                20,                                                                                             //PP
                0);                                                                                             //Priority
        this.sideEffect = true;
    }

    @Override
    public void sideEffect(Pokemon user, Pokemon target, BattleArena battleArena) {
        Random random = new Random();
        if(random.nextDouble() < 0.2){
            if(target.statusCondition == null){
            	new Poison().setPokemonStatusCondition(target, battleArena);
            }
            else{
                //messaggio
            }
        }
    }

}
