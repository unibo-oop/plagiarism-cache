package controls;

import java.awt.event.KeyListener;
import gamegraphics.ViewGame;
import movements.Movements;
import speed.Speed;

/**
 * Interface for the TetrisKeyListener.
 */
public interface TetrisKeyListener extends KeyListener {
    /**
     * Sets the Movements in the KeyListenerLogics.
     * 
     * @param movements : Movements Object
     */
    void setMovements(Movements movements);

    /**
     * Sets the Speed in the KeyListenerLogics.
     * 
     * @param speed : Speed Object
     */
    void setSpeed(Speed speed);

    /**
     * Sets the ViewGame in the KeyListenerLogics.
     * 
     * @param view : ViewGame Object
     */
    void setView(ViewGame view);
}
