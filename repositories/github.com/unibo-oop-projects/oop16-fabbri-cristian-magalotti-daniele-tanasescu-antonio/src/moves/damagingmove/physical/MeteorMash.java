package moves.damagingmove.physical;

import java.util.Random;

import battle_arena.BattleArena;
import pokemon.Pokemon;
import types.Steel;

public class MeteorMash extends PhysicalDamagingMove{

    public MeteorMash() {
        super(  "Meteor Mash",                                                                                                  //name
                "The target is hit with a hard punch fired like a meteor. This may also raise the user's Attack stat.",         //description                   
                90,                                                                                                             //base power
                new Steel(),                                                                                                    //type
                0.9,                                                                                                            //accuracy
                critRange1,                                                                                                     //crit range 
                10,                                                                                                             //PP
                0);                                                                                                             //Priority
        this.sideEffect = true;
    }

    @Override
    public void sideEffect(Pokemon user, Pokemon target, BattleArena battleArena) {
        Random random = new Random();
        if(random.nextDouble() < 0.2){
            user.setAlterationAtk(+1, true);
        }
        
    }

}
