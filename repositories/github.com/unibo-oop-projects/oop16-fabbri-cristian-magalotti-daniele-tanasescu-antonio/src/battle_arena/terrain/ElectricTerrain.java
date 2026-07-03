package battle_arena.terrain;

import main.view.BattleMenuController;
import moves.Move;
import moves.damagingmove.DamagingMove;
import pokemon.Pokemon;
import types.Electric;

/**
 * 
 * @author daniele
 *
 */

public class ElectricTerrain extends Terrain {

    private static final String ELECTRICSTART = "An electric current runs across the battlefield!";
    private static final String ELECTRICEND = "The electricity disappeared from the battlefield.";
    private static final String ELECTRICPROTECTION = "surrounds itself with the electrified terrain!";

    public ElectricTerrain(int turns) {
        super( turns,                                                                             //turnActive
                ELECTRICSTART,                                                                    //terrainStartMessage
                ELECTRICEND);                                                                     //terrainEndMessage
    }

    @Override
    public void getTerrainMovePowerChange(Pokemon pokemon, DamagingMove move) {
        if(move.getMoveType().equals(new Electric())){
            if(Terrain.doesPokemonGainEffect(pokemon)){
                move.setBasePower(1.5);
            }
        }

    }

    @Override
    public void getTerrainPreventMoveMessage(Pokemon pokemon) {
        BattleMenuController.battleLogManager.setTerrainProtectionMessage(pokemon.toString() + ELECTRICPROTECTION);

    }


    @Override
    public void getTerrainEndTurnEffect(Pokemon pokemon) {
        // TODO Auto-generated method stub

    }

}
