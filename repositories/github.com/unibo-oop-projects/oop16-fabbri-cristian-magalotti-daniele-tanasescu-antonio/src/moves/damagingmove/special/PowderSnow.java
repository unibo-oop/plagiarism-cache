package moves.damagingmove.special;

import java.util.Random;

import battle_arena.BattleArena;
import pokemon.Pokemon;
import status_condition.Freeze;
import types.Ice;

public class PowderSnow extends SpecialDamagingMove{

    public PowderSnow() {
        super(  "Powder Snow",                                                                                                //name
                "The user attacks with a chilling gust of powdery snow. This may also freeze the opposing Pokemon.",          //description
                40,                                                                                                           //base power
                new Ice(),                                                                                                    //type
                1,                                                                                                            //accuracy
                critRange1,                                                                                                   //crit range 
                25,                                                                                                           //PP
                0);                                                                                                           //Priority
        this.sideEffect = true;
    }

    @Override
    public void sideEffect(Pokemon user, Pokemon target, BattleArena battleArena) {
        Random random = new Random();
        if(random.nextDouble() < 0.1){
            new Freeze().setPokemonStatusCondition(target, battleArena);
        }
        
    }

}
