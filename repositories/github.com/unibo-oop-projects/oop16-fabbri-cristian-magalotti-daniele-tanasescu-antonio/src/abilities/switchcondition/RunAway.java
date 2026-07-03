package abilities.switchcondition;

import battle_arena.BattleArena;
import main.view.BattleMenuController;
import pokemon.Pokemon;

public class RunAway extends SwitchConditionAbility{

    private static final String RUN = "makes the Pokemon flee safely!";
    public RunAway() {
        super(  "Run Away",                                                                        	 //name, 
                "Enables a sure getaway from wild Pokémon.");                                       //description
    }

    @Override
    public void activateAbility(Pokemon user, Pokemon target, BattleArena battleArena) {
        BattleMenuController.battleLogManager.setAbilityActivationMessage(user, this.getAbilityEffect(user, target, battleArena));
        if(!user.canSwitch){      
            user.canSwitch = true;
        }
        
    }

    @Override
    public String getAbilityEffect(Pokemon user, Pokemon target, BattleArena battleArena) {
        return RUN;
    }

}
