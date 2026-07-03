package abilities.otherconditions;

import battle_arena.BattleArena;
import main.view.BattleMenuController;
import pokemon.Pokemon;

public class Overcoat extends OtherConditionsAbility{

    private static final String COAT = "protected the Pokemon from being hit.";

    public Overcoat() {
        super(  "Overcoat",                                                                                           //name, 
                "Protects the Pokémon from things like sand, hail, and powder.");                                     //description
    }

    @Override
    public void activateAbility(Pokemon user, Pokemon target, BattleArena battleArena) {
        BattleMenuController.battleLogManager.setAbilityActivationMessage(user, this.getAbilityEffect(user, target, battleArena));
    }

    @Override
    public String getAbilityEffect(Pokemon user, Pokemon target, BattleArena battleArena) {
        return COAT;
    }
    
}
