package moves.damagingmove.physical.selfrecoil;

import java.util.Random;

import battle_arena.BattleArena;
import pokemon.Pokemon;
import status_condition.Paralysis;
import types.Electric;

public class VoltTackle extends SelfRecoilPhysicalDamagingMove{

    public VoltTackle() {
        super(  "Volt Tackle",                                                                            //name
                "The user electrifies itself, then charges.\n"+                                           //description
                "It also damages the user quite a lot. This may leave the target with paralysis",            
                120,                                                                                      //base power
                new Electric(),                                                                           //type
                1,                                                                                        //accuracy
                critRange1,                                                                               //crit range                                                                                                      
                15,                                                                                       //PP
                0,                                                                                        //priority
                0.33,                                                                                     //recoil percentage
                false);                                                                                   //recoil after fail?
        this.sideEffect = true;
    }

    @Override
    public void sideEffect(Pokemon user, Pokemon target, BattleArena battleArena) {
    	Random random = new Random();
        if(random.nextDouble() < 0.1){
            new Paralysis().setPokemonStatusCondition(target, battleArena);
        }
        
    }

}
