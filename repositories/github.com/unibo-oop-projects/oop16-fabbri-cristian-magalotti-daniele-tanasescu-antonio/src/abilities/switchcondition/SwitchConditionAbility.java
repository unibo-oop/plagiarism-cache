package abilities.switchcondition;

import abilities.Ability;
import battle_arena.BattleArena;
import moves.status.Switch;
import pokemon.Pokemon;

public abstract class SwitchConditionAbility extends Ability {

    public SwitchConditionAbility(String name, String description) {
        super(name, description);
        this.abilityStartCondition();
    }

    @Override
    public void setNextCondition(Pokemon user, Pokemon target, BattleArena battleArena) {
        if(user.equals(battleArena.activePlayer)){
            //it shouldn't be just arrived!
            if(battleArena.playerMove != null && battleArena.playerMove.equals(new Switch()) && user.isAttacking){
                this.setIsActivable(true);
            }
         }
         else if(battleArena.enemyMove != null && battleArena.enemyMove.equals(new Switch()) && user.isAttacking){
             this.setIsActivable(true);
         }
         else{
             this.setIsActivable(false);
        }       
    }

    @Override
    public void abilityStartCondition() {
        this.setIsActivable(false);
    }

    @Override
    public void checkForActivation(Pokemon user, Pokemon target, BattleArena battleArena) {
        this.setNextCondition(user, target, battleArena);        
        if(this.getIsActivable()){
            this.activateAbility(user, target, battleArena);
        }
    }

}