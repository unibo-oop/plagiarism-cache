package moves.damagingmove.special;

import java.util.Random;

import abilities.statisticsalterationondemand.ClearBody;
import abilities.statisticsalterationondemand.WhiteSmoke;
import battle_arena.BattleArena;
import pokemon.Pokemon;
import types.Water;

public class Bubble extends SpecialDamagingMove{

    public Bubble() {
        super(  "Bubble",                                                                                                     //name
                "A spray of countless bubbles is jetted at the opposing Pokémon.\n" +                                         //description
                "This may also lower their Speed stat.",    
                40,                                                                                                           //base power
                new Water(),                                                                                                  //type
                1,                                                                                                            //accuracy
                critRange1,                                                                                                   //crit range 
                30,                                                                                                           //PP
                0);                                                                                                           //Priority
        this.sideEffect = true;
    }

    @Override
    public void sideEffect(Pokemon user, Pokemon target, BattleArena battleArena) {
        Random random = new Random();
        if(random.nextDouble() < 0.1){
            target.setAlterationSpe(-1, false);                        
        }
    }
}