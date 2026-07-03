package moves.damagingmove.special;

import java.util.Random;

import battle_arena.BattleArena;
import pokemon.Pokemon;
import status_condition.Burn;
import types.Fire;

public class HeatWave extends SpecialDamagingMove{

    public HeatWave() {
        super(  "Heat Wave",                                                                                                              //name
                "The user attacks by exhaling hot breath on the opposing Pokémon. This may also leave those Pokémon with a burn.",        //description
                95,                                                                                                                       //base power
                new Fire(),                                                                                                               //type
                0.9,                                                                                                                      //accuracy
                critRange1,                                                                                                               //crit range 
                10,                                                                                                                       //PP
                0);                                                                                                                       //Priority
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
