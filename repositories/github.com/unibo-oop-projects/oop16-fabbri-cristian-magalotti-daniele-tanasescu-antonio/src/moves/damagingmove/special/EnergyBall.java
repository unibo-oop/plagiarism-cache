package moves.damagingmove.special;

import java.util.Random;

import battle_arena.BattleArena;
import pokemon.Pokemon;
import types.Grass;

public class EnergyBall extends SpecialDamagingMove{

    public EnergyBall() {
        super(  "Energy Ball",                                                                                                                  //name
                "The user draws power from nature and fires it at the target. This may also lower the target's Sp. Def stat.",                  //description
                90,                                                                                                                             //base power
                new Grass(),                                                                                                                    //type
                1,                                                                                                                              //accuracy
                critRange1,                                                                                                                     //crit range 
                10,                                                                                                                             //PP
                0);                                                                                                                             //Priority
        this.sideEffect = true;
    }

    @Override
    public void sideEffect(Pokemon user, Pokemon target, BattleArena battleArena) {
        Random random = new Random();
        if(random.nextDouble() < 0.1){
            target.setAlterationSpD(-1, false);
        }
        
    }

}
