package abilities.otherconditions;

import abilities.movecondition.MoveConditionAbility;
import battle_arena.BattleArena;
import main.view.BattleMenuController;
import pokemon.Pokemon;

public class Damp extends MoveConditionAbility{

    private static final String DAMPPREVENT = "prevents the explosion!";

    public Damp() {
        super(  "Damp",                                                                                           //name, 
                "Prevents the use of explosive moves such as Self-Destruct by dampening its surroundings.");      //description
    }

    @Override
    public boolean checkMoveCondition(Pokemon user, Pokemon target, BattleArena battleArena) {
        return false;
    }

    @Override
    public void activateAbility(Pokemon user, Pokemon target, BattleArena battleArena) {
        BattleMenuController.battleLogManager.setAbilityActivationMessage(user, this.getAbilityEffect(user, target, battleArena));
        
    }

    @Override
    public String getAbilityEffect(Pokemon user, Pokemon target, BattleArena battleArena) {
        return DAMPPREVENT;
    }

}
