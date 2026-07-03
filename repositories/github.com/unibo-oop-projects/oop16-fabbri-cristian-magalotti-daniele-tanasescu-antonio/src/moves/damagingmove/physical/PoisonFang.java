package moves.damagingmove.physical;

import java.util.Random;

import battle_arena.BattleArena;
import pokemon.Pokemon;
import status_condition.BadPoison;
import types.Poison;

public class PoisonFang extends PhysicalDamagingMove{

    public PoisonFang() {
        super(  "Poison Fang",                                                                                          //name
                "The user bites the target with toxic fangs. This may also leave the target badly poisoned.",           //description
                50,                                                                                                     //base power
                new Poison(),                                                                                           //type
                1,                                                                                                      //accuracy
                critRange1,                                                                                             //crit range 
                15,                                                                                                     //PP
                0);                                                                                                     //Priority
        this.sideEffect = true;
    }

    @Override
    public void sideEffect(Pokemon user, Pokemon target, BattleArena battleArena) {
        Random random = new Random();
        if(random.nextDouble() < 0.5){
            new BadPoison().setPokemonStatusCondition(target, battleArena);
        }
        
    }

}
