package model.entity.cell.standard.obtainable;
/**
 * 
 * the motivation for the energy acquisition of a cell.
 * This serves to catologize the behaviour of the cellules according to their feeding habits.
 *
 */
public enum EnergyTypeEnum {
    /**
     * eating another cell.
     */
    EATING,
    /**
     * gaining energy from light.
     */
    PHOTOSYNTHESIS,
    /**
     * by transforming minerals into energy through a specific and mysterious gene.
     */
    CONVERTING_MINERAL,
    /**
     * gifted for other near cells.
     */
    ALTRUISM
}
