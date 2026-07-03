package battle_arena.terrain;

import java.io.Serializable;

import abilities.movecondition.Levitate;
import battle_arena.BattleArena;
import main.view.BattleMenuController;
import moves.Move;
import moves.damagingmove.DamagingMove;
import pokemon.Pokemon;
import types.Flying;

/**
 * 
 * @author Daniele
 *
 */

public abstract class Terrain implements Serializable{

    private static final long serialVersionUID = 1L;
    
    private int turnActive;
    
    private final String terrainStartMessage;
    private final String terrainEndMessage;

    //costruttore
    public Terrain(int turnActive, String terrainStartMessage, String terrainEndMessage){
        this.turnActive = turnActive;
        this.terrainStartMessage = terrainStartMessage;
        this.terrainEndMessage = terrainEndMessage;
    }

    //getter
    public int getTerrainTurnActive(){
        return this.turnActive;
    }

    public abstract void getTerrainMovePowerChange(Pokemon pokemon, DamagingMove move);
    public abstract void getTerrainPreventMoveMessage(Pokemon pokemon);
    public abstract void getTerrainEndTurnEffect(Pokemon pokemon);

    public void setTerrain(Pokemon user, Pokemon target, BattleArena battleArena){
        if(battleArena.terrain != null){
            if(!battleArena.terrain.equals(this)){                                      //non posso rimettere lo stesso weather!
                battleArena.terrain.exitingTerrain(user, target, battleArena);
            }
        }

        //it's not a weather, but there's no reason why to create a new function!
        BattleMenuController.battleLogManager.setWeatherStartMessage(this.terrainStartMessage);
        battleArena.terrain = this;
    }

    public void exitingTerrain(Pokemon user, Pokemon target, BattleArena battleArena){
        battleArena.terrain = null;
        BattleMenuController.battleLogManager.setWeatherEndMessage(this.terrainEndMessage);
    }
    
    //the pokemon doesn't have to fly!
    public static boolean doesPokemonGainEffect(Pokemon pokemon){
        return (! (pokemon.getType()[0].equals(new Flying()) || (pokemon.getType()[1] != null? pokemon.getType()[1].equals(new Flying()) : false) ||pokemon.getAbility().equals(new Levitate())));
    }
    
    public void decrementTurnActive(Pokemon user, Pokemon target, BattleArena battleArena){
        this.turnActive--;
        if(this.turnActive == 0){
            this.exitingTerrain(user, target, battleArena);
        }
    }
    
    @Override
    public String toString(){
        String[] name = this.getClass().getSimpleName().split("(?=[A-Z])");
        String complete = "";
        for(String part : name){
            complete += part;
            complete += " ";
        }
        return complete;
    }

    @Override
    public boolean equals (Object terrain){
        if(terrain instanceof Terrain){
            if(this.getClass().equals(terrain.getClass())){
                return true;
            }
        }
        return false;
    }	
    
}
