package moves.damagingmove.physical;

import java.util.Random;

import battle_arena.BattleArena;
import pokemon.Pokemon;
import status_condition.Burn;
import status_condition.Freeze;
import types.Fire;

public class SacredFire extends PhysicalDamagingMove{

    public SacredFire() {
        super(  "Sacred Fire",                                                                                                       //name
                "The target is razed with a mystical fire of great intensity. This may also leave the target with a burn.",          //description
                100,                                                                                                                 //base power
                new Fire(),                                                                                                          //type
                0.95,                                                                                                                //accuracy
                critRange1,                                                                                                          //crit range 
                5,                                                                                                                   //PP
                0);                                                                                                                  //Priority
        this.sideEffect = true;
        this.setMakingContact(false);
    }

    @Override
    public void sideEffect(Pokemon user, Pokemon target, BattleArena battleArena) {
        if(target.statusCondition != null && target.statusCondition.equals(new Freeze())){
            target.statusCondition.exitingStatusAlteration(target);
        }
        Random random = new Random();
        if(random.nextDouble() < 0.5){
            if(target.statusCondition == null){
                new Burn().setPokemonStatusCondition(target, battleArena);
            }
        }
        
    }

}
