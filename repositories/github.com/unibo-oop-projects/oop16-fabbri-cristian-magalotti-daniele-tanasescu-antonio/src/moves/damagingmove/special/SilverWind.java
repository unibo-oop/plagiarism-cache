package moves.damagingmove.special;

import java.util.Random;

import battle_arena.BattleArena;
import pokemon.Pokemon;
import types.Bug;

public class SilverWind extends SpecialDamagingMove{

    public SilverWind() {
        super(  "Silver Wind",                                                                                           //name
                "The target is attacked with powdery scales blown by the wind.\n"+                                       //description
                "This may also raise all the user's stats.",                                   
                65,                                                                                                      //base power
                new Bug(),                                                                                            //type
                1,                                                                                                       //accuracy
                critRange1,                                                                                              //crit range 
                25,                                                                                                      //PP
                0);                                                                                                      //Priority
        this.sideEffect = true;
    }

    @Override
    public void sideEffect(Pokemon user, Pokemon target, BattleArena battleArena) {
        Random random = new Random();
        if(random.nextDouble() < 0.1){
            user.setAlterationAtk(+1, true);
            user.setAlterationDef(+1, true);
            user.setAlterationSpe(+1, true);
            user.setAlterationSpA(+1, true);
            user.setAlterationSpD(+1, true);
        }
        
    }

}
