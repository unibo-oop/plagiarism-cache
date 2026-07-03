package battle_arena.weather;

import java.io.Serializable;
import java.util.Arrays;

import abilities.otherconditions.MagicGuard;
import battle_arena.BattleArena;
import main.MainApp;
import main.view.BattleMenuController;
import moves.Move;
import moves.damagingmove.DamagingMove;
import pokemon.Pokemon;
import types.Type;

/**
 * 
 * @author Daniele
 *
 */

public abstract class Weather implements Serializable{
    
    private static final long serialVersionUID = 1L;
    
    private int turnsActive;
    private int currentTurns;
    //tutti gli indicatori di come funzionino i weather
    private final boolean doesModifyMoveAccuracy;                       //cambia precisione a delle mosse (specifiche) ?
    private final boolean doesModifyMovePower;                          //modifica la potenza di alcune tipologie di mosse (↑ o ↓) ?
    private final boolean doesModifyPokemonStat;                        //modifica qualche statistica di una qualche tipologia di Pokemon?
  
    private final Move[] accuracyChangeMoves;                           //tutte le mosse a cui il weather cambia precisione (se lo fa)
    private final String[] typesImmuneToDot;                            //i tipi di Pokemon immuni al danno del weather (se ce l'ha)
    private final Type powerUpType;                                     //le mosse di questo tipo aumentano di potenza (se il weather lo prevede)
    private final Type powerDropType;                                   //le mosse di questo tipo diminuiscono di potenza (se il weather lo prevede)
    private final Type typePokemonToAlterStat;                          //tipologia di Pokemon che subiranno qualche modifica di statistiche
    
    private final String weatherStartMessage;
    private final String weatherDotMessage;
    private final String weatherEndMessage;
    
    private boolean messageDone; 
    
    public boolean weatherEffectsActivable;
    
    //costruttore
    public Weather(int turns, boolean doesModifyMoveAccuracy, boolean doesModifyMovePower, boolean doesModifyPokemonStat,
                   Move[] accuracyChangeMoves, String[] typesImmuneToDot, Type powerUpType, Type powerDropType, Type typePokemonToAlterStat,
                   String weatherStartMessage, String weatherDotMessage, String weatherEndMessage){
        this.setTurnActive(turns);
        this.setCurrentTurns(0);
        this.doesModifyMoveAccuracy = doesModifyMoveAccuracy;
        this.doesModifyMovePower = doesModifyMovePower;
        this.doesModifyPokemonStat = doesModifyPokemonStat;

        if(accuracyChangeMoves != null){
            this.accuracyChangeMoves = Arrays.copyOf(accuracyChangeMoves, accuracyChangeMoves.length);
        }
        else{
            this.accuracyChangeMoves = null;
        }
        if(typesImmuneToDot != null){
            this.typesImmuneToDot = Arrays.copyOf(typesImmuneToDot, typesImmuneToDot.length);
        }
        else{
            this.typesImmuneToDot = null;
        }
        this.powerUpType = powerUpType;
        this.powerDropType = powerDropType;
        this.typePokemonToAlterStat = typePokemonToAlterStat;
        
        this.weatherStartMessage = weatherStartMessage;
        this.weatherDotMessage = weatherDotMessage;
        this.weatherEndMessage = weatherEndMessage;
        this.messageDone = false;
        
        this.weatherEffectsActivable = true;
    }

    public int getTurnActive() {
        return turnsActive;
    }
    
    public void setTurnActive(int turnActive) {
        this.turnsActive = turnActive;
    }
    
    public int getCurrentTurns() {
        return currentTurns + 1;                        //il "turno 0" è il "turno 1"
    }
    
    public void setCurrentTurns(int currentTurns) {
        this.currentTurns = currentTurns;
    }
    
    public void nextTurn(Pokemon user, Pokemon target, BattleArena battleArena){
        this.currentTurns++;
        if(this.isWeatherOver()){
            //messaggio
            this.exitingWeather(user, target, battleArena);
        }
    }
    
    public boolean isWeatherOver(){
        return this.currentTurns >= this.turnsActive;
    }
    
    
    public void checkForWeatherDot(Pokemon pokemon){
        if(this.weatherEffectsActivable){
            boolean canBeHit = true;                                //guardo se il Pokemon ha o meno una tipologia immune al danno da questo weather
            for(Type type: pokemon.getType()){
               if(type != null){
                   if(Type.containsType(this.typesImmuneToDot, type)){
                       canBeHit = false;
                   }
               }
            }
            if(pokemon.getAbility().equals(new MagicGuard())){
                canBeHit = false;
            }
            if(canBeHit){                                           //solo se non è immune, a questo punto parte la chiamata al danno
                if(!this.messageDone){
                    BattleMenuController.battleLogManager.setWeatherDotMessage(this.weatherDotMessage);
                    this.messageDone = true;
                }
                else{
                    this.messageDone = false;
                }
                this.getDot(pokemon);
            }
        }
    }
    
    public void checkForWeatherAccuracyChange(Move move){
        if(this.doesModifyMoveAccuracy){
            if(this.weatherEffectsActivable){
                for(Move consideredMove : this.accuracyChangeMoves){
                    if(move.equals(consideredMove)){
                        this.changeMoveAccuracy(move);
                    }
                }
            }
        }
    }
    
    public void checkForWeatherPowerChange(Move move){
        if(this.doesModifyMovePower){
            if(this.weatherEffectsActivable){
                if(move.getMoveType().equals(powerUpType)){
                    this.powerUpMove(move);
                }
                else if(move.getMoveType().equals(powerDropType)){
                    this.powerDropMove(move);
                }
            }
        }
    }
    
    public void checkForWeatherAlterPokemonStat(Pokemon pokemon){
        if(this.doesModifyPokemonStat){
            if(this.weatherEffectsActivable){
                for(Type type : pokemon.getType()){
                    if(type != null){
                        if(type.equals(this.typePokemonToAlterStat)){
                            this.alterPokemonStat(pokemon);
                        }
                    }
                }
            }
        }
    }
    
    public void setWeather(Pokemon user, Pokemon target, BattleArena battleArena){
        if(battleArena.weather != null){
            if(!battleArena.weather.equals(this)){                                      //non posso rimettere lo stesso weather!
                battleArena.weather.exitingWeather(user, target, battleArena);
                this.realSetWeather(user, target, battleArena);
            }          
        }
        else{
            this.realSetWeather(user, target, battleArena);
        }
        
    }  
    
    private void realSetWeather(Pokemon user, Pokemon target, BattleArena battleArena){
        battleArena.weather = this;
        BattleMenuController.battleLogManager.setWeatherStartMessage(weatherStartMessage);
        this.checkForWeatherAlterPokemonStat(user);
        user.getAbility().checkForActivation(user, target, battleArena);
        this.checkForWeatherAlterPokemonStat(target);
        target.getAbility().checkForActivation(target, user, battleArena);
    }
    
    public void exitingWeather(Pokemon user, Pokemon target, BattleArena battleArena){
        battleArena.weather = null;
        BattleMenuController.battleLogManager.setWeatherEndMessage(this.weatherEndMessage);
    }
    
    public abstract void getDot(Pokemon pokemon); 
    public abstract void changeMoveAccuracy(Move move);
    public abstract void alterPokemonStat(Pokemon pokemon);
    

    public void powerUpMove(Move move) {
        if(move instanceof DamagingMove){
            ((DamagingMove) move).setBasePower(1.5);                            //+ 50%
        }
    }
    
    public void powerDropMove(Move move) {
        if(move instanceof DamagingMove){
            ((DamagingMove) move).setBasePower(0.5);                            //- 50%
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
        if(terrain instanceof Weather){
            if(this.getClass().equals(terrain.getClass())){
                    return true;
                }
        }
        return false;
    }

}