package moves.damagingmove.special;

import java.util.Random;

import battle_arena.BattleArena;
import pokemon.Pokemon;
import status_condition.Burn;
import types.Fire;

public class FireBlast extends SpecialDamagingMove{

    public FireBlast() {
        super(  "Fire Blast",                                                                                          //name
                "The target is attacked with an intense blast of all-consuming fire.\n" +
        		"This may also leave the target with a burn.",     											           //description
                110,                                                                                                   //base power
                new Fire(),                                                                                            //type
                0.85,                                                                                                  //accuracy
                critRange1,                                                                                            //crit range 
                5,                                                                                                     //PP
                0);                                                                                                    //Priority
        this.sideEffect = true;
    }

    @Override
    public void sideEffect(Pokemon user, Pokemon target, BattleArena battleArena) {
        Random random = new Random();
        if(random.nextDouble() < 0.3){
            if(target.statusCondition == null){
            	new Burn().setPokemonStatusCondition(target, battleArena);
            }
        }
        
    }

}
