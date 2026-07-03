package moves.damagingmove.special;

import java.util.Random;

import battle_arena.BattleArena;
import pokemon.Pokemon;
import types.Poison;

public class Smog extends SpecialDamagingMove{

    public Smog() {
        super(  "Smog",                                                                                                       //name
                "The target is attacked with a discharge of filthy gases. This may also poison the target.",                  //description
                30,                                                                                                           //base power
                new Poison(),                                                                                                 //type
                0.70,                                                                                                         //accuracy
                critRange1,                                                                                                   //crit range 
                20,                                                                                                           //PP
                0);                                                                                                           //Priority
        this.sideEffect = true;
    }

    @Override
    public void sideEffect(Pokemon user, Pokemon target, BattleArena battleArena) {
       Random random = new Random();
       if(random.nextDouble() < 0.4){
           if(target.statusCondition == null){
               target.statusCondition = new status_condition.Poison();
               //message
           }
       }
        
    }

}
