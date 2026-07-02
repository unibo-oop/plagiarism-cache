package model;

import java.io.Serializable;
import java.security.InvalidParameterException;

import exceptions.InvalidStatisticException;
/**
 * Contains all the type of statistics
 * @author lucadalseno
 *
 */
public class Statistics implements Serializable{ 
    /**
     * 
     */
    private static final long serialVersionUID = 7872501312758066108L;
    public int points;
    public int offRebounds;
    public int defRebounds;
    public int assists;
    public int blocks;
    public int personalFouls;
    public int turnovers;
    public int steals;
    
    public Statistics(){
        this.points = 0;
        this.offRebounds = 0;
        this.defRebounds = 0;
        this.assists = 0;
        this.blocks = 0;
        this.personalFouls = 0;
        this.turnovers = 0;
        this.steals = 0;
    }
    
    /**
     * Getter for points
     * @return the statistic points
     */
    public int getPoints(){
        return this.points;
    }
    
    /**
     * Getter for offensive rebounds
     * @return the statistic offensive rebounds
     */
    public int getOffRebounds(){
        return this.offRebounds;
    }
    
    /**
     * Getter for defensive rebounds
     * @return the statistic defensive rebounds
     */
    public int getDefRebounds(){
        return this.defRebounds;
    }
    
    /**
     * Getter for assists
     * @return the statistic assists
     */
    public int getAssists(){
        return this.assists;
    }
    
    /**
     * Getter for blocks
     * @return the statistic blocks
     */
    public int getBlocks(){
        return this.blocks;
    }
    
    /**
     * Getter for personal fouls
     * @return the statistic personal fouls
     */
    public int getPersonalFouls(){
        return this.personalFouls;
    }
    
    /**
     * Getter for turnovers
     * @return the statistic turnovers
     */
    public int getTurnovers(){
        return this.turnovers;
    }
    
    /**
     * Getter for steals
     * @return the statistic steals
     */
    public int getSteals(){
        return this.steals;
    }
    
    /**
     * Increase the statistic points
     * @param value: the value to add to points
     */
    public void increasePoints(int value){
        this.points += value;
    }
    
    /**
     * Decrease the statistic points
     * @param value:the value to subtract to points
     */
    public void decreasePoints(int value){
       if(this.points < value){
           throw new InvalidParameterException();
       }
        this.points -= value;
    }
    
    /**
     * Increase the statistic offensive rebounds
     */
    public void increaseOffRebounds(){
        this.offRebounds++;
    }
    
    /**
     * Decrease the statistic offensive rebounds
     */
    public void decreaseOffRebounds(){
        if(this.offRebounds == 0){
            throw new InvalidStatisticException();
        }
        this.offRebounds--;
    }
    
    /**
     * Increase the statistic defensive rebounds
     */
    public void increaseDefRebounds(){
        this.defRebounds++;
    }
    
    /**
     * Decrease the statistic defensive rebounds
     */
    public void decreaseDefRebounds(){
        if(this.defRebounds == 0){
            throw new InvalidStatisticException();
        }
        this.defRebounds--;
    }
    
    /**
     * Increase the statistic assists
     */
    public void increseAssists(){
        this.assists++;
    }
    
    /**
     * Decrease the statistic assists
     */
    public void decreaseAssists(){
        if(this.assists == 0){
            throw new InvalidStatisticException();
        }
        this.assists--;
    }
    
    /**
     * Increase the statistic blocks
     */
    public void increaseBlocks(){
        this.blocks++;
    }
    
    /**
     * Decrease the statistic blocks
     */
    public void decreaseBlocks(){
        if(this.blocks == 0){
            throw new InvalidStatisticException();
        }
        this.blocks--;
    }
    
    /**
     * Increase the statistic personal fouls
     */
    public void incresePersonalFouls(){
        this.personalFouls++;
    }
    
    /**
     * Decrease the statistic personal fouls
     */
    public void decreasePeronsalFouls(){
        if(this.personalFouls == 0){
            throw new InvalidStatisticException();
        }
        this.personalFouls--;
    }
    
    /**
     * Increase the statistic turnovers
     */
    public void increaseTurnovers(){
        this.turnovers++;
    }
    
    /**
     * Decrease the statistic turnovers
     */
    public void decreaseTurnovers(){
        if(this.turnovers == 0){
            throw new InvalidStatisticException();
        }
        this.turnovers--;
    }
    
    /**
     * Increase the statistic steals
     */
    public void increaseSteals(){
        this.steals++;
    }
    
    /**
     * Decrease the statistic steals
     */
    public void decreaseSteals(){
        if(this.steals == 0){
            throw new InvalidStatisticException();
        }
        this.steals--;
    }
}
