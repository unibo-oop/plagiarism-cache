package moves.damagingmove.physical;

import java.util.Random;

import battle_arena.BattleArena;
import pokemon.Pokemon;
import types.Steel;

public class SteelWing extends PhysicalDamagingMove{

    public SteelWing() {
        super(  "Steel Wing",                                                                                           //name
                "The target is hit with wings of steel. This may also raise the user's Defense stat.",                  //description                   
                70,                                                                                                     //base power
                new Steel(),                                                                                           //type
                0.9,                                                                                                    //accuracy
                critRange1,                                                                                             //crit range 
                25,                                                                                                     //PP
                0);                                                                                                     //Priority
        this.sideEffect = true;
    }

    @Override
    public void sideEffect(Pokemon user, Pokemon target, BattleArena battleArena) {
        Random random = new Random();
        if(random.nextDouble() < 0.1){
            target.setAlterationDef(-1, false);
        }
        
    }

}
