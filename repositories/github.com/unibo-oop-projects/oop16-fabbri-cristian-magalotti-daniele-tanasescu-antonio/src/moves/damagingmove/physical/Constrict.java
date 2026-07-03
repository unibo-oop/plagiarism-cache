package moves.damagingmove.physical;

import java.util.Random;

import abilities.statisticsalterationondemand.*;
import battle_arena.BattleArena;
import pokemon.Pokemon;
import types.Normal;

public class Constrict extends PhysicalDamagingMove{

    public Constrict() {
        super(  "Constrict",                                                                                            //name
                "The target is attacked with long, creeping tentacles, vines, or the like.\n" +                         //description
                "This may also lower the target's Speed stat.",                        
                35,                                                                                                     //base power
                new Normal(),                                                                                           //type
                1,                                                                                                      //accuracy
                critRange1,                                                                                             //crit range 
                10,                                                                                                     //PP
                0);                                                                                                     //Priority
        this.sideEffect = true;
    }

    @Override
    public void sideEffect(Pokemon user, Pokemon target, BattleArena battleArena) {
        Random random = new Random();
        if(random.nextDouble() < 0.1){
            if(target.getAbility().equals(new ClearBody())){
                ((ClearBody)target.getAbility()).activateAbility(user, target, battleArena);
            }
            else if(target.getAbility().equals(new WhiteSmoke())){
                ((WhiteSmoke)target.getAbility()).activateAbility(user, target, battleArena);
            }
            else{
                target.setAlterationSpe(-1, false);
            }
        }
        
    }

}
