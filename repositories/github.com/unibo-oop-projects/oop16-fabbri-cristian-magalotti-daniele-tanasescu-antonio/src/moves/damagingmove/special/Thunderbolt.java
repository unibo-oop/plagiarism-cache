package moves.damagingmove.special;

import java.util.Random;

import battle_arena.BattleArena;
import pokemon.Pokemon;
import status_condition.Paralysis;
import types.Electric;

public class Thunderbolt extends SpecialDamagingMove{

    public Thunderbolt() {
        super(  "Thunderbolt",                                                                                                //name
                "A strong electric blast crashes down on the target. This may also leave the target with paralysis.",         //description
                90,                                                                                                           //base power
                new Electric(),                                                                                               //type
                1,                                                                                                            //accuracy
                critRange1,                                                                                                   //crit range 
                15,                                                                                                           //PP
                0);                                                                                                           //Priority
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
