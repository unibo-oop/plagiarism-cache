package abilities.statisticsalterationondemand;

import battle_arena.BattleArena;
import main.view.BattleMenuController;
import managers.BattleLogManager;
import pokemon.Pokemon;

public class HyperCutter extends StatisticAlterationOnDemandAbility{

    public HyperCutter() {
        super(  "Hyper Cutter",                                                                                           //name, 
                "The Pok�mon's proud of its powerful pincers.\n" +
        		"They prevent other Pok�mon from lowering its Attack stat.");                                             //description
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
