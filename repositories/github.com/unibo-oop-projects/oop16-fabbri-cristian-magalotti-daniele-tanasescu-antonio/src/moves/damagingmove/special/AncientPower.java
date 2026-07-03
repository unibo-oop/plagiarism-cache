package moves.damagingmove.special;

import java.util.Random;

import battle_arena.BattleArena;
import pokemon.Pokemon;
import types.Rock;

public class AncientPower extends SpecialDamagingMove{

    public AncientPower() {
        super(  "Ancient Power",                                                                                                                //name
                "The user attacks with a prehistoric power. It may also raise all the user's stats at once.",                                   //description
                60,                                                                                                                             //base power
                new Rock(),                                                                                                                     //type
                1,                                                                                                                              //accuracy
                critRange1,                                                                                                                     //crit range 
                5,                                                                                                                              //PP
                0);                                                                                                                             //Priority
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
