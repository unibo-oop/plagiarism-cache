package abilities.weathercondition;

import battle_arena.BattleArena;
import battle_arena.weather.Rain;
import main.view.BattleMenuController;
import managers.BattleLogManager;
import pokemon.Pokemon;

public class Hydration extends WeatherConditionAbility{

    public Hydration() {
        super(  "Hydration",                                                               //name 
                "Heals status conditions if it's raining.",                                //description
                new Rain(5));                                                   //weatherCondition
    }

    @Override
    public void activateAbility(Pokemon user, Pokemon target, BattleArena battleArena) {
        if(user.statusCondition != null){
            BattleMenuController.battleLogManager.setAbilityActivationMessage(user, this.getAbilityEffect(user, target, battleArena));
            user.statusCondition = null;
        }
        
    }

    @Override
    public String getAbilityEffect(Pokemon user, Pokemon target, BattleArena battleArena) {
        return BattleLogManager.ACTIVATES;
    }

}
