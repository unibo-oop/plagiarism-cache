package moves.status;

import battle_arena.BattleArena;
import main.view.BattleMenuController;
import pokemon.Pokemon;
import types.Ground;

public class Sandstorm extends StatusMove{

    public Sandstorm() {
        super(  "Sandstorm",                                                                                                      //name
                "A five-turn sandstorm is summoned to hurt all combatants except the Rock, Ground, and Steel types.\n"+           //description
                "It raises the Sp. Def stat of Rock types.",                                           
                new Ground(),                                                                                                     //type
                999,                                                                                                              //accuracy
                10,                                                                                                               //PP                                                                                                                     
                0);                                                                                                               //priority    
        this.setSelfEffect(true);
    }

    @Override
    public void sideEffect(Pokemon user, Pokemon target, BattleArena battleArena) {
        battle_arena.weather.Sandstorm sandstorm = new battle_arena.weather.Sandstorm(5);
        if(battleArena.weather != null && battleArena.weather.equals(sandstorm)){
            BattleMenuController.battleLogManager.setMoveFailedMassage();
        }
        else{
            sandstorm.setWeather(user, target, battleArena);
        }

    }

}
