package moves.damagingmove.special;

import java.util.Random;

import battle_arena.BattleArena;
import pokemon.Pokemon;
import types.Poison;

public class Sludge extends SpecialDamagingMove{

    public Sludge() {
        super(  "Sludge",                                                                                              //name
                "Unsanity sludge is hurled at the target. This may also poison the target.",       				       //description
                65,                                                                                                    //base power
                new Poison(),                                                                                          //type
                1,                                                                                                     //accuracy
                critRange1,                                                                                            //crit range 
                20,                                                                                                    //PP
                0);                                                                                                    //Priority
        this.sideEffect = true;
    }

    @Override
    public void sideEffect(Pokemon user, Pokemon target, BattleArena battleArena) {
        Random random = new Random();
        if(random.nextDouble() < 0.4){
            if(target.statusCondition == null){
            	new status_condition.Poison().setPokemonStatusCondition(target, battleArena);
            }
        }
        
    }

}
