package model.entity.cell.standard.history;

import model.entity.cell.standard.obtainable.EnergyTypeEnum;

/**
 * a interface to store all the usefull data of a cell to create a visual filter for the user.
 * 
 *
 */
public interface History {
    /**
     * 
     * @return the total energy gained by the cell since its birth.
     */
    int getTotalEnergyGained();
    /**
     * 
     * @param energyType
     * the type of energy desired
     * @return
     * the total energy gained by the cell since its birth of a specific type.
     */
    int getSpecificEnergyGained(EnergyTypeEnum energyType);
    /**
     * the generation is the number of father to create the cell.
     * @return
     * a positive integer.
     */
    int getGeneration();

}
