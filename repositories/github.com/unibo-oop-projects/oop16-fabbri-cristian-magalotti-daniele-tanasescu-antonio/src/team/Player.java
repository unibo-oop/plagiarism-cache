package team;

import java.io.Serializable;

import pokemon.Pokemon;

/**
 * 
 * @author daniele
 *
 */

public class Player extends Team implements Serializable{
    
    private static final long serialVersionUID = 1L;
    private int numOfEnemiesDefeated;
    private int currentPosition;
    private int playerPoints;

    public Player(Pokemon[] availablePokemons, int numOfPokemon) {
        super(availablePokemons, numOfPokemon);
        this.numOfEnemiesDefeated = 0;
        this.setCurrentPosition(0);
        this.playerPoints = 0;
    }

    public int getNumOfEnemiesDefeated() {
        return numOfEnemiesDefeated;
    }

    public void incrementNumOfEnemiesDefeated() {
        this.numOfEnemiesDefeated++;
    }

    public int getCurrentPosition() {
        return currentPosition;
    }
    
    //helpful in loading
    public void setCurrentPosition(int currentPosition){
        this.currentPosition = currentPosition;
    }

    public void changeCurrentPosition(int change) {
        this.currentPosition += change;
    }

    public int getPlayerPoints() {
        return playerPoints;
    }

    public void incrementPlayerPoints(int value) {
        this.playerPoints += value;
    }
    
    public void decrementPlayerPoints(int value){
        this.playerPoints -= value;
    }

}
