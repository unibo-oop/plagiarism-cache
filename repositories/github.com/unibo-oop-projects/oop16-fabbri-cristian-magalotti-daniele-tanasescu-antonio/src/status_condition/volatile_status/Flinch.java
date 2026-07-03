package status_condition.volatile_status;

import abilities.Ability;
import abilities.otherconditions.InnerFocus;
import pokemon.Pokemon;

public class Flinch extends VolatileStatusCondition{
    
    private static final String FLINCHES = "flinches";

    public Flinch() {
        super(  null,                                                                           //immunityToThis 
        	new Ability[]{new InnerFocus()},                                                //avoidThis
                false,                                                                          //hasDot                      
                true,                                                                           //mayPreventAttack
                false,                                                                          //alterStat
                1,                                                                              //turnCount (...)
                6,                                                                              //executionPriority (this is max)
                "",                                                                             //statusDot      (don't have)  
                "",                                                                             //statusPrevent  (is FLINCHES itself)
                "");                                                                            //statusEnd      (don't have)
    }

    @Override
    public void getVolatilePreventEffect(Pokemon ill) {
        ill.canAttack = false;
        //messaggio flinch
        this.deleteOneVolatile(ill);   //dopo aver flinchato, perde subito lo status d iflinch per non avere sovrapposizioni il turno seguente
        
    }

    @Override
    public void getVolatileDotEffect(Pokemon ill) {
        // TODO Auto-generated method stub
        
    }

    @Override
    public void checkNextTurnActive() {
        // TODO Auto-generated method stub
        
    }

    @Override
    public void getStatAlteration(Pokemon ill) {
        // TODO Auto-generated method stub
        
    }

    @Override
    public void exitingStatusAlteration(Pokemon ill) {
        //messaggi e fine turno di questo Pokemon
        
    }

    @Override
    public String getStatusString() {
        return FLINCHES;
    }
}
