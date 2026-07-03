package abilities.statusalterationcondition;

import battle_arena.BattleArena;
import pokemon.Pokemon;
import status_condition.Sleep;

public class EarlyBird extends StatusAlterationConditionAbility{

    public EarlyBird() {
        super(  "Early Bird",                                                                                              //name, 
                "The Pokemon awakens twice as fast as other Pokemon from sleep.");                                         //description
    }

    @Override
    public void activateAbility(Pokemon user, Pokemon target, BattleArena battleArena) {
        //no message needed
        ((Sleep)user.statusCondition).setTurnCount(user.statusCondition.getTurnCounter()/2);                             
    }

    @Override
    public void checkStatusCondition(Pokemon user) {
        if(user.statusCondition != null && user.statusCondition.equals(new Sleep())){
            this.setIsActivable(true);
        }
        else{
            this.setIsActivable(false);
        }
        
    }

    @Override
    public String getAbilityEffect(Pokemon user, Pokemon target, BattleArena battleArena) {
        // TODO Auto-generated method stub
        return null;
    }

}
