package moves.status;

import abilities.otherconditions.MagicBounce;
import abilities.otherconditions.MagicGuard;
import battle_arena.BattleArena;
import main.view.BattleMenuController;
import moves.Move;
import pokemon.Pokemon;
import types.Type;

public abstract class StatusMove extends Move{
    
    private boolean selfEffect;                                 /*move that involves only the user (for exemple douesn't activate opponent's 
                                                                  Magic Guard :) */

    public StatusMove(String name, String description, Type moveType, double moveAccuracy, int minPP, int priority) {
        super(name, description, moveType, moveAccuracy, minPP, priority);
        this.setMakingContact(false);
        this.setSelfEffect(false);
    }
    
    public void getEffect(Pokemon user, Pokemon target, BattleArena battleArena){
        BattleMenuController.battleLogManager.setUsedMoveMessage(user, target, battleArena, this);
        if(!this.hasMoveFailed(user, target, battleArena)){
            if(!target.isProtecting){
                if(target.getAbility().equals(new MagicGuard()) && !this.isSelfEffect()){
                    ((MagicGuard)target.getAbility()).activateAbility(target, user, battleArena);
                }
                else if(target.getAbility().equals(new MagicBounce()) && !this.isSelfEffect()){
                    ((MagicBounce)target.getAbility()).activateAbility(target, user, battleArena);
                }
                else if(!target.isFainted() || this.selfEffect){
                    user.isAttacking = true;
                    user.lastMoveUsed = this;
                    user.getAbility().checkForActivation(user, target, battleArena);
                    target.getAbility().checkForActivation(user, target, battleArena);
                    this.sideEffect(user, target, battleArena);
                    user.getAbility().checkForActivation(user, target, battleArena);
                    target.getAbility().checkForActivation(user, target, battleArena);
                    user.isAttacking = false;
                }
                else{
                    BattleMenuController.battleLogManager.setMoveFailedMassage();
                }
            }
            else{
                BattleMenuController.battleLogManager.setProtectMessage(target);
            }
        }   
        else{
            BattleMenuController.battleLogManager.setMoveFailedMassage();
        }
    }

    public boolean isSelfEffect() {
        return selfEffect;
    }

    public void setSelfEffect(boolean selfEffect) {
        this.selfEffect = selfEffect;
    }
    
    

}
