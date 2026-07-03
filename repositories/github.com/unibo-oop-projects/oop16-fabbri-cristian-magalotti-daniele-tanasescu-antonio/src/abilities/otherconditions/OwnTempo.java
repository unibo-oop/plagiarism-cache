package abilities.otherconditions;

import battle_arena.BattleArena;
import main.view.BattleMenuController;
import pokemon.Pokemon;

public class OwnTempo extends OtherConditionsAbility{
    
    private static final String OWNTEMPO = "protected the Pokemon from being confused.";

    public OwnTempo() {
        super(  "Own Tempo",                                                                                          //name, 
                "This Pokémon has its own tempo, and that prevents it from becoming confused. ");                     //description
    }

    @Override
    public void activateAbility(Pokemon user, Pokemon target, BattleArena battleArena) {
        BattleMenuController.battleLogManager.setAbilityActivationMessage(user, this.getAbilityEffect(user, target, battleArena));
        
    }

    @Override
    public String getAbilityEffect(Pokemon user, Pokemon target, BattleArena battleArena) {
        return OWNTEMPO;
    }

}
