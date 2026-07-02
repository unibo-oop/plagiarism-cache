package file.data.input;

import java.awt.Component;
import java.util.Optional;

import data.Languages;
import model.world.initialization.Modality;

/**
 * Interface that will be implemented classes that uploads data passed to file by argument.
 *
 */
public interface FileSet {

    /**
     * adds fields maxEnergy maxSunLight heightWorld wedthWorld upDateFrame colorFilterfinal to a file.
     * @param maxEnergy energy max of a cell
     * @param maxSunLight energy of Sun max
     * @param heightWorld height of World 
     * @param widthWorld width of World
     * @param upDateFrame number of frames after which the graphical user interface must be updated
     * @param colorFilterfinal value of color filter
     */
    /*void addtoFile(int maxEnergy, int maxSunLight, int heightWorld, int widthWorld, int upDateFrame,
            int colorFilterfinal);*/

    /**
     * adds fields maxEnergy maxSunLight heightWorld wedthWorld upDateFrame colorFilterfinal to a file.
     * @param maxEnergy energy max of a cell
     * @param maxSunLight energy of Sun max
     * @param heightWorld height of World
     * @param widthWorld width of World
     * @param upDateFrame number of frames after which the graphical user interface must be updated
     * @param colorFilter value of color filter
     * @param hue hue of color filter
     */
    /*void addtoFile(int maxEnergy, int maxSunLight, int heightWorld, int widthWorld, int upDateFrame, int colorFilter,
            Optional<Float> hue);*/

    /**
     * Adds the language field, with which you want to translate the program, to a file.
     * @param languages chosen to translate the program of type Languages
     */
    void addtoFile(Languages languages);


    void addtoFile(int maxEnergy, int maxSunLight, int sizeGenoma, int maxAge, int worldHSize, int worldWSize, int upDateview,
             int filterValue, Modality mod, float sunPenetration, float mineralDepth, Optional<Float> hue);


    void addtoFile(int maxEnergy, int maxSunLight, int sizeGenoma, int maxAge, int worldHSize, int worldWSize, int upDateview,
             int filterValue, Modality mod, float sunPenetration, float mineralDepth);
}
