package status_condition.volatile_status;

import java.util.Random;

import abilities.statusalterationcondition.Oblivious;
import main.MainApp;
import pokemon.Pokemon;

public class Infatuation extends VolatileStatusCondition{

    private static final String INFATUATED = "is in love";
    private static final String LOVEPREVENT = "is so in love that can't attack!";
    private static final String LOVEEND = "throws its love far far away!";

    public Infatuation() {
        super(  null,                                                                           //immunityToThis 
                null, //magicbounce ...                                                         avoidThis
                false,                                                                          //hasDot                      
                true,                                                                           //mayPreventAttack
                false,                                                                          //alterStat
                9999,                                                                           //turnCount (...)
                2,                                                                              //executionPriority
                "",                                                                             //statusDot      (don't have)
                LOVEPREVENT,                                                                    //statusPrevent
                LOVEEND);                                                                       //statusEnd      
        this.setStatusAlready("in love");

    }

    @Override
    public void getVolatilePreventEffect(Pokemon ill) {
        Random random = new Random();
        if(random.nextDouble() < 0.5){
            ill.canAttack = false;
            this.getPreventAttackMessage(ill);
        }
    }
    
    @Override
    public void addVolatile(Pokemon target, VolatileStatusCondition[] container){
        if(target.getAbility().equals(new Oblivious())){
            target.getAbility().activateAbility(target, target, MainApp.getBattleArena());
        }
        else{
            super.addVolatile(target, container);
        }
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
        ill.canAttack = true;
        this.getEndMessage(ill);

    }

    @Override
    public String getStatusString() {
        return INFATUATED;
    }

}
