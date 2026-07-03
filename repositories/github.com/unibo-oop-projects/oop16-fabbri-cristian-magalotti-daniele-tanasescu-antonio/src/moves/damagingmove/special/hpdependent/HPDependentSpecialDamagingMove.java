package moves.damagingmove.special.hpdependent;

import battle_arena.BattleArena;
import moves.damagingmove.special.SpecialDamagingMove;
import pokemon.Pokemon;
import types.Type;

public abstract class HPDependentSpecialDamagingMove extends SpecialDamagingMove{
    
    private int fullHPPower;

    public HPDependentSpecialDamagingMove(String name, String description, int moveBasePower, Type moveType, double moveAccuracy,
                                          double actualCritRange, int minPP, int priority) {
        super(name, description, moveBasePower, moveType, moveAccuracy, actualCritRange, minPP, priority);
        this.fullHPPower = moveBasePower;
    }
    
    public void getDamage(Pokemon user, Pokemon target, BattleArena battleArena){
        this.setBasePower((this.fullHPPower*user.getHp())/user.getMaxHp());
        super.getDamage(user, target, battleArena);
    }

}
