package moves.damagingmove.special;

import java.util.Random;

import abilities.statisticsalterationondemand.ClearBody;
import abilities.statisticsalterationondemand.WhiteSmoke;
import battle_arena.BattleArena;
import pokemon.Pokemon;
import types.Water;

public class BubbleBeam extends SpecialDamagingMove{

    public BubbleBeam() {
        super(  "Bubble Beam",                                                                                                //name
                "A spray of bubbles is forcefully ejected at the target. This may also lower its Speed stat.",                //description
                65,                                                                                                           //base power
                new Water(),                                                                                                  //type
                1,                                                                                                            //accuracy
                critRange1,                                                                                                   //crit range 
                20,                                                                                                           //PP
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