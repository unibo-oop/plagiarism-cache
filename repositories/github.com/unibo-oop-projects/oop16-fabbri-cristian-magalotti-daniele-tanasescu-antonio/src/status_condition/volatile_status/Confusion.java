package status_condition.volatile_status;

import java.util.Random;

import abilities.otherconditions.OwnTempo;
import main.MainApp;
import main.view.BattleMenuController;
import pokemon.Pokemon;

public class Confusion extends VolatileStatusCondition{
    
    private static final int CONFUSIONMIN = 1;
    private static final int CONFUSIONMAX = 4;
    private static final int CONFUSIONDOTBASEPOWER = 4;
    private static final String CONFUSED = "is confused";
    private static final String CONFUSIONDOT = "hurt itself in its confusion!";
    private static final String CONFUSIONEND = "is no more confused!";
    

    public Confusion() {
        super(  null,                                                                           //immunityToThis 
                null,                                                                           //avoidThis
                false,                                                                          //hasDot                      
                true,                                                                           //mayPreventAttack
                false,                                                                          //alterStat
                VolatileStatusCondition.getVolatileTurns(CONFUSIONMIN,CONFUSIONMAX),            //turnCount
                2,                                                                              //executionPriority
                CONFUSIONDOT,                                                                   //statusDot
                CONFUSED,                                                                       //statusPrevent
                CONFUSIONEND);                                                                  //statusEnd        
        this.setStatusAlready("confused");
                
    }

    @Override
    public void getVolatilePreventEffect(Pokemon ill) {
        if(this.isThisTurnActive()){
            BattleMenuController.battleLogManager.setStatusAlterationMessage(ill, CONFUSED);
            Random random = new Random();
            if(random.nextDouble() < 0.33){
                ill.canAttack = false;
                this.getVolatileDotEffect(ill);
            }
            this.decrementTurnCount();
        }
        else{
            this.deleteOneVolatile(ill);
        }      
    }

    @Override
    public void getVolatileDotEffect(Pokemon ill) {
        double damage = ((double)((2 * ill.getLevel() +10) * ill.getAtk() * CONFUSIONDOTBASEPOWER))/(50 * ill.getDef())+2;
        this.getStatusDotMessage(ill);
        ill.takeDamage(damage, false);
        
    }

    @Override
    public void checkNextTurnActive() {
        if(this.isVolatileOver()){
            this.setNextTurnActive(false);
        }
        else{
            this.setNextTurnActive(true);
        }
        
    }
    
    @Override
    public void addVolatile(Pokemon target, VolatileStatusCondition[] container){
        if(target.getAbility().equals(new OwnTempo())){
            ((OwnTempo)target.getAbility()).activateAbility(target, target, MainApp.getBattleArena());
        }
        else{
            super.addVolatile(target, container);
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
        
    }

    @Override
    public String getStatusString() {
        return CONFUSED;
    }

}
