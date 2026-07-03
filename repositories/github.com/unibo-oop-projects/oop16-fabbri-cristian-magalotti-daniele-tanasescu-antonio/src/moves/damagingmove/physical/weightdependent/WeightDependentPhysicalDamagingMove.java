package moves.damagingmove.physical.weightdependent;

import java.util.Arrays;

import battle_arena.BattleArena;
import moves.damagingmove.physical.PhysicalDamagingMove;
import pokemon.Pokemon;
import types.Type;

public abstract class WeightDependentPhysicalDamagingMove extends PhysicalDamagingMove{

    public int[] weightRanges;                  //in Kg, ordine crescente        
    public int[] weighDependentDamage;          //sempre ordine crescente! ha un elemento in più, caso > ultimo range
    private boolean isRelative;                 //se la mossa non varia sul peso assoluto del nemico ma rispetto a quello dell'user
    
    public WeightDependentPhysicalDamagingMove(String name, String description, Type moveType, double moveAccuracy,
                                    double actualCritRange, int minPP, int priority, int[]weightRanges,int[] weighDependentDamage) {
        super(name, description, 999, moveType, moveAccuracy, actualCritRange, minPP, priority);
        this.weightRanges = Arrays.copyOf(weightRanges, weightRanges.length);
        this.weighDependentDamage = Arrays.copyOf(weighDependentDamage, weighDependentDamage.length);
        this.isRelative = false;
    }
    
    @Override
    public void getDamage(Pokemon user, Pokemon target, BattleArena battleArena){
        this.calculateBasePower(user, target);
        super.getDamage(user, target, battleArena);
    }
    
    public void calculateBasePower(Pokemon user, Pokemon target){
        if(!isRelative){
            int index = 0;
            for(int weight: this.weightRanges){
                if(target.getWeight() >= weight){
                    index++;
                }
            }
            this.setBasePower(this.weighDependentDamage[index]);
        }
        else{
            int weightRelativePercentage = (int) ((target.getWeight()*100)/user.getWeight());
            int index = 0;
            for(int weight : this.weightRanges){
                if(weightRelativePercentage >= weight){
                    index++;
                }
            }
            this.setBasePower(this.weighDependentDamage[index]);
        }
    }

}
