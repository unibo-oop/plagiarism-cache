package abilities.firstturn;

import battle_arena.BattleArena;
import battle_arena.weather.Rain;
import battle_arena.weather.Sandstorm;
import main.view.BattleMenuController;
import managers.BattleLogManager;
import pokemon.Pokemon;

public class SandStream extends FirstTurnAbility{

    public SandStream() {
        super(  "Sand Stream",                                                                                                      //name, 
                "The Pokémon summons a sandstorm when it enters a battle. ");                                                       //description
    }

    @Override
    public void activateAbility(Pokemon user, Pokemon target, BattleArena battleArena) {
        Sandstorm sandstorm = new Sandstorm(5);
        if(battleArena.weather == null || !battleArena.weather.equals(sandstorm)){   
            BattleMenuController.battleLogManager.setAbilityActivationMessage(user, this.getAbilityEffect(user, target, battleArena));
            sandstorm.setWeather(user, target, battleArena);
        }       
    }

    @Override
    public String getAbilityEffect(Pokemon user, Pokemon target, BattleArena battleArena) {
        return BattleLogManager.ACTIVATES;
    }

}
