package model.levels;

import java.util.List;

import model.entities.DonkeyKong;
import model.entities.FloorTile;
import model.entities.Mario;
import model.entities.Princess;
import model.entities.Stair;
import utilities.Pair;

/**
 * Implementation of a basic level
 */

public abstract class BasicLevelImpl implements BasicLevel{
    
    private String name;
    private String imageDirectory;
    
    private Mario mario;
    private DonkeyKong donkeyKong;
    private Princess princess;
    
    private  List<FloorTile> floor;
    private List<Stair> stairs;
    
    private Pair<Double,Double> marioSpawn;
    
    public BasicLevelImpl() {
    }

    //level
    protected void setLevelName(final String name) {
        this.name = name;
    }
    
    public String getLevelName() {
        return this.name;
    }
    
    protected void setImageDirectory(final String imageDirectory) {
        this.imageDirectory = imageDirectory;
    }
    
    public String getImageDirectory() {
        return this.imageDirectory;
    }
    
    //mario
    protected void setMario(final Mario mario) {
        this.mario = mario;
        this.marioSpawn = new Pair<Double, Double>(mario.getX(),mario.getY());
    }
    
    public Mario getMario() {
        return this.mario;
    }
    
    public Pair<Double,Double> getMarioSpawn(){
        return this.marioSpawn;
    }
    
    //donkeyKong
    protected void setDonkeyKong(final DonkeyKong donkeyKong) {
        this.donkeyKong = donkeyKong;
    }
    
    public DonkeyKong getDonkeyKong() {
        return this.donkeyKong;
    }
    
    //princess
    protected void setPrincess(final Princess princess) {
        this.princess = princess;
    }
    
    public Princess getPrincess() {
        return this.princess;
    }
    
    //floor
    protected void setFloor(final List<FloorTile> floor) {
        this.floor = floor;
    }
    
    public List<FloorTile> getFloor(){
        return this.floor;
    }
    
    //stairs
    protected void setStairs(final List<Stair> stairs) {
        this.stairs = stairs;
    }
    
    public List<Stair> getStairs(){
        return this.stairs;
    }

}
