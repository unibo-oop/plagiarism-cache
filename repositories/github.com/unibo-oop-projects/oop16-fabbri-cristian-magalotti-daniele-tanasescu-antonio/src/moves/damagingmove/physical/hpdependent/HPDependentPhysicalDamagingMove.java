package moves.damagingmove.physical.hpdependent;

import java.util.Arrays;

import battle_arena.BattleArena;
import moves.damagingmove.physical.PhysicalDamagingMove;
import pokemon.Pokemon;
import types.Type;

public abstract class HPDependentPhysicalDamagingMove extends PhysicalDamagingMove{
    
    public double[] hpRanges;                      //in ordine decrescente di hp
    public int[] hpDependentDamage;             //in ordine crescente! ha un elemento in più, caso > ultimo range

    public HPDependentPhysicalDamagingMove(String name, String description, Type moveType, double moveAccuracy,
                                            double actualCritRange, int minPP, int priority, double[]hpRanges,int[] hpDependentDamage){
        super(name, description, 999, moveType, moveAccuracy, actualCritRange, minPP, priority);
        this.hpRanges = Arrays.copyOf(hpRanges, hpRanges.length);
        this.hpDependentDamage = Arrays.copyOf(hpDependentDamage, hpDependentDamage.length);
    }

    @Override
    public void getDamage(Pokemon user, Pokemon target, BattleArena battleArena) {
        this.calculateBasePower(user, target);
        super.getDamage(user, target, battleArena);
        
    }
    
    public void calculateBasePower(Pokemon user, Pokemon target){
        int index = 0;
        for(double hpRange: this.hpRanges){
            if(target.getHp() < target.getMaxHp()*hpRange){
                index++;
            }
        }
        this.setBasePower(this.hpDependentDamage[index]);
    }

}
