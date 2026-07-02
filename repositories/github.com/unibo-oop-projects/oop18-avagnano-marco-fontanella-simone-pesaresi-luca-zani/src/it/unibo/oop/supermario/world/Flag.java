package it.unibo.oop.supermario.world;

import com.badlogic.gdx.physics.box2d.Body;
/**Flag model interface.
 * */
public interface Flag {
    /**Set the fall of the flag.
     * @param flagBool if the flag has to fall 
    */
    void setFlagFalling(boolean flagBool);
    /**If flag is falling.
     * @return if flag is falling
    */
    boolean isFlagFalling();
    /**Get body of flag.
     * @return body
    */
    Body getBody();

}
