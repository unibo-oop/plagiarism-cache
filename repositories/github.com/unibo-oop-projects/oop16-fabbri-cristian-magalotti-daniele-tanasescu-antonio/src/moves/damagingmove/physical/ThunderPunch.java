package moves.damagingmove.physical;

import java.util.Random;

import battle_arena.BattleArena;
import pokemon.Pokemon;
import status_condition.Paralysis;
import types.Electric;

public class ThunderPunch extends PhysicalDamagingMove{

    public ThunderPunch() {
        super(  "Thunder Punch",                                                                                        //name
                "The target is punched with an electrified fist. This may also leave the target with paralysis.",       //description
                75,                                                                                                     //base power
                new Electric(),                                                                                         //type
                1,                                                                                                      //accuracy
                critRange1,                                                                                             //crit range 
                15,                                                                                                     //PP
                0);                                                                                                     //Priority
        this.sideEffect = true;
    }

    @Override
    public void sideEffect(Pokemon user, Pokemon target, BattleArena battleArena) {
        Random random = new Random();
        if(random.nextDouble() < 0.1){
            if(target.statusCondition == null){
              new Paralysis().setPokemonStatusCondition(target, battleArena);
            }
        }
    }

}
