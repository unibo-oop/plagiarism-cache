package abilities.otherconditions;

import battle_arena.BattleArena;
import main.view.BattleMenuController;
import pokemon.Pokemon;

public class Limber extends OtherConditionsAbility{

    private static final String ANTIPARA = "prevents the paralysis.";

    public Limber() {
        super(  "Limber",                                                                                     //name, 
                "Its limber body protects the Pokemon from paralysis.");                                      //description
    }

    @Override
    public void activateAbility(Pokemon user, Pokemon target, BattleArena battleArena) {   
    }

    @Override
    public String getAbilityEffect(Pokemon user, Pokemon target, BattleArena battleArena) {
        return ANTIPARA;
    }
}
