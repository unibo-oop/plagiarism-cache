package moves.damagingmove.special;

import java.util.Random;

import battle_arena.BattleArena;
import pokemon.Pokemon;
import status_condition.Paralysis;
import types.Electric;

public class Thunder extends SpecialDamagingMove{

    public Thunder() {
        super(  "Thunder",                                                                                                    //name
                "A wicked thunderbolt is dropped on the target to inflict damage.\n"+                                         //description
                "This may also leave the target with paralysis.",        
                110,                                                                                                          //base power
                new Electric(),                                                                                               //type
                0.7,                                                                                                          //accuracy
                critRange1,                                                                                                   //crit range 
                10,                                                                                                           //PP
                0);                                                                                                           //Priority
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
