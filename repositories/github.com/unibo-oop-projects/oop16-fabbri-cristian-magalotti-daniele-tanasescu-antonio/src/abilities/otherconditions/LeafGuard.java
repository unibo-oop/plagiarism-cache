package abilities.otherconditions;

import battle_arena.BattleArena;
import battle_arena.weather.HarshSunlight;
import main.view.BattleMenuController;
import managers.BattleLogManager;
import pokemon.Pokemon;

//verrà chiamata direttamente dagli status condition!
public class LeafGuard extends OtherConditionsAbility{


    public LeafGuard() {
        super(  "Leaf Guard",                                                                                  //name, 
                "Prevents status conditions in sunny weather.");                                               //description
    }

    @Override
    public void activateAbility(Pokemon user, Pokemon target, BattleArena battleArena) {
        BattleMenuController.battleLogManager.setAbilityActivationMessage(user, this.getAbilityEffect(user, target, battleArena));
    }

    public boolean sunnyCondition(Pokemon user, Pokemon target, BattleArena battleArena){
        if(battleArena.weather != null){
            return battleArena.weather.equals(new HarshSunlight(5));
        }
        return false;
    }

    @Override
    public String getAbilityEffect(Pokemon user, Pokemon target, BattleArena battleArena) {
        return BattleLogManager.ACTIVATES;
    }

}
