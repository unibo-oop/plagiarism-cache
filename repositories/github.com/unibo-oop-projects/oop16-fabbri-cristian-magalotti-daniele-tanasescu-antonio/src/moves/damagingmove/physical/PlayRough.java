package moves.damagingmove.physical;

import java.util.Random;

import battle_arena.BattleArena;
import pokemon.Pokemon;
import types.Fairy;

public class PlayRough extends PhysicalDamagingMove{

    public PlayRough() {
        super(  "Play Rough",                                                                                   //name
                "The user plays rough with the target and attacks it.\n"
                + "This may also lower the target's Attack stat.",												//description                   
                90,                                                                                             //base power
                new Fairy(),                                                                                    //type
                0.9,                                                                                            //accuracy
                critRange1,                                                                                     //crit range 
                10,                                                                                             //PP
                0);                                                                                             //Priority
        this.sideEffect = true;
    }

    @Override
    public void sideEffect(Pokemon user, Pokemon target, BattleArena battleArena) {
    	Random random = new Random();
    	if(random.nextDouble() < 0.1){
    		target.setAlterationAtk(-1, false);
    	}
    }

}
