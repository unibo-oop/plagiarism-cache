package abilities.statisticsalterationondemand;

import battle_arena.BattleArena;
import main.view.BattleMenuController;
import managers.BattleLogManager;
import pokemon.Pokemon;

public class Defiant extends StatisticAlterationOnDemandAbility{

    public Defiant() {
        super(  "Defiant",                                                                                            //name, 
                "Boosts the Pokémon's Attack stat sharply when its stats are lowered. ");                             //description
    }

    @Override
    public void activateAbility(Pokemon user, Pokemon target, BattleArena battleArena) {
        BattleMenuController.battleLogManager.setAbilityActivationMessage(user, this.getAbilityEffect(user, target, battleArena));
        user.setAlterationAtk(+2, true);        
    }

    @Override
    public String getAbilityEffect(Pokemon user, Pokemon target, BattleArena battleArena) {
        return BattleLogManager.ACTIVATES;
    }

}
