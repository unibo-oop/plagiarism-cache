package moves.damagingmove.physical;

import java.util.Random;

import battle_arena.BattleArena;
import pokemon.Pokemon;
import status_condition.Freeze;
import status_condition.volatile_status.Flinch;
import types.Ice;

public class IceFang extends PhysicalDamagingMove{

    public IceFang() {
        super(  "Ice Fang",                                                                             //name
                "The user bites with cold-infused fangs."
                + "This may also make the target flinch or leave it frozen.",      					    //description
                65,                                                                                     //base power
                new Ice(),                                                                              //type
                0.95,                                                                                      //accuracy
                critRange1,                                                                             //crit range 
                15,                                                                                     //PP
                0);                                                                                     //Priority
        this.sideEffect = true;
    }

    @Override
    public void sideEffect(Pokemon user, Pokemon target, BattleArena battleArena) {
        Random random = new Random();
        if(random.nextDouble() < 0.1){
            if(target.statusCondition == null){
            	new Freeze().setPokemonStatusCondition(target, battleArena);       
            }
        }
        else if(random.nextDouble() < 0.1){
        	new Flinch().addVolatile(target, target.volatileStatusConditions);
        }
    }
}
