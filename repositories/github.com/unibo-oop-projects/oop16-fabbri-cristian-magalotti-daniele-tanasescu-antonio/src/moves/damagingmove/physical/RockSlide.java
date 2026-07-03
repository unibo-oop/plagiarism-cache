package moves.damagingmove.physical;

import java.util.Random;

import battle_arena.BattleArena;
import pokemon.Pokemon;
import status_condition.volatile_status.Flinch;
import types.Rock;

public class RockSlide extends PhysicalDamagingMove{

    public RockSlide() {
        super(  "Rock Slide",                                                                                           //name
                "Large boulders are hurled at the opposing Pokémon to inflict damage.\n"+                               //description
                "This may also make the opposing Pokémon flinch.",                                           
                75,                                                                                                     //base power
                new Rock(),                                                                                             //type
                0.90,                                                                                                   //accuracy
                critRange1,                                                                                             //crit range 
                10,                                                                                                     //PP
                0);                                                                                                     //Priority
        this.sideEffect = true;
    }

    @Override
    public void sideEffect(Pokemon user, Pokemon target, BattleArena battleArena) {
        Random random = new Random();
        if(random.nextDouble() < 0.3){
            new Flinch().addVolatile(target, target.volatileStatusConditions);
        }
        
    }

}
