package moves.damagingmove.physical;

import battle_arena.BattleArena;
import pokemon.Pokemon;
import status_condition.Paralysis;
import types.Fight;

public class SmellingSalts extends PhysicalDamagingMove{

    public SmellingSalts() {
        super(  "Smelling Salts",                                                                               //name
                "This attack's power is doubled when used on a target with paralysis.\n"+                       //description
                "This also cures the target's paralysis, however.",                    
                70,                                                                                             //base power
                new Fight(),                                                                                    //type
                1,                                                                                              //accuracy
                critRange1,                                                                                     //crit range 
                10,                                                                                             //PP
                0);                                                                                             //Priority
    }
    
    @Override
    public void getDamage(Pokemon user, Pokemon target, BattleArena battleArena){
        boolean boost = false;
        if(this.checkTargetParalysis(target)){
            this.setBasePower(2d);
            boost = true;
        }
        super.getDamage(user, target, battleArena);
        if(boost){
            this.setBasePower(0.5);
        }
    }

    @Override
    public void sideEffect(Pokemon user, Pokemon target, BattleArena battleArena) {
        if(this.checkTargetParalysis(target)){
            target.statusCondition.exitingStatusAlteration(target);
            //message
        }
    }
    
    private boolean checkTargetParalysis(Pokemon target){
        if(target.statusCondition != null){
            if(target.statusCondition.equals(new Paralysis())){
                return true;
            }
        }
        return false;
    }

}
