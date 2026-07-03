package status_condition.volatile_status;

import java.util.Random;

import abilities.Ability;
import main.MainApp;
import main.view.BattleMenuController;
import pokemon.Pokemon;
import status_condition.StatusCondition;
import types.Type;
import utilities.Utilities;

public abstract class VolatileStatusCondition extends StatusCondition{

    private int turnCount;

    private final int executionPriority;

    public VolatileStatusCondition(Type[] immunityToThis, Ability[] avoidThis, boolean hasDot, boolean mayPreventAttack, boolean alterStat,
            int turnCount, int executionPriority, String statusDot, String statusPrevent, String statusEnd) {
        super(immunityToThis, avoidThis, hasDot, mayPreventAttack, alterStat, statusDot, statusPrevent, statusEnd);
        this.turnCount = turnCount;
        this.executionPriority = executionPriority;
        this.setNextTurnActive(true);
    }

    @Override
    public void operationsInSwitch(Pokemon ill){
        deleteAllVolatiles(ill.volatileStatusConditions);    
    }

    //it will be safe because it will be called only when the volatile has to be deleted, so it will certainly and always be in the array
    public int findVolatilePosition(VolatileStatusCondition[] container){
        int position = 0;
        int counter = 0;
        for(VolatileStatusCondition vsc : container){
           if(vsc != null){
               if(vsc.equals(this)){
                   position = counter;
               }
           }
            counter++;
        }
        return position; 
    }

    public boolean isContained(VolatileStatusCondition[] container){
        for(VolatileStatusCondition vsc : container){
            if(vsc != null){
                if(this.equals(vsc)){
                    return true;
                }
            }
        }
        return false;
    }

    public static void deleteAllVolatiles(VolatileStatusCondition[] container){
        for(int i = 0; i < container.length; i++){
            if(container[i] != null){
                container[i] = null;
            }
        }
    }

    public void addVolatile(Pokemon target, VolatileStatusCondition[] container){
        if(!this.isContained(container)){
            Utilities.allNullsAtTheEnd(container);
            int index = Utilities.numOfElemsNotNull(container);
            container[index] = this;
            if(! (this.equals(new Flinch()) && target.equals(MainApp.getBattleArena().speedTie[0]))){       //it can't flinch if it attacks first!
                BattleMenuController.battleLogManager.setStatusAlterationMessage(target, this.getStatusString());            
            }
            orderVolatileContainer(container);
        }
        else{
            BattleMenuController.battleLogManager.setAlreadyHasAStatus(target);
        }
    }

    public void deleteOneVolatile(Pokemon ill){
        for(int i = 0; i < ill.volatileStatusConditions.length; i++){
            if(this != null && ill.volatileStatusConditions[i] != null && ill.volatileStatusConditions[i].equals(this)){
                ill.volatileStatusConditions[i] = null;
                this.exitingStatusAlteration(ill);
            }
        }
        orderVolatileContainer(ill.volatileStatusConditions);
    }


    public static void orderVolatileContainer(VolatileStatusCondition[] container){
        Utilities.allNullsAtTheEnd(container);
        VolatileStatusCondition temp;
        for (int i = 0; i < Utilities.numOfElemsNotNull(container) - 1; i++) {
            for (int j = 1; j < Utilities.numOfElemsNotNull(container) - i; j++) {
                if(container[j] != null){
                    if (container[j - 1].executionPriority < container[j].executionPriority) {
                        temp = container[j - 1];
                        container[j - 1] = container[j];
                        container[j] = temp;
                    }                   
                }
            }
        }
    }

    @Override
    public void getPreventAttack(Pokemon ill){
        for(VolatileStatusCondition vsc : ill.volatileStatusConditions){
            if(vsc != null && vsc.isMayPreventAttack()){
                vsc.getVolatilePreventEffect(ill);
                if(vsc.equals(new Flinch())){                                   //nothing more
                    break;
                }
            }
        }
    }

    @Override
    public void getDotDamage(Pokemon ill){
        for(VolatileStatusCondition vsc : ill.volatileStatusConditions){
            if(vsc != null && vsc.hasDot() && !ill.isFainted()){
                vsc.getVolatileDotEffect(ill);
            }
        }
    }

    public abstract void getVolatilePreventEffect(Pokemon ill);
    public abstract void getVolatileDotEffect(Pokemon ill);

    public static int getVolatileTurns(int min, int max){
        Random random = new Random();
        return random.nextInt(max - min) + min;
    }

    public boolean isVolatileOver() {
        return turnCount <= 0;
    }

    public void decrementTurnCount() {
        this.turnCount--;
    }
}
