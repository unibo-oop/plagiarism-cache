package moves.damagingmove.special;

import java.util.Random;

import battle_arena.BattleArena;
import pokemon.Pokemon;
import status_condition.Paralysis;
import types.Dragon;

public class DragonBreath extends SpecialDamagingMove{

    public DragonBreath() {
        super(  "Dragon Breath",                                                                                       //name
                "The user exhales a mighty gust that inflicts damage.\n"+                                              //description
                "This may also leave the target with paralysis.",              
                60,                                                                                                    //base power
                new Dragon(),                                                                                          //type
                1,                                                                                                     //accuracy
                critRange1,                                                                                            //crit range 
                20,                                                                                                    //PP
                0);                                                                                                    //Priority
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
