package moves.damagingmove.physical;

import java.util.Random;

import battle_arena.BattleArena;
import pokemon.Pokemon;
import status_condition.Freeze;
import types.Ice;

public class IcePunch extends PhysicalDamagingMove{

    public IcePunch() {
        super(  "Ice Punch",                                                                            //name
                "The target is punched with an icy fist. This may also leave the target frozen.",       //description
                75,                                                                                     //base power
                new Ice(),                                                                              //type
                1,                                                                                      //accuracy
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
        
    }

}
