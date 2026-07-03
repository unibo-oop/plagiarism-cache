package moves.damagingmove.special;

import java.util.Random;

import battle_arena.BattleArena;
import pokemon.Pokemon;
import types.Water;

public class MuddyWater extends SpecialDamagingMove{

    public MuddyWater() {
        super(  "Muddy Water",                                                                                                //name
                "The user attacks by shooting muddy water at the opposing Pokémon.\n" +                                       //description
                "This may also lower their accuracy.",    
                90,                                                                                                           //base power
                new Water(),                                                                                                  //type
                0.85,                                                                                                         //accuracy
                critRange1,                                                                                                   //crit range 
                10,                                                                                                           //PP
                0);                                                                                                           //Priority
        this.sideEffect = true;
    }

    @Override
    public void sideEffect(Pokemon user, Pokemon target, BattleArena battleArena) {
        Random random = new Random();
        if(random.nextDouble() < 0.3){
            target.setAlterationAccuracy(-1, false);
        }
        
    }

}
