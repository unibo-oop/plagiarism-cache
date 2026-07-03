package abilities.statisticsalterationondemand;

import battle_arena.BattleArena;
import main.view.BattleMenuController;
import managers.BattleLogManager;
import pokemon.Pokemon;

public class ClearBody extends StatisticAlterationOnDemandAbility{

    public ClearBody() {
        super(  "Clear Body",                                                                                             //name, 
                "Prevents other Pokemon's moves or Abilities from lowering the Pokemon's stats.");                        //description
    }

    @Override
    public void activateAbility(Pokemon user, Pokemon target, BattleArena battleArena) {
        BattleMenuController.battleLogManager.setAbilityActivationMessage(user, this.getAbilityEffect(user, target, battleArena));
    }

    @Override
    public String getAbilityEffect(Pokemon user, Pokemon target, BattleArena battleArena) {
        return BattleLogManager.STATSCANTLOWER;
    }

}
