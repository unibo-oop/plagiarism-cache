package battle_arena.terrain;

import moves.Move;
import moves.damagingmove.DamagingMove;
import moves.damagingmove.physical.Earthquake;
import pokemon.Pokemon;
import types.Grass;

/**
 * 
 * @author daniele
 *
 */

public class GrassyTerrain extends Terrain {

    private static final String GRASSYSTART = "Grass grew to cover the battlefield!";
    private static final String GRASSYEND = "The grass disappeared from the battlefield.";

    public GrassyTerrain(int turns) {
        super( turns,                                                                           //turnActive
                GRASSYSTART,                                                                    //terrainStartMessage
                GRASSYEND);                                                                     //terrainEndMessage
    }

    @Override
    public void getTerrainMovePowerChange(Pokemon pokemon, DamagingMove move) {
        if(move.equals(new Earthquake())){
            move.setBasePower(0.5);
        }
        if(move.getMoveType().equals(new Grass())){
            if(Terrain.doesPokemonGainEffect(pokemon)){
                move.setBasePower(1.5);
            }
        }
    }

    @Override
    public void getTerrainPreventMoveMessage(Pokemon pokemon) {
        // TODO Auto-generated method stub

    }

    @Override
    public void getTerrainEndTurnEffect(Pokemon pokemon) {
        if(Terrain.doesPokemonGainEffect(pokemon)){
            pokemon.takeDamage(-pokemon.getMaxHp()/16, false);                          //heals!
        }

    }


}
