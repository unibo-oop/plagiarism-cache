package status_condition;

import java.util.Random;

import abilities.Ability;
import abilities.otherconditions.Insomnia;
import pokemon.Pokemon;

public class Sleep extends StatusCondition{

    private static final int MINTURN = 1;
    private static final int MAXTURN = 3;
    private static final String SLEEP = "starts to sleep";
    private static final String SLEEPPREVENT = "is fast asleep.";
    private static final String SLEEPEND = "woke up!";
    private int turnCount;

    public Sleep() {
        super(  null,                                                   //types immune to this   
                new Ability[]{new Insomnia()},                          //abilities that resists to this status condition       
                false,                                                  //has Dot?
                true,                                                   //may prevent attack?
                false,                                                  //alters Stats?
                "",                                                     //statusDot      (don't have)      
                SLEEPPREVENT,                                           //statusPrevent         (doesn't have)
                SLEEPEND);                                              //statusEnd        
        this.setStatusAlready("asleep");
        this.calculateSleepTurns();
    }

    @Override
    public void checkNextTurnActive() {
        if(this.turnCount > 0){
            this.setNextTurnActive(true);
        }
        else{
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
            this.turnCount--;
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
        ill.statusCondition = null;
        ill.canAttack = true;
        this.getEndMessage(ill);

    }

    public void setTurnCount(int newValue){
        this.turnCount = newValue;
    }

    public void calculateSleepTurns(){
        Random random = new Random();
        this.turnCount = random.nextInt(MAXTURN) + MINTURN;             //1-3
    }

    @Override
    public String getStatusString() {
        return SLEEP;
    }

}

