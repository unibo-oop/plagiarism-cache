package abilities.endofturnconditionability;

import battle_arena.BattleArena;
import main.view.BattleMenuController;
import managers.BattleLogManager;
import pokemon.Pokemon;

public class SpeedBoost extends EndOfTurnConditionAbility{

    public SpeedBoost() {
        super(  "Speed Boost", 
                "Its Speed stat is boosted every turn.");
    }

    @Override
    public void activateAbility(Pokemon user, Pokemon target, BattleArena battleArena) {
        BattleMenuController.battleLogManager.setAbilityActivationMessage(user, this.getAbilityEffect(user, target, battleArena));
        user.setAlterationSpe(+1, true);
        
    }

    @Override
    public String getAbilityEffect(Pokemon user, Pokemon target, BattleArena battleArena) {
        return BattleLogManager.ACTIVATES;
    }

}
