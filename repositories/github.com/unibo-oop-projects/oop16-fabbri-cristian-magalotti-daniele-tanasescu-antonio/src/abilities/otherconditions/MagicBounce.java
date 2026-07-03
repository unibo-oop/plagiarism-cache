package abilities.otherconditions;

import battle_arena.BattleArena;
import main.view.BattleMenuController;
import managers.BattleLogManager;
import moves.status.StatusMove;
import pokemon.Pokemon;

public class MagicBounce extends OtherConditionsAbility{

    public MagicBounce() {
        super(  "Magic Bounce",                                                                     //name, 
                "Reflects status moves, instead of getting hit by them.");                          //description
    }

    @Override
    public void activateAbility(Pokemon user, Pokemon target, BattleArena battleArena) {
        if(!target.getAbility().equals(this)){                                                      //it would generate a loop...
            BattleMenuController.battleLogManager.setAbilityActivationMessage(user, this.getAbilityEffect(user, target, battleArena));
            if(target.equals(battleArena.activeEnemy)){                                             /*it's surely a StatusMove: MagicBounce is called
                                                                                                    *inside StatusMove's sideEffect! */
                ((StatusMove)battleArena.enemyMove).sideEffect(user, target, battleArena);          //user is the owner of this ability, now!
                
            }
            else{                                                                                   //i.e. player
                ((StatusMove)battleArena.playerMove).sideEffect(user, target, battleArena); 
            }
        }
        else{
            BattleMenuController.battleLogManager.setAbilityActivationMessage(user, this.getAbilityEffect(user, target, battleArena));
            BattleMenuController.battleLogManager.setMoveFailedMassage();
        }
        
    }

    @Override
    public String getAbilityEffect(Pokemon user, Pokemon target, BattleArena battleArena) {
        return BattleLogManager.ACTIVATES;
    }

}
