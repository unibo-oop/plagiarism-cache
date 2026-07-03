package abilities.statisticsalterationondemand;

import battle_arena.BattleArena;
import main.view.BattleMenuController;
import managers.BattleLogManager;
import pokemon.Pokemon;

public class KeenEye extends StatisticAlterationOnDemandAbility{

    public KeenEye() {
        super(  "Keen Eye",                                                                                              //name, 
                "Keen eyes prevent other Pokemon from lowering this Pokemon's accuracy.");                                                  //description
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
