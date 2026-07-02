package controller.selection;

import model.SkillTreeCommands;
import model.TurnManagementCommands;
import model.map.ObservableGameMap;
import model.objects.unit.Unit;

/**
 * Enables to play the game using selection commands, masking other "unsafe"
 * commands. It does so combining selection commands and basic commands.
 */
public interface GameCommandsUsingSelection extends SelectionCommands, ObservableGameMap, SkillTreeCommands, TurnManagementCommands {

    /**
     * AMERI. This method cretes the unit and set it to the map.
     * 
     * @param unit to create
     * 
     * @throws IllegalArgumentException if canCreateUnitFromCity returns false
     */
    void createUnitFromSelectedCity(Unit unit);

    /**
     * AMERI.
     * 
     * This method verify if there isn't CONTINUA
     * 
     * @param unit to create
     * 
     * 
     * @return true if a method that creates a unit can be called
     */
    boolean canCreateUnit(Unit unit);

    /**
     * AMERI.
     * 
     * @return true if the actual selection is a city or a capital and if the owner
     *         of that structure is the actual player.
     */
    boolean canSelectedCityCreate();

}
