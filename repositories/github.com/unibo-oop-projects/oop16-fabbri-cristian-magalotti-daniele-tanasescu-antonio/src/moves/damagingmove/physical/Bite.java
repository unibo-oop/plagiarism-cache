package moves.damagingmove.physical;

import java.util.Random;

import battle_arena.BattleArena;
import pokemon.Pokemon;
import status_condition.volatile_status.Flinch;
import types.Dark;

public class Bite extends PhysicalDamagingMove{

    public Bite() {
        super(  "Bite",                                                                                          //name
                "The target is bitten with viciously sharp fangs. This may also make the target flinch.",        //description
                60,                                                                                              //base power
                new Dark(),                                                                                      //type
                1,                                                                                               //accuracy
                critRange1,                                                                                      //crit range 
                25,                                                                                              //PP
                0);                                                                                              //Priority
        this.sideEffect = true;
    }

    @Override
    public void sideEffect(Pokemon user, Pokemon target, BattleArena battleArena) {
        Random random = new Random();
        if(random.nextDouble() < 0.3){
            new Flinch().addVolatile(target, target.volatileStatusConditions);
        }
        
    }

}
