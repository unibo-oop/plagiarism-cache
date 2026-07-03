package abilities.firstturn;

import battle_arena.BattleArena;
import battle_arena.weather.HarshSunlight;
import main.view.BattleMenuController;
import managers.BattleLogManager;
import pokemon.Pokemon;

public class Drought extends FirstTurnAbility{

    public Drought() {
        super(  "Drought",                                                                                              //name, 
                "Turns the sunlight harsh when the Pokémon enters a battle.");                                          //description
    }

    @Override
    public void activateAbility(Pokemon user, Pokemon target, BattleArena battleArena) {
        HarshSunlight sun = new HarshSunlight(5);
        if(battleArena.weather == null || !battleArena.weather.equals(sun)){         
            BattleMenuController.battleLogManager.setAbilityActivationMessage(user, this.getAbilityEffect(user, target, battleArena));
            sun.setWeather(user, target, battleArena);  
        }

    }

    @Override
    public String getAbilityEffect(Pokemon user, Pokemon target, BattleArena battleArena) {
        return BattleLogManager.ACTIVATES;
    }

}
