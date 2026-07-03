package moves.damagingmove.special;

import java.util.Random;

import battle_arena.BattleArena;
import pokemon.Pokemon;
import status_condition.Freeze;
import types.Ice;

public class IceBeam extends SpecialDamagingMove{

    public IceBeam() {
        super(  "Ice Beam",                                                                                                   //name
                "The target is struck with an icy-cold beam of energy. This may also leave the target frozen.",               //description
                90,                                                                                                           //base power
                new Ice(),                                                                                                    //type
                1,                                                                                                            //accuracy
                critRange1,                                                                                                   //crit range 
                10,                                                                                                           //PP
                0);                                                                                                           //Priority
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
