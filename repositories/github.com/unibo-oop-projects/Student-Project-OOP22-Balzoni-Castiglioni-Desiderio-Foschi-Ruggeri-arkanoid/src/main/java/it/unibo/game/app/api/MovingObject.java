package it.unibo.game.app.api;

/*** interface that models the characteristics of a mobile object. */
public interface MovingObject extends GameObject {
  /**
   * assigns the physics of the object.
   * 
   * @param phsycs
   */
  void setPhysics(Physics phsycs);

  /**
   * 
   * @return the physics of the object.
   */
  Physics getPhysics();

  /**
   * set dimension of the obj.
   * 
   * @param d
   */
  @Override
  void setDimension(Dimension d);

  /**
   * @return speed of obj.
   */
  Speed getSpeed();

  /**
   * set a speed.
   * 
   * @param vel
   */
  void setSpeed(Speed vel);
}
