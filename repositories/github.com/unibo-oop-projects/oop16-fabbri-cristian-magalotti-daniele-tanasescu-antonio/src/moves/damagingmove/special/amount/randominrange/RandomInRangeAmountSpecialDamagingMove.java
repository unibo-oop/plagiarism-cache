package moves.damagingmove.special.amount.randominrange;

import java.util.Random;

import battle_arena.BattleArena;
import moves.damagingmove.special.amount.AmountSpecialDamagingMove;
import pokemon.Pokemon;
import types.Type;

public abstract class RandomInRangeAmountSpecialDamagingMove extends AmountSpecialDamagingMove{
    
    private final int startValue;
    private final double varietyDamage;


    public RandomInRangeAmountSpecialDamagingMove(String name, String description, Type moveType, double moveAccuracy,
                                            int minPP, int priority, int startValue, double varietyDamage) {
        super(name, description, moveType, moveAccuracy, minPP, priority, 999);
        this.startValue = startValue;
        this.varietyDamage = varietyDamage;
    }
    @Override
    public void getDamage(Pokemon user, Pokemon target, BattleArena battleArena) {
        this.calculateAmountDamage();
        super.getDamage(user, target, battleArena);
        this.setBasePower(999);
    }
    
    public void calculateAmountDamage(){
        Random random = new Random();
        double newPower = (random.nextDouble() + this.varietyDamage)*this.startValue;
        this.setAmount(newPower);
    }
   
    
    public int getStartValue(){
        return this.startValue;
    }
    
    public double getVarietyDamage(){
        return this.varietyDamage;
    }

}
