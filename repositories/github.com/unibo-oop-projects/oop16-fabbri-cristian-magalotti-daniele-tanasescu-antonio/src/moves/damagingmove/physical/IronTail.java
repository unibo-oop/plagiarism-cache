package moves.damagingmove.physical;

import java.util.Random;

import battle_arena.BattleArena;
import pokemon.Pokemon;
import types.Steel;

public class IronTail extends PhysicalDamagingMove{

    public IronTail() {
        super(  "Iron Tail",                                                                                            //name
                "The target is slammed with a steel-hard tail. This may also lower the target's Defense stat.",         //description                   
                100,                                                                                                    //base power
                new Steel(),                                                                                            //type
                0.75,                                                                                                   //accuracy
                critRange1,                                                                                             //crit range 
                15,                                                                                                     //PP
                0);                                                                                                     //Priority
        this.sideEffect = true;
    }

    @Override
    public void sideEffect(Pokemon user, Pokemon target, BattleArena battleArena) {
        Random random = new Random();
        if(random.nextDouble() < 0.3){
            target.setAlterationDef(-1, false);
        }
        
    }

}
