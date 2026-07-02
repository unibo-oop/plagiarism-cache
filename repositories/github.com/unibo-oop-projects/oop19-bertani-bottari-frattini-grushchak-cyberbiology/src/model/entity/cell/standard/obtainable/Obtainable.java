package model.entity.cell.standard.obtainable;

public interface Obtainable {
    /**
     * 
     * @param value
     * the value of decrementation.
     * 
     * @throws IllegalArgumentException if value is negative
     * 
     * set to 0 if the value is bigger of the energy of the cell.
     */
    void decrementEnergy(int value);
    /**
     * 
     * @param value
     * the value of decrementation.
     * 
     * @throws IllegalArgumentException if value is negative
     * 
     * set to 0 if the value is bigger of the mineral of the cell.
     */
    void decrementMineral(int value);
    /**
     * 
     * a standard getter.
     * 
     * @return energy
     */
    int getEnergy();

    /**
     * 
     * a standard getter.
     * 
     * @return mineral
     */
    int getMineral();

    /**
     * add or subtract (with a MAX and a MIN(0) ) to energy.
     * 
     * @param value      an int to add (or subtract) to the energy of the cell.
     * @param energyType the type of the energy added.
     * @throws IllegalArgumentException if value is negative
     */
    void incrementEnergy(int value, EnergyTypeEnum energyType);

    /**
     * add or subtract (with a MAX and a MIN(0) ) to mineral.
     * 
     * @param value an int to add (or subtract) to the mineral of the cell.
     * @throws IllegalArgumentException if value is negative.
     */
    void incrementMineral(int value);
}
