package cubevisual;

import javafx.scene.paint.Color;

/**
 * This interface is created for code extension and scalability, 
 * we can create enumerators containing center coordinates for every cube dimensions.
 */
public interface CentersCoords {

    /**
     * Row getter.
     * @return Row position of the selected face.
     */
    int getRows();

    /**
     * Column getter.
     * @return Column position of the selected face.
     */
    int getCols();

    /**
     * Depth getter.
     * @return Depth position of the selected face.
     */
    int getDepth();

    /**
     * Color getter.
     * @return Color of the selected face.
     */
    Color getColor();
}

