package moves.damagingmove.physical;

import java.util.Random;

import battle_arena.BattleArena;
import pokemon.Pokemon;
import types.Steel;

public class MetalClaw extends PhysicalDamagingMove{

    public MetalClaw() {
        super(  "Metal Claw",                                                                                           //name
                "The target is raked with steel claws. This may also raise the user's Attack stat.",                    //description                                 
                75,                                                                                                     //base power
                new Steel(),                                                                                             //type
                0.90,                                                                                                   //accuracy
                critRange1,                                                                                             //crit range 
                10,                                                                                                     //PP
                0);                                                                                                     //Priority
        this.sideEffect = true;
    }

    @Override
    public void sideEffect(Pokemon user, Pokemon target, BattleArena battleArena) {
        Random random = new Random();
        if(random.nextDouble() < 0.1){
            user.setAlterationAtk(+1, true);
        }
        
    }

}
