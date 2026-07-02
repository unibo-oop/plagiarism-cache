package model.collidable.tank.shape;

import java.util.List;

import org.apache.commons.math3.geometry.euclidean.twod.Vector2D;

/**
*
* Represents a tank shape.
*
* @author Nicola Tamburini
*
*/
public interface TankShape {

   /**
    *
    * Returns the outline points of the tank.
    *
    * @return outline points of the tank
    */
   List<Vector2D> getOutlinePoints();

   /**
    *
    * Returns the points of the turret.
    *
    * @return points of the turret
    */
   List<Vector2D> getTurretPoints();

   /**
    *
    * Returns the explosion radius of the tank.
    *
    * @return explosion radius of the tank, in meters
    */
   double getExplosionRadius();

}

