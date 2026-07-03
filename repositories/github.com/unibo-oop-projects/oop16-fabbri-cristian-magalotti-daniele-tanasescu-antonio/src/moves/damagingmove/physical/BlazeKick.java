package moves.damagingmove.physical;

import java.util.Random;

import battle_arena.BattleArena;
import pokemon.Pokemon;
import status_condition.Burn;
import types.Fire;

public class BlazeKick extends PhysicalDamagingMove{

    public BlazeKick() {
        super(  "Blaze Kick",                                                                                                           //name
                "The user launches a kick that lands a critical hit more easily. This may also leave the target with a burn.",          //description
                85,                                                                                                                     //base power
                new Fire(),                                                                                                             //type
                0.9,                                                                                                                    //accuracy
                critRange2,                                                                                                             //crit range 
                10,                                                                                                                     //PP
                0);                                                                                                                     //Priority
        this.sideEffect = true;
    }

    @Override
    public void sideEffect(Pokemon user, Pokemon target, BattleArena battleArena) {
        Random random = new Random();
        if(random.nextDouble() < 0.1){
            new Burn().setPokemonStatusCondition(target, battleArena);
        }
        
    }

}
