package battle_arena;

import pokemon.Pokemon;
import status_condition.volatile_status.VolatileStatusCondition;
import team.Player;
import team.Team;
import team.enemies.Enemy;

import java.io.Serializable;
import java.util.Random;

import battle_arena.terrain.Terrain;
import battle_arena.weather.Weather;
import main.MainApp;
import moves.Move;
import moves.damagingmove.physical.variablepriority.Pursuit;
import moves.status.Switch;

/**
 * 
 * @author daniele
 *
 */

public class BattleArena implements Serializable{

    private static final long serialVersionUID = 1L;

    private static final int MAXPOKEMONINBATTLE = 2;

    private final Player player;                        //squadra del giocatore
    private final Enemy enemy;                          //squadra nemica
    
    public Pokemon activePlayer;                        //pokemon attivo del giocatore
    public Pokemon activeEnemy;                         //pokemon attivo del nemico
    
    public Weather weather;                             //condizione climatica attiva
    public Terrain terrain;                             //campo attivo

    public boolean hasEnemyChosen;
    public boolean hasPlayerChosen;
    
    public boolean beginningOfTurn;
    public boolean battleTime;
    public boolean endOfTurn;
    
    public int turnCount;
    
    public Pokemon[] speedTie;
    public Move enemyMove;
    public Move playerMove;
    
   
    public BattleArena(Player player, Enemy enemy) {
        this.player = player;
        this.enemy = enemy;
        this.activePlayer = player.getFirst();
        this.activePlayer.resetBattleIndicators();
        this.activeEnemy = enemy.getFirst();
        this.activeEnemy.resetBattleIndicators();
        this.weather = null;    //vorremo creare battaglie che iniziano con una condizione climatica? sarebbe figo!                    
        this.terrain = null;    //o anche campi....nel qual caso cambieremo il costruttore passandoli per parametro!
        this.hasEnemyChosen = false;
        this.hasPlayerChosen = false;
        this.turnCount = 0;
        this.speedTie = new Pokemon[MAXPOKEMONINBATTLE];
    }
    
    public Player getPlayer(){
        return this.player;
    }
    
    public Enemy getEnemy(){
        return this.enemy;
    }
    
    //SE le mosse sono state scelte, decide l'ordine di attacco confrontando priority, velocità o affidandosi al caos
    public void setSpeedTie(){
        if(this.hasEnemyChosen && this.hasPlayerChosen){
            //condizione nemico che cambia e player che usa Pursuit
            if(this.enemyMove.equals(new Switch()) && this.playerMove.equals(new Pursuit())){
                ((Pursuit)this.playerMove).opponentSwitch = true;
                ((Pursuit)this.playerMove).setPriority(((Pursuit)this.playerMove).getOtherPriority());
            }
          	//condizione player che cambia e nemico che usa Pursuit
            else if(this.playerMove.equals(new Switch()) && this.enemyMove.equals(new Pursuit())){
                ((Pursuit)this.enemyMove).opponentSwitch = true;
                ((Pursuit)this.enemyMove).setPriority(((Pursuit)this.enemyMove).getOtherPriority());
            }
            if(this.enemyMove.getPriority() > this.playerMove.getPriority()){
                this.enemyFirst();
            }
            else if(this.playerMove.getPriority() > this.enemyMove.getPriority()){
                this.playerFirst();
            }
            else{
                if(this.activeEnemy.getSpe() > this.activePlayer.getSpe()){
                    this.enemyFirst();
                }
                else if(this.activePlayer.getSpe() > this.activeEnemy.getSpe()){
                    this.playerFirst();
                }
                else{
                    this.realSpeedTie();
                }
            }
        }
    }
    
    //il nemico è più veloce -> verrà gestito prima del giocatore (attacca prima)
    public void enemyFirst(){
        this.speedTie[0] = this.activeEnemy;
        this.speedTie[1] = this.activePlayer;
    }
    
    //il giocatore è più veloce -> ...
    public void playerFirst(){
        this.speedTie[0] = this.activePlayer;
        this.speedTie[1] = this.activeEnemy;
    }
    
    //totalmente casuale
    public void realSpeedTie(){
        Random random = new Random();
        if(random.nextBoolean()){
            this.enemyFirst();
        }
        else{
            this.playerFirst();
        }
    }
    
    public void setPlayerMove(Move move){
        this.playerMove = move;
        this.hasPlayerChosen = true;
    }
    
    public void switchActive(Team team, Pokemon pokemon){
    	if(team instanceof Enemy){
            this.activeEnemy.resetAlterations();
            this.activeEnemy.resetAllOtherModifierFactors();
            this.activeEnemy.resetBattleFactors();
            this.activeEnemy.resetBattleIndicators();
            VolatileStatusCondition.deleteAllVolatiles(this.activeEnemy.volatileStatusConditions);
            this.activeEnemy.getAbility().resetAbilityInSwitch(this.activeEnemy, this.activePlayer, this);
            this.activeEnemy = pokemon;
            pokemon.turnCount --;                              //this isn't effectively its first turn!
        }
        else{
            this.activePlayer.resetAlterations();
            this.activePlayer.resetAllOtherModifierFactors();
            this.activePlayer.resetBattleFactors();
            this.activePlayer.resetBattleIndicators();
            VolatileStatusCondition.deleteAllVolatiles(this.activePlayer.volatileStatusConditions);
            this.activePlayer.getAbility().resetAbilityInSwitch(this.activePlayer, this.activeEnemy, this);
            this.activePlayer = pokemon;
            pokemon.turnCount --;                             //this isn't effectively its first turn!
        }
    	MainApp.battleMenuController.setSpriteChanged(true);            //this will change the sprite!
    }
    
    public void restoreEnemyAndPlayer(){
        this.enemy.pokemonCenterCare();
        this.player.pokemonCenterCare();
    }
}
