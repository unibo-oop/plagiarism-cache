package moves.damagingmove.special;

import java.util.Random;

import battle_arena.BattleArena;
import pokemon.Pokemon;
import status_condition.Burn;
import types.Water;

public class Scald extends SpecialDamagingMove{

    public Scald() {
        super(  "Scald",                                                                                       //name
                "The user shoots boiling hot water at its target.\n"+                                          //description
                "This may also leave the target with a burn.",               
                80,                                                                                            //base power
                new Water(),                                                                                   //type
                1,                                                                                             //accuracy
                critRange1,                                                                                    //crit range 
                15,                                                                                            //PP
                0);                                                                                            //Priority
        this.sideEffect = true;
    }

    @Override
    public void sideEffect(Pokemon user, Pokemon target, BattleArena battleArena) {
        Random random = new Random();
        if(random.nextDouble() < 0.3){
            new Burn().setPokemonStatusCondition(target, battleArena);
        }
        
    }

}
