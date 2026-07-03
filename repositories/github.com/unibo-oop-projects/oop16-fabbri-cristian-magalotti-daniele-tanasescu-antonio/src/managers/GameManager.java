package managers;

import java.io.Serializable;

import team.Player;
import team.Team;
import team.enemies.Enemy;

/**
 * 
 * @author Cristian
 *
 */

//this class will contain all the references to important objects that should be unique (trying to emulate Singleton...)
public class GameManager implements Serializable{

    private static final long serialVersionUID = 1L;
    
    public static final int NUMENEMIES = 15;
    public static final String RESOURCES = "resources/";
    public static final String POKEMONFOLDER = "pokemon/";
    public static final String IMAGEFRONT = "Front";
    public static final String IMAGEBACK = "Back";
    public static final String DOT = ".";
    public static final String UNDERSCORE = "_";
    public static final String ANIMATEDIMAGEEXTENSION = "gif";
    public static final String STATICIMAGEEXTENSION = "png";
         
    
    private static GameManager instance;
    private Player player;
    private Enemy[] allEnemies;
    private int lastEnemyCreated;   
    
    

    public GameManager() {    
        // Exists only to defeat instantiation.
    }
    
    public static GameManager getInstance() {
        if(instance == null) {
           instance = new GameManager();
        }
        return instance;
     }
    
    public void newPlayer(){
        
        this.setPlayer(new Player(PokemonListManager.PLAYERSTARTINGLIST, Team.MAX_POKEMON));
    }

    public Player getPlayer() {
        return player;
    }

    public void setPlayer(Player player) {
        this.player = player;
    }

    public Enemy[] getAllEnemies() {
        return allEnemies;
    }

    public void setAllEnemies() {
        this.allEnemies = new Enemy[NUMENEMIES];
        this.lastEnemyCreated = -1;                              //the next one created will be at position 0
    }
    
    public void createNewEnemy(){
        if(this.lastEnemyCreated < NUMENEMIES - 1){
            this.lastEnemyCreated ++;
            this.allEnemies[this.lastEnemyCreated] = EnemyManager.ALLENEMIES[this.lastEnemyCreated];
        }
    }
    
    public int getLastEnemyCreatedIndex(){
        return this.lastEnemyCreated;
    }
    
    protected static void setReference(GameManager gameManager){
        instance = gameManager;
    }

}
