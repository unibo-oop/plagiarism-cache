package moves.damagingmove.physical.selfrecoil;

import java.util.Random;

import battle_arena.BattleArena;
import pokemon.Pokemon;
import status_condition.Burn;
import types.Fire;

public class FlareBlitz extends SelfRecoilPhysicalDamagingMove{

    public FlareBlitz() {
        super(  "Fire Blitz",                                                                                                     //name
                "The user cloaks itself in fire and charges the target."
                + "This also damages the user quite a lol. This may leave the target with a bun.",                                //description
                120,                                                                                                              //base power
                new Fire(),                                                                                                       //type
                1,                                                                                                                //accuracy
                critRange1,                                                                                                       //crit range                                                                                                      
                15,                                                                                                               //PP
                0,                                                                                                                //priority
                0.33,                                                                                                             //recoil percentage
                false);                                                                                                           //recoil after fail?
    }

    @Override
    public void sideEffect(Pokemon user, Pokemon target, BattleArena battleArena) {
    	Random random = new Random();
        if(random.nextDouble() < 0.1){
            new Burn().setPokemonStatusCondition(target, battleArena);
        }
        
    }

}
