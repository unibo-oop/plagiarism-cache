package abilities.firstturn.alwaysactive;

import battle_arena.BattleArena;
import main.view.BattleMenuController;
import managers.BattleLogManager;
import pokemon.Pokemon;

public class MarvelScale extends AlwaysActiveAbility{

    private boolean effectDone;
    
    public MarvelScale() {
        super(  "Marvel Scale",                                                                                           //name, 
                "The Pokemon's marvelous scales boost the Defense stat if it has a status condition.");                   //description
        this.effectDone = false;
    }

    @Override
    public void activateAbility(Pokemon user, Pokemon target, BattleArena battleArena) {
        if(!this.effectDone){
            if(user.statusCondition != null){
                user.setOtherModifierFactorDef(user.getOtherModifierFactorDef()*1.5);                                     //+ 50%
                BattleMenuController.battleLogManager.setAbilityActivationMessage(user, this.getAbilityEffect(user, target, battleArena));
                this.effectDone = true;
            }
        }
        
    }
    
    @Override
    public void exitingAbility(Pokemon user, Pokemon target, BattleArena battleArena){
        if(effectDone){
            user.setOtherModifierFactorDef(user.getOtherModifierFactorDef()/1.5);                                     //- 50%
            this.effectDone = false;
        }
        super.exitingAbility(user, target, battleArena);
    }

    @Override
    public String getAbilityEffect(Pokemon user, Pokemon target, BattleArena battleArena) {
        return BattleLogManager.ACTIVATES;
    }

}
