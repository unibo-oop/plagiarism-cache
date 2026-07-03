package abilities.firstturn.alwaysactive;

import battle_arena.BattleArena;
import main.view.BattleMenuController;
import pokemon.Pokemon;

public class ArenaTrap extends AlwaysActiveAbility{

    private static final String ARENATRAPPREVENTS = "prevents ";
    private static final String ARENATRAPFLEEING = "from fleeing.";

    public ArenaTrap() {
        super(  "Arena Trap",                                                                                             //name, 
                "Prevents opposing Pokemon from fleeing.");                                                               //description
    }

    @Override
    public void activateAbility(Pokemon user, Pokemon target, BattleArena battleArena) {
        target.canSwitch = false;     
        if(!this.messageDone){
            BattleMenuController.battleLogManager.setAbilityActivationMessage(user, this.getAbilityEffect(user, target, battleArena));
            this.messageDone = true;
        }
    }

    @Override
    public void exitingAbility(Pokemon user, Pokemon target, BattleArena battleArena){
        target.canSwitch = true;
        this.messageDone = false;
        super.exitingAbility(user, target, battleArena);
    }

    @Override
    public String getAbilityEffect(Pokemon user, Pokemon target, BattleArena battleArena) {
        return ARENATRAPPREVENTS + target.toString() + ARENATRAPFLEEING;
    }

}
