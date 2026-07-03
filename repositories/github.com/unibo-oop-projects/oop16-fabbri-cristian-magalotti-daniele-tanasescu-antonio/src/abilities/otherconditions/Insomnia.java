package abilities.otherconditions;

import battle_arena.BattleArena;
import main.view.BattleMenuController;
import pokemon.Pokemon;

public class Insomnia extends OtherConditionsAbility{

    private static final String INSOMNIA = "prevents the Pokemon to fell asleep.";

    public Insomnia() {
        super(  "Insomnia",                                                                                     //name, 
                "The Pokemon is suffering from insomnia and cannot fall asleep.");                              //description
    }

    @Override
    public void activateAbility(Pokemon user, Pokemon target, BattleArena battleArena) {
        BattleMenuController.battleLogManager.setAbilityActivationMessage(user, this.getAbilityEffect(user, target, battleArena));        
    }

    @Override
    public String getAbilityEffect(Pokemon user, Pokemon target, BattleArena battleArena) {
        return INSOMNIA;
    }

}
