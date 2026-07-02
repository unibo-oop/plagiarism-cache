package model.buildingscounter;

import model.construction.ConstructionType;

/**
 * This interface is used to maintain the number of buildings of each type.
 */
public interface BuildingsCounter {
    /**
     * Method used to increase by one the quantity of one type of building.
     * @param constructionType
     *          the name of the building
     */
    void addBuilding(ConstructionType constructionType);

    /**
     * Method used to decrease by one the quantity of one type of building.
     * @param name
     *          the name of the building
     */
    void removeBuilding(ConstructionType name);

    /**
     * Method used to get the number of building of one type.
     * @param name
     *          the name of the building
     * @return
     *          the number of building of a type present in the map
     */
    int getNumberOf(ConstructionType name);
}
