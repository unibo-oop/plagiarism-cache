package moves.damagingmove.physical.bypassprotect;

import battle_arena.BattleArena;
import main.MainApp;
import main.view.BattleMenuController;
import moves.damagingmove.physical.PhysicalDamagingMove;
import pokemon.Pokemon;
import types.Type;

public abstract class BypassProtectPhysicalDamagingMove extends PhysicalDamagingMove{

    public BypassProtectPhysicalDamagingMove(String name, String description, int moveBasePower, Type moveType, double moveAccuracy,
                                             double actualCritRange, int minPP, int priority) {
        super(name, description, moveBasePower, moveType, moveAccuracy, actualCritRange, minPP, priority);
    }
    
    @Override
    public void getDamage(Pokemon user, Pokemon target, BattleArena battleArena){
        if(target.isProtecting){
            target.isProtecting = false;
            BattleMenuController.battleLogManager.setDestroyProtectMessage(user, target);
        }
        super.getDamage(user, target, battleArena);
    }

}
