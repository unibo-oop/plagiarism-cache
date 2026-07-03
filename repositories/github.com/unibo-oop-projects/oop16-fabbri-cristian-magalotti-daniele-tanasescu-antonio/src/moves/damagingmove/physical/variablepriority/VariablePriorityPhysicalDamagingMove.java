package moves.damagingmove.physical.variablepriority;

import battle_arena.BattleArena;
import moves.damagingmove.physical.PhysicalDamagingMove;
import pokemon.Pokemon;
import types.Type;

public abstract class VariablePriorityPhysicalDamagingMove extends PhysicalDamagingMove{

    private int standardPriority;
    private int otherPriority;
    
    
    public VariablePriorityPhysicalDamagingMove(String name, String description, int moveBasePower, Type moveType, double moveAccuracy,
                                                double actualCritRange, int minPP, int standardPriority, int otherPriority) {
        super(name, description, moveBasePower, moveType, moveAccuracy, actualCritRange, minPP, standardPriority);
        this.setStandardPriority(standardPriority);
        this.setOtherPriority(otherPriority);
    }
    
    @Override
    public void getDamage(Pokemon user, Pokemon target, BattleArena battleArena){
        super.getDamage(user, target, battleArena);
        this.setPriority(this.standardPriority);
    }
    public abstract boolean checkPriorityChangeCondition();

    public int getOtherPriority() {
        return otherPriority;
    }

    public void setOtherPriority(int otherPriority) {
        this.otherPriority = otherPriority;
    }

    public int getStandardPriority() {
        return standardPriority;
    }

    public void setStandardPriority(int standardPriority) {
        this.standardPriority = standardPriority;
    }
}
