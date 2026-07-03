package abilities.statisticsalterationondemand;

import battle_arena.BattleArena;
import main.view.BattleMenuController;
import managers.BattleLogManager;
import pokemon.Pokemon;

public class BigPecks extends StatisticAlterationOnDemandAbility{

    public BigPecks() {
        super(  "Big Pecks",                                                                                              //name, 
                "Protects the Pokémon from Defense-lowering effects.");                                                  //description
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
