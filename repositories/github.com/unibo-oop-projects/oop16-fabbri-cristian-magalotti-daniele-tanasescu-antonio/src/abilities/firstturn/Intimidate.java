package abilities.firstturn;

import battle_arena.BattleArena;
import main.view.BattleMenuController;
import pokemon.Pokemon;

public class Intimidate extends FirstTurnAbility{

    private static final String INTIMIDATES = "intimidates ";

    public Intimidate() {
        super(  "Intimidate",                                                                                             //name, 
                "The Pokemon intimidates opposing Pokémon upon entering battle, lowering their Attack stat.");           //description
    }

    @Override
    public void activateAbility(Pokemon user, Pokemon target, BattleArena battleArena) {
        BattleMenuController.battleLogManager.setAbilityActivationMessage(user, this.getAbilityEffect(user, target, battleArena));
        target.setAlterationAtk(-1, false);
    }

    @Override
    public String getAbilityEffect(Pokemon user, Pokemon target, BattleArena battleArena) {
        return INTIMIDATES + target.toString();
    }

}
