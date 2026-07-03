package moves.damagingmove.physical.onehitko;

import abilities.otherconditions.Scrappy;
import battle_arena.BattleArena;
import main.view.BattleMenuController;
import moves.damagingmove.physical.PhysicalDamagingMove;
import pokemon.Pokemon;
import types.Fight;
import types.Ghost;
import types.Normal;
import types.Type;

public class OneHitKOPhysicalDamagingMove extends PhysicalDamagingMove{

    public OneHitKOPhysicalDamagingMove(String name, String description, Type moveType, double moveAccuracy,int minPP, int priority) {
        super(name, description, 999, moveType, moveAccuracy, 0, minPP, priority);      //max power, no crit
        // TODO Auto-generated constructor stub
    }

    @Override
    public void getDamage(Pokemon user, Pokemon target, BattleArena battleArena) {
        BattleMenuController.battleLogManager.setUsedMoveMessage(user, target, battleArena, this);
        if(!target.isFainted()){
            if(!this.hasMoveFailed(user, target, battleArena)){
                if(!target.isProtecting){
                    user.isAttacking = true;
                    boolean effective = true;
                    for(Type type : target.getType()){
                        if(type != null){
                            if(Type.containsType(type.getTypeImmunities(), this.getMoveType())){    //la Terra con colpisce i Volanti..
                                //if has scrappy ability with right conditions, it will attack just the same
                                if(! (type.equals(new Ghost()) && 
                                        (this.getMoveType().equals(new Normal()) || this.getMoveType().equals(new Fight())) &&
                                        user.getAbility().equals(new Scrappy()))
                                        ){
                                    effective = false;
                                }
                            }
                        }
                    }
                    if(effective){
                        user.getAbility().checkForActivation(user, target, battleArena);
                        target.getAbility().checkForActivation(user, target, battleArena);
                        target.takeDamage(target.getMaxHp(), this.hasRecoil());
                        BattleMenuController.battleLogManager.setOneHitKOMessage();
                        user.getAbility().checkForActivation(user, target, battleArena);
                        target.getAbility().checkForActivation(user, target, battleArena);
                        user.isAttacking = false;
                    }
                    else{
                        BattleMenuController.battleLogManager.setEffectivenessMessage(0);
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
        else{
            BattleMenuController.battleLogManager.setMoveFailedMassage();
        }
    }

    @Override
    public void sideEffect(Pokemon user, Pokemon target, BattleArena battleArena) {
        // TODO Auto-generated method stub

    }

}
