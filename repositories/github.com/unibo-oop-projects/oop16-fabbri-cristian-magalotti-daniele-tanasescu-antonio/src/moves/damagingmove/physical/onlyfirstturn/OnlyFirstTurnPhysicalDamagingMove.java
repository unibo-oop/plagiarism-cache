package moves.damagingmove.physical.onlyfirstturn;

import battle_arena.BattleArena;
import main.view.BattleMenuController;
import moves.damagingmove.physical.PhysicalDamagingMove;
import pokemon.Pokemon;
import types.Type;

public abstract class OnlyFirstTurnPhysicalDamagingMove extends PhysicalDamagingMove{

    public OnlyFirstTurnPhysicalDamagingMove(String name, String description, int moveBasePower, Type moveType, double moveAccuracy,
            double actualCritRange, int minPP, int priority) {
        super(name, description, moveBasePower, moveType, moveAccuracy, actualCritRange, minPP, priority);
    }
    
    //they work only if it's this user pokemon's first turn
    @Override
    public void getDamage(Pokemon user, Pokemon target, BattleArena battleArena){
        if(user.isFirstTurn()){
            super.getDamage(user, target, battleArena);
        }
        else{
            BattleMenuController.battleLogManager.setUsedMoveMessage(user, target, battleArena, this);
            BattleMenuController.battleLogManager.setMoveFailedMassage();
        }
    }

}
