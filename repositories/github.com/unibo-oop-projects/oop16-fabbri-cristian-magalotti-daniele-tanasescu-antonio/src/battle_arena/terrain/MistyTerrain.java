package battle_arena.terrain;

import main.view.BattleMenuController;
import moves.damagingmove.DamagingMove;
import pokemon.Pokemon;
import types.Dragon;

/**
 * 
 * @author daniele
 *
 */

public class MistyTerrain extends Terrain {

    private static final String MISTYSTART = "Mist swirled about the battlefield";
    private static final String MISTYEND = "The mist disappeared from the battlefield.";
    private static final String MISTYPROTECTION = "surrounds itself with a protective mist!";

    public MistyTerrain(int turns) {
        super( turns,                                                                          //turnActive
                MISTYSTART,                                                                    //terrainStartMessage
                MISTYEND);                                                                     //terrainEndMessage
    }

    @Override
    public void getTerrainMovePowerChange(Pokemon pokemon, DamagingMove move) {
       if(move.getMoveType().equals(new Dragon())){
           if(Terrain.doesPokemonGainEffect(pokemon)){
                   move.setBasePower(0.5);
           }
       }

    }

    @Override
    public void getTerrainPreventMoveMessage(Pokemon pokemon) {
        BattleMenuController.battleLogManager.setTerrainProtectionMessage(pokemon.toString() + MISTYPROTECTION);

    }

    @Override
    public void getTerrainEndTurnEffect(Pokemon pokemon) {
        
    }

}
