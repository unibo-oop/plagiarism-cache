package moves;

import java.io.Serializable;
import java.util.Random;

import battle_arena.BattleArena;
import pokemon.Pokemon;
import types.Type;
import utilities.Utilities;

/**
 * 
 * @author Antonio
 *
 */

public abstract class Move implements Serializable{


    private static final long serialVersionUID = 1L;

    private static final double PERCENTAGEPPVARIETY = 0.6;

    private final String name;
    private final String description;
    private Type moveType;
    private double moveAccuracy;
    private int priority;
    private int PP;
    private final int maxPP;
    private boolean makesContact;
    private boolean hasRecoil;

    public boolean hasFailed;
    public boolean typeHasChanged;

    public Move(String name, String description, Type moveType, double moveAccuracy,
            int minPP, int priority){
        this.name = name;
        this.description = description;
        this.moveType = moveType;
        this.moveAccuracy = moveAccuracy;
        this.maxPP = Utilities.getAroundInt(minPP, PERCENTAGEPPVARIETY);
        this.PP = maxPP;
        this.priority = priority;
        this.typeHasChanged = false;
        this.hasRecoil = false;
        this.makesContact = false;
    }

    public String getMoveName(){
        return this.name;
    }

    public String getMoveDescription(){
        return this.description;
    }

    public Type getMoveType(){
        return this.moveType;
    }

    public double getMoveAccuracy(){
        return this.moveAccuracy;
    }

    public int getActualPP(){
        return this.PP;
    }
    public boolean hasSomePP(){
        return this.PP > 0;
    }

    public int getMaxPP(){
        return this.maxPP;
    }

    public int getPriority(){
        return this.priority;
    }

    public void setPriority(int newValue){
        this.priority = newValue;
    }

    public void resetHasFailed(){
        this.hasFailed = false;
    }

    public boolean isMakingContact(){
        return this.makesContact;
    }

    public void setMakingContact(boolean value){
        this.makesContact = value;
    }

    public void setNewType(Type newType){
        this.moveType = newType;
    }

    public void setMoveAccuracy(double value){
        this.moveAccuracy = value;
    }

    public void decrementActualPP(int value){
        this.PP -= value;
        if(this.PP < 0){
            this.PP = 0;
        }
        if(this.PP > this.maxPP){
            this.PP = this.maxPP;
        }
    }

    public abstract void sideEffect(Pokemon user, Pokemon target, BattleArena battleArena);

    public boolean hasMoveFailed(Pokemon user, Pokemon target, BattleArena battleArena){
        Random random = new Random();
        double previousAccuracy = this.moveAccuracy;
        if(battleArena.weather != null){
            battleArena.weather.checkForWeatherAccuracyChange(this);
        }
        this.hasFailed = (random.nextDouble() > (this.moveAccuracy*user.getAccuracy()/target.getElusion()));       //guardo se FALLISCE
        if(this.moveAccuracy != previousAccuracy){
            this.moveAccuracy = previousAccuracy;
        }


        return this.hasFailed;
    }

    @Override
    public boolean equals(Object move){
        if(move instanceof Move){
            if(this.name.equals(((Move)move).name)){
                return true; 
            }
        }
        //if not a Move or if not that move
        return false; 
    }

    public boolean hasRecoil() {
        return hasRecoil;
    }

    public void setHasRecoil(boolean hasRecoil) {
        this.hasRecoil = hasRecoil;
    }


}

