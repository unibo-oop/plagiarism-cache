package abilities.statusalterationcondition;

import battle_arena.BattleArena;
import managers.BattleLogManager;
import pokemon.Pokemon;
import status_condition.Freeze;

public class MagmaArmor extends StatusAlterationConditionAbility{

    public MagmaArmor() {
        super(  "Magma Armor",                                                                                       //name, 
                "The Pokemon is covered with hot magma, which prevents the Pokemon from becoming frozen.");          //description      
    }

    @Override
    public void checkStatusCondition(Pokemon user) {
        if(user.statusCondition != null){
            if(user.statusCondition.equals(new Freeze())){
                this.setIsActivable(true);
            }
            else{
                this.setIsActivable(false);
            }
        }
        else{
            this.setIsActivable(false);
        }
        
    }

    //this occurs only when a frozen Pokemon acquires this ability ('cause a pokemon with this ability would be immune to Freeze)
    @Override
    public void activateAbility(Pokemon user, Pokemon target, BattleArena battleArena) {
        user.statusCondition.exitingStatusAlteration(user);        
    }

    @Override
    public String getAbilityEffect(Pokemon user, Pokemon target, BattleArena battleArena) {
        return BattleLogManager.ACTIVATES;
    }

}
