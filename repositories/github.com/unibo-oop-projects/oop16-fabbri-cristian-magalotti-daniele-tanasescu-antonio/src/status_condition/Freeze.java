package status_condition;

import java.util.Random;

import abilities.Ability;
import abilities.statusalterationcondition.MagmaArmor;
import pokemon.Pokemon;
import types.Ice;
import types.Type;

public class Freeze extends StatusCondition{

    private static final String FROZEN = "is frozen";                   //not Disney©'s movie, of course.
    private static final String FREEZEPREVENT = "is frozen solid!";
    private static final String FREEZEEND = "thaws out!";

    public Freeze() {
        super(  new Type[]{new Ice()},                                  //types immune to this   
                new Ability[]{new MagmaArmor()},                        //abilities that resists to this status condition       
                false,                                                  //has Dot?
                true,                                                   //may prevent attack?
                false,                                                  //alters Stats?
                "",                                                     //statusDot      (don't have) 
                FREEZEPREVENT,                                          //statusPrevent         
                FREEZEEND);                                             //statusEnd       
        this.setStatusAlready("frozen");
    }

    @Override
    public void checkNextTurnActive() {
        Random random = new Random();
        if(random.nextDouble() < 0.1){
            this.setNextTurnActive(false);
        }

    }

    @Override
    public void getDotDamage(Pokemon ill) {
        // TODO Auto-generated method stub

    }

    @Override
    public void getPreventAttack(Pokemon ill) {
        if(this.isThisTurnActive()){
            ill.canAttack = false;
            this.getPreventAttackMessage(ill);
        }
        else{
            this.exitingStatusAlteration(ill);
        }

    }

    @Override
    public void getStatAlteration(Pokemon ill) {
        // TODO Auto-generated method stub

    }

    @Override
    public void exitingStatusAlteration(Pokemon ill) {
        ill.canAttack = true;  
        this.getEndMessage(ill);
        ill.statusCondition = null;    

    }

    @Override
    public String getStatusString() {
        return FROZEN;
    }

}
