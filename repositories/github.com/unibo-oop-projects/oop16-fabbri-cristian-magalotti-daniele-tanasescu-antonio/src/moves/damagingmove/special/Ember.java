package moves.damagingmove.special;

import java.util.Random;

import battle_arena.BattleArena;
import pokemon.Pokemon;
import status_condition.Burn;
import types.Fire;

public class Ember extends SpecialDamagingMove{

    public Ember() {
        super(  "Ember",                                                                                               //name
                "The target is attacked with small flames. This may also leave the target with a burn.",               //description
                40,                                                                                                    //base power
                new Fire(),                                                                                            //type
                1,                                                                                                     //accuracy
                critRange1,                                                                                            //crit range 
                25,                                                                                                    //PP
                0);                                                                                                    //Priority
        this.sideEffect = true;
    }

    @Override
    public void sideEffect(Pokemon user, Pokemon target, BattleArena battleArena) {
        Random random = new Random();
        if(random.nextDouble() < 0.1){
            if(target.statusCondition == null){
            	new Burn().setPokemonStatusCondition(target, battleArena);
            }
        }
        
    }

}
