package fargoal.view.api;

import fargoal.commons.api.Position;
import fargoal.model.commons.FloorElement;
import fargoal.model.interactable.pickupable.inside_chest.impl.ChestImpl;
import fargoal.model.interactable.pickupable.on_ground.SackOfMoney;
import fargoal.model.interactable.pickupable.on_ground.SwordOfFargoal;

/**
 * A factory to generate any type of renderers 
 * for every FloorElement that can appear in the game.
 */
public interface RenderFactory {
    /**
     * Method to generate a renderer for the player.
     * 
     * @param obj - the Object player to be associate with a renderer
     * @return the renderer associated with the player
     */
    Renderer playerRenderer(FloorElement obj);

    /**
     * Method to generate a renderer for a single wall cell.
     * 
     * @param pos - the position of the wall cell
     * @return the renderer associated with a wall cell
     */
    Renderer wallRenderer(Position pos);

    /**
     * Method to generate a renderer for a single tile cell.
     * 
     * @param pos - the position of the tile cell
     * @return the renderer associated with a tile cell
     */
    Renderer tileRenderer(Position pos);

    /**
     * Method to generate a renderer for a single upstair cell.
     * 
     * @param obj - the Object upstair to be associate with a renderer
     * @return the renderer associated with an object upstair
     */
    Renderer upstairRenderer(FloorElement obj);

    /**
     * Method to generate a renderer for a single downstair cell.
     * 
     * @param obj - the Object downstair to be associate with a renderer
     * @return the renderer associated with an object downstair
     */
    Renderer downstairRenderer(FloorElement obj);

    /**
     * Method to generate a renderer for the temple of the map.
     * 
     * @param obj - the Object temple to be associate with a renderer
     * @return the renderer associated with the object temple
     */
    Renderer templeRenderer(FloorElement obj);

    /**
     * Method to generate a renderer for a chest in the map.
     * 
     * @param obj - the Object chest to be associated with a renderer
     * @return the renderer associated with an object chest
     */
    Renderer chestRenderer(ChestImpl obj);

    /**
     * Method to generate a renderer for sack of gold in the map.
     * 
     * @param obj - the Object sack of gold to be associated with a renderer
     * @return the renderer associated with a sack of gold
     */
    Renderer goldRenderer(SackOfMoney obj);

    /**
     * Method to generate a renderer for a beacon in the map.
     * 
     * @param obj - the Object beacon to be associated with a renderer
     * @return the renderer associated with a beacon
     */
    Renderer beaconRenderer(FloorElement obj);

    /**
     * Method to generate a renderer for an asssassin in the map.
     * 
     * @param obj - the Object assassom to be associated with a renderer
     * @return the renderer associated with an assassin
     */
    Renderer assassinRenderer(FloorElement obj);

    /**
     * Method to generate a renderer for a barbarian in the map.
     * 
     * @param obj - the Object barbarian to be associated with a renderer
     * @return the renderer associated with a barbarian
     */
    Renderer barbarianRenderer(FloorElement obj);

    /**
     * Method to generate a renderer for a mage in the map.
     * 
     * @param obj - the Object mage to be associated with a renderer
     * @return the renderer associated with a mage
     */
    Renderer mageRenderer(FloorElement obj);

    /**
     * Method to generate a renderer for a monk in the map.
     * 
     * @param obj - the Object monk to be associated with a renderer
     * @return the renderer associated with a monk
     */
    Renderer monkRenderer(FloorElement obj);

    /**
     * Method to generate a renderer for a rogue in the map.
     * 
     * @param obj - the Object rogue to be associated with a renderer
     * @return the renderer associated with a rogue
     */
    Renderer rogueRenderer(FloorElement obj);

    /**
     * Method to generate a renderer for a spider in the map.
     * 
     * @param obj - the Object spider to be associated with a renderer
     * @return the renderer associated with a spider
     */
    Renderer spiderRenderer(FloorElement obj);

    /**
     * Method to generate a renderer for a war lord in the map.
     * 
     * @param obj - the Object war lord to be associated with a renderer
     * @return the renderer associated with a war lord
     */
    Renderer warlordRenderer(FloorElement obj);

    /**
     * Method to generate a renderer for the sword in the map.
     * 
     * @param obj - the Object sword to be associated with a renderer
     * @return the renderer associated with the sword
     */
    Renderer swordRenderer(SwordOfFargoal obj);
}
