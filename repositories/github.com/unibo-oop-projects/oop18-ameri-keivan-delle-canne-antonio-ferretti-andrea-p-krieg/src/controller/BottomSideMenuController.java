package controller;

import java.util.List;

import model.objects.unit.Unit;

/**
 * The BottomSideMenuController interface extends SecondaryController. It
 * represent the controller part of the bottom side menu. This interface aims to
 * make the view part interact with the general model part.
 */
public interface BottomSideMenuController extends SecondaryController {

    /**
     * This method can be use to tell to the model to create the unit.
     * 
     * @param unitLevel is the level of the unit to create.
     */
    void createUnit(int unitLevel);

    /**
     * This method can be use to update the view.
     */
    void update();

    /**
     * This method can be use to get the name of the actual selection.
     * 
     * @return the name of the actual selection.
     */
    String getActualSelectionName();

    /**
     * This method can be use to get the number of possible unit that can be
     * created.
     * 
     * @return the number of possible unit.
     */
    int getNumberOfPossibleUnit();

    /**
     * This method can be use to get the unit name of the unit which corresponds to
     * the level passed as a parameter.
     * 
     * @param level that corresponds to a unit.
     * @return the unit name.
     */
    String getUnitName(int level);

    /**
     * This method can be use to get the unit cost to string of the unit which
     * corresponds to the level passed as a parameter.
     * 
     * @param level that corresponds to a unit.
     * @return the unit name.
     */
    String getUnitCostToString(int level);

    /**
     * This method can be use to verify if a structure can create unit.
     * @return true if it can create.
     */
    boolean canCreate();

    /**
     * This method verify if is possible to create the unit that corresponds to the level passed as a parameter.
     * @param level passed as a parameter.
     * @return true if it can be create.
     */
    boolean hasEnoughResourcesToCreate(int level);

    /**
     * This method can be use to get a list of the units that the current player can create.
     * @return the list of possible unit.
     */
    List<Unit> getPossibleUnit();
}
