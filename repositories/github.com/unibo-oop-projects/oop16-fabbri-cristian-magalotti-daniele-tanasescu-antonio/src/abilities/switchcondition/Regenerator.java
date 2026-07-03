package abilities.switchcondition;

import battle_arena.BattleArena;
import main.view.BattleMenuController;
import managers.BattleLogManager;
import pokemon.Pokemon;

public class Regenerator extends SwitchConditionAbility{

    public Regenerator() {
        super(  "Regenerator",                                                                                          //name, 
                "Restores a little HP when withdrawn from battle. ");                                                   //description
    }

    @Override
    public void activateAbility(Pokemon user, Pokemon target, BattleArena battleArena) {
        if(!user.isFainted() && user.getHp() < user.getMaxHp()){
            user.takeDamage(-user.getMaxHp()/3, false);                                                                 //heals 1/3
        }
        BattleMenuController.battleLogManager.setAbilityActivationMessage(user, this.getAbilityEffect(user, target, battleArena));
        
    }

    @Override
    public String getAbilityEffect(Pokemon user, Pokemon target, BattleArena battleArena) {
        return BattleLogManager.ACTIVATES;
    }

}
