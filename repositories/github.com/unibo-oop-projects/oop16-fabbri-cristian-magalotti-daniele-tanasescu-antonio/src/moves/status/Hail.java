package moves.status;

import battle_arena.BattleArena;
import main.view.BattleMenuController;
import pokemon.Pokemon;
import types.Ice;

public class Hail extends StatusMove{

    public Hail() {
        super(  "Hail",                                                                                                   //name
                "The user summons a hailstorm lasting five turns. It damages all Pokémon except the Ice type.",           //description
                new Ice(),                                                                                                //type
                999,                                                                                                      //accuracy
                10,                                                                                                       //PP                                                                                                                     
                0);                                                                                                       //priority     
        this.setSelfEffect(true);                                       //...
    }

    @Override
    public void sideEffect(Pokemon user, Pokemon target, BattleArena battleArena) {
        battle_arena.weather.Hail hail = new battle_arena.weather.Hail(5);
        if(battleArena.weather != null && battleArena.weather.equals(hail)){
            BattleMenuController.battleLogManager.setMoveFailedMassage();
        }
        else{
            hail.setWeather(user, target, battleArena);
        }

    }

}
