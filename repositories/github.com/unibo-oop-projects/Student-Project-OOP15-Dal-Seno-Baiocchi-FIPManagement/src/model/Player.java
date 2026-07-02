package model;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
/**
 * Model a player
 * @author lucadalseno
 *
 */
public class Player extends PersonImpl{
    /**
     * 
     */
    private static final long serialVersionUID = 8648663467990512362L;
    private PLAYEROLE role;
    private double height;
    private List<Statistics> stat;
    
    public Player(String name, String surname, Date birth, String cf,PLAYEROLE role,double height) {
        super(name, surname, birth, cf);
        this.role = role;
        this.height = height;
        this.stat = new ArrayList<Statistics>();
    }
    /**
     * Getter for role
     * @return the player role
     */
    public PLAYEROLE getRole(){
        return this.role;
    }
    
    /**
     * Setter for role
     * @param r: the role to set
     */
    public void setRole(PLAYEROLE r){
        this.role = r;
    }
    
    /**
     * Getter for height
     * @return the height of the player
     */
    public double getHeight(){
        return this.height;
    }
    
    /**
     * Getter for statistics
     * @return a List of all the statistics
     */
    public List<Statistics> getStatistics(){
        return this.stat;
    }
    
    /**
     * Add a statistic to that player
     * @param s: the statistic to add
     */
    public void addStat(Statistics s){
        this.stat.add(s);
    }
    
    /**
     * Setter for height
     * @param height: the height to set
     */
    public void setHeight(double height){
        this.height = height;
    }
    
    /**
     * The role a player can have
     * @author lucadalseno
     *
     */
    public static enum PLAYEROLE{
        POINTGUARD,GUARD,SHOOTING_GUARD,POINT_FORWARD,SWINGMAN,SMALLFORWARD,POWERFORWARD,CENTER
    }
}