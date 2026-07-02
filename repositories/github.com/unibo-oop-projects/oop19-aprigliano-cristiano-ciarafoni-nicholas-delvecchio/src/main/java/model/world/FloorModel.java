package model.world;

/**
 * This interface has to model the floor object model.
 */
public interface FloorModel {

    /**
     *
     * @return the image path of the Floor.
     */
   String getFloorImgPath();

    /**
     * .
     * @return X Coordinate of the Floor.
     */
   double getFloorPosX();

    /**
     *
     * @return Y Coordinate of the Floor.
     */
   double getFloorPosY();

    /**
     *
     * @return Floor's Width.
     */
   double getFloorWidth();

    /**
     *
     * @return Floor's Height.
     */
   double getFloorHeight();

}
