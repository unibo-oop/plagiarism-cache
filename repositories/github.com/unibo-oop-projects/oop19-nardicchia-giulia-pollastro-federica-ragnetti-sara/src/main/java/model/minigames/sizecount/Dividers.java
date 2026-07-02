package model.minigames.sizecount;

import java.util.List;

/**
 * 
 * A interface to compute dividers of a number.
 *
 */
public interface Dividers {

    /**
     * Get dividers of a number.
     * 
     * @param number the number whose dividers you want to know
     * 
     * @return the list of dividers of the number specify
     */
    List<Integer> getDividers(Integer number);

}
