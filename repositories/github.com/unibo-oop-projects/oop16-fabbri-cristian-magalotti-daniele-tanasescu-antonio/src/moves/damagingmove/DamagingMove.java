package moves.damagingmove;

import java.util.Random;

import battle_arena.BattleArena;
import moves.Move;
import pokemon.Pokemon;
import types.Type;

public abstract class DamagingMove extends Move{
    
    public static final double critRange1 = 0.0625;
    public static final double critRange2 = 0.125;
    public static final double critRange3 = 0.25;
    public static final double critRange4 = 0.33;
    public static final double critRange5 = 0.5;
    public static final double critRange6 = 1;

    private int moveBasePower;
    private double lastDamageDone;   
    private double actualCritRange;

    
    public boolean sideEffect;
    
    public DamagingMove(String name, String description, int moveBasePower, Type moveType, double moveAccuracy,
            double actualCritRange, int minPP, int priority) {
        super(name, description, moveType, moveAccuracy, minPP, priority);
        this.moveBasePower = moveBasePower;
        this.actualCritRange = actualCritRange;
        this.sideEffect = false;
    }
    
    public int getMoveBasePower(){
        return this.moveBasePower;
    }
    
    public void setBasePower(int newBasePower){
        this.moveBasePower = newBasePower;
    }
    
    public double getLastDamageDone(){
        return this.lastDamageDone;
    }
    
    public void setLastDamageDone(double damage){
        this.lastDamageDone = damage;
    }
    
    public void setBasePower(double percentage){
        double power = this.moveBasePower ;
        power *= percentage;
        this.moveBasePower = (int)power;
    }
    
    public double getCritRange(){
        return this.actualCritRange;
    }

    
    //Determinates if the move will crit 
    public boolean critHit(){
        Random random = new Random();
        if((double) random.nextDouble() < this.actualCritRange){
            return true;
        }
        return false;
    }
    
    public abstract void getDamage(Pokemon user, Pokemon target, BattleArena battleArena);

    
}
