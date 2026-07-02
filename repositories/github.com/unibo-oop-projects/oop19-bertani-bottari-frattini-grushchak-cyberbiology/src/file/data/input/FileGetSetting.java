package file.data.input;

import color.filter.Filters;
import model.world.initialization.Modality;

/**
 * Interface that will be implemented classes that loads from file the language set for the translation of the program.
 * @exception FileNotFoundException
 * @exception IOException
 *
 */
public interface FileGetSetting {

    /**
     * @return enum corresponding to the chosen filter
     */
    Filters getColorFilter();

    /**
     * @return the the world height
     */
    int getHeightWorld();

    /**
     * @return the hue of the filter 
     */
    float getHue();

    /**
     * @return the maxEnergy of the cell
     */
    int getMaxEnery();

    /**
     * @return the maxSunLight of the cell
     */
    int getSunLight();
    
    /**
     * @return number of frames after which the graphical user interface must be updated
     */
    int getUpDateFrame();
    
    /**
     * @return the the world width
     */
    int getWidthWorld();

    float getMineralDepth();

    float getSunPenetration();

    int getMaxAge();

    int getSizeGenoma();

    Modality getMod();

}
