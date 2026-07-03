package moves.damagingmove.physical;

import battle_arena.BattleArena;
import pokemon.Pokemon;
import status_condition.*;
import types.Normal;
import types.Poison;

public class Facade extends PhysicalDamagingMove{

    public Facade() {
        super(  "Facade",                                                                                               //name
                "This attack move doubles its power if the user is poisoned, burned, or paralyzed",                     //description                    
                70,                                                                                                     //base power
                new Normal(),                                                                                           //type
                1,                                                                                                      //accuracy
                critRange1,                                                                                             //crit range 
                20,                                                                                                     //PP
                0);                                                                                                     //Priority
    }

    @Override
    public void getDamage(Pokemon user, Pokemon target, BattleArena battleArena){
        boolean boost = false;
        if(user.statusCondition != null){
            if(user.statusCondition.equals(new Paralysis()) || user.statusCondition.equals(new Burn()) || 
                    user.statusCondition.equals(new Poison())    || user.statusCondition.equals(new BadPoison())){
                this.setBasePower(2d);
                boost = true;
            }
        }
        super.getDamage(user, target, battleArena);
        if(boost){
            this.setBasePower(0.5);
        }
    }

    @Override
    public void sideEffect(Pokemon user, Pokemon target, BattleArena battleArena) {
        // TODO Auto-generated method stub

    }

}
