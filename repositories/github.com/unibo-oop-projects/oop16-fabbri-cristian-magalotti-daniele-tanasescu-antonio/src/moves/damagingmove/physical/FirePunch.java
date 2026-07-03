package moves.damagingmove.physical;

import java.util.Random;

import battle_arena.BattleArena;
import pokemon.Pokemon;
import status_condition.Burn;
import types.Fire;

public class FirePunch extends PhysicalDamagingMove{

    public FirePunch() {
        super(  "Fire Punch",                                                                                    //name
                "The target is punched with a fiery fist. This may also leave the target with a burn.",          //description
                75,                                                                                              //base power
                new Fire(),                                                                                      //type
                1,                                                                                               //accuracy
                critRange1,                                                                                      //crit range 
                15,                                                                                              //PP
                0);                                                                                              //Priority
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
