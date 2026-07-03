package status_condition;

import java.util.Random;

import abilities.Ability;
import abilities.otherconditions.Limber;
import pokemon.Pokemon;
import types.Electric;
import types.Type;


public class Paralysis extends StatusCondition{
    
    private static final String PARALYSIS  = "is paralyzed";
    private static final String PARAPREVENT = "is paralyzed! It can't move!";
    private static final String PARAEND = "is freed from its paralysis!";

    public Paralysis() {
        super(  new Type[]{new Electric()},                             //type immune to this   
        	new Ability[]{new Limber()},		                    //abilities that resists to this status condition       
                false,                                                  //has Dot?
                true,                                                   //may prevent attack?
                true,                                                   //alters Stats?
                "",                                                     //statusDot      (don't have)   
                PARAPREVENT,                                            //statusPrevent      
                PARAEND);                                               //statusEnd   
        this.setStatusAlready("paralyzed");
    }

    @Override
    public void checkNextTurnActive() {
        this.setNextTurnActive(true);
        
    }

    @Override
    public void getDotDamage(Pokemon ill) {
        // TODO Auto-generated method stub
        
    }

    @Override
    public void getPreventAttack(Pokemon ill) {
        Random random = new Random();
        if(random.nextDouble() < 0.25){
            ill.canAttack = false;
            this.getPreventAttackMessage(ill);
        }
    }

    @Override
    public void getStatAlteration(Pokemon ill) {
        ill.setOtherModifierFactorSpe(ill.getOtherModifierFactorSpe()/2);
        
    }

    @Override
    public void exitingStatusAlteration(Pokemon ill) {
        ill.setOtherModifierFactorSpe(ill.getOtherModifierFactorSpe()*2);
        ill.canAttack = true;
        this.getEndMessage(ill);
        ill.statusCondition = null;
        
    }

    @Override
    public String getStatusString() {
        return PARALYSIS;
    }

}
