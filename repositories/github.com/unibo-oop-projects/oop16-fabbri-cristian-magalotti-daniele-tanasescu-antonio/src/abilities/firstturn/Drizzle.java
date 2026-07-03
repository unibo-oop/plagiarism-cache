package abilities.firstturn;

import battle_arena.BattleArena;
import battle_arena.weather.Rain;
import main.view.BattleMenuController;
import managers.BattleLogManager;
import pokemon.Pokemon;

public class Drizzle extends FirstTurnAbility{

    public Drizzle() {
        super(  "Drizzle",                                                                                                      //name, 
                "The Pokémon makes it rain when it enters a battle.");                                                          //description
    }

    @Override
    public void activateAbility(Pokemon user, Pokemon target, BattleArena battleArena) {
        Rain rain = new Rain(5);
        if(battleArena.weather == null || !battleArena.weather.equals(rain)){   
            BattleMenuController.battleLogManager.setAbilityActivationMessage(user, this.getAbilityEffect(user, target, battleArena));
            rain.setWeather(user, target, battleArena);
        }

    }

    @Override
    public String getAbilityEffect(Pokemon user, Pokemon target, BattleArena battleArena) {
        return BattleLogManager.ACTIVATES;
    }

}
