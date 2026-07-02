package model.projectile;

/**
*
* Represents a projectile.
*
* @author Nicola Zamagni
*
*/
public abstract class AbstractProjectile implements Projectile {

   /**
    * Fragments the projectile in a certain number of fragments.
    *
    * @param numberOfFragments
    *            number of fragments
    */
   protected abstract void fragment(int numberOfFragments);

}
