package team.enemies;

import java.io.Serializable;

import managers.GameManager;
import pokemon.Pokemon;
import team.Team;
import team.enemies.enemyais.AIBattle;

/**
 * 
 * @author daniele
 *
 */

public class Enemy extends Team implements Serializable{

    private static final long serialVersionUID = 1L;
    private AIBattle alBattle;
    private final String enemyName;
    private final String enemySurname;
    private final String enemyClass;
    private final String battleScenario;
    private final int enemyLevel;
    private final int winningPoints;

    private int numOfWins;
    private int numOfLosses;

    //indica se il nemico è stato sconfitto (e ad es. seil giocatore può vedere a schermo il team di questo enemy)
    private boolean hasBeenDefeated; 
    //è già stato fatto lo scambio??
    private boolean hasTraded;

    //costruttore
    public Enemy(Pokemon[] availablePokemons, AIBattle alBattle,final int numOfPokemon,String enemyName,
                 String enemySurname,String enemyClass,String battleScenario, int enemyLevel, int winningPoints){
        super(availablePokemons, numOfPokemon);
        this.alBattle = alBattle;
        this.enemyName = enemyName;
        this.enemySurname = enemySurname;
        this.enemyClass = enemyClass;
        this.battleScenario = battleScenario;
        this.enemyLevel = enemyLevel;
        this.winningPoints = winningPoints;

        this.startValues();
    }

    //getter
    public AIBattle getAlBattle(){
        return this.alBattle;
    }

    public String getEnemyName() {
        return enemyName;
    }

    public String getEnemySurname() {
        return enemySurname;
    }

    public String getEnemyClass() {
        return enemyClass;
    }

    public int getNumOfWins() {
        return numOfWins;
    }

    public void incrementNumOfWins() {
        this.numOfWins++;
        if(!this.hasBeenDefeated){
            this.hasBeenDefeated = true;
        }
    }

    public int getNumOfLosses() {
        return numOfLosses;
    }

    public void incrementNumOfLosses() {
        this.numOfLosses++;
    }

    private void startValues(){
        this.numOfWins = 0;
        this.numOfLosses = 0;
        this.setHasBeenDefeated(false);
        this.setHasTraded(false);
    }

    public boolean hasBeenDefeated() {
        return hasBeenDefeated;
    }

    public void setHasBeenDefeated(boolean isTeamVisible) {
        this.hasBeenDefeated = isTeamVisible;
    }

    public int getEnemyLevel() {
        return enemyLevel;
    }

    public String getEnemyImage(){
        return (GameManager.RESOURCES + this.enemyName + GameManager.UNDERSCORE + this.enemySurname +
                GameManager.DOT + GameManager.STATICIMAGEEXTENSION);              
    }

    public boolean hasTraded() {
        return hasTraded;
    }

    public void setHasTraded(boolean hasTraded) {
        this.hasTraded = hasTraded;
    }

    public int getWinningPoints() {
        return winningPoints;
    }
    
    public static String getEnemyScenarioCompletePath(String scenario){
        return ("resources/battlescenarios/" + scenario + ".png");
    }

    public String getBattleScenario() {
        return battleScenario;
    }

}
