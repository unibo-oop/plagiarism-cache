package abilities.statisticsalterationondemand;

import battle_arena.BattleArena;
import main.view.BattleMenuController;
import managers.BattleLogManager;
import pokemon.Pokemon;

public class WhiteSmoke extends StatisticAlterationOnDemandAbility{

    public WhiteSmoke() {
        super(  "White Smoke",                                                                                             //name, 
                "The Pokemon is protected by its white smoke, which prevents other Pokemon from lowering its stats.");     //description
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
