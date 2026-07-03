package battle_arena.terrain;

import main.view.BattleMenuController;
import moves.damagingmove.DamagingMove;
import pokemon.Pokemon;
import types.Psychic;

/**
 * 
 * @author daniele
 *
 */

public class PsychicTerrain extends Terrain {

    private static final String PSYCHICSTART = "The battlefield got weird!";
    private static final String PSYCHICEND = "The weirdness disappears from the battleflied!";
    private static final String PSYCHICPROTECTION = "surrounds itself with psychic terrain!";

    public PsychicTerrain(int turns) {
        super( turns,                                                                            //turnActive
                PSYCHICSTART,                                                                    //terrainStartMessage
                PSYCHICEND);                                                                     //terrainEndMessage
    }

    @Override
    public void getTerrainMovePowerChange(Pokemon pokemon, DamagingMove move) {
        if(move.getMoveType().equals(new Psychic())){
            if(Terrain.doesPokemonGainEffect(pokemon)){
                move.setBasePower(1.5);
            }       
        }

    }

    @Override
    public void getTerrainPreventMoveMessage(Pokemon pokemon) {
        BattleMenuController.battleLogManager.setTerrainProtectionMessage(pokemon.toString() + PSYCHICPROTECTION);

    }

    @Override
    public void getTerrainEndTurnEffect(Pokemon pokemon) {
        // TODO Auto-generated method stub

    }

}
