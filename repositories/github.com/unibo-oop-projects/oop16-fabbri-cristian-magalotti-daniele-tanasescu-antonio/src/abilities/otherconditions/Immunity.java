package abilities.otherconditions;

import battle_arena.BattleArena;
import main.view.BattleMenuController;
import pokemon.Pokemon;

public class Immunity extends OtherConditionsAbility{

    private static final String IMMUNITY = "prevents the poison.";

    //this will be checked by (Bad)Posion itself
    public Immunity() {
        super(  "Immunity",                                                                                           //name, 
                "The immune system of the Pokémon prevents it from getting poisoned.");                               //description
    }

    @Override
    public void activateAbility(Pokemon user, Pokemon target, BattleArena battleArena) {
        BattleMenuController.battleLogManager.setAbilityActivationMessage(user, this.getAbilityEffect(user, target, battleArena));
    }

    @Override
    public String getAbilityEffect(Pokemon user, Pokemon target, BattleArena battleArena) {
        return IMMUNITY;
    }

}
