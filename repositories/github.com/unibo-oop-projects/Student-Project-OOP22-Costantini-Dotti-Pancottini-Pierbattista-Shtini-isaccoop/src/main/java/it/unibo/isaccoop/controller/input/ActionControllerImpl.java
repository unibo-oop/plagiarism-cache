package it.unibo.isaccoop.controller.input;
/**
 * Class that manages the pressure of the buttons,
 * saving only the last key pressed until it is used.
 */
public class ActionControllerImpl implements ActionController {

    private final int keyEsc;
    private final int keyNextRoom;
    private final int keyPrevRoom;

    private boolean isEsc;
    private boolean isNextRoom;
    private boolean isPrevRoom;

    /**
     * ActionControllerImpl Constructor.
     * @param keyEsc
     * @param keyNextRoom
     * @param keyPrevRoom
     */
    public ActionControllerImpl(final int keyEsc, final int keyNextRoom, final int keyPrevRoom) {
        super();
        this.keyEsc = keyEsc;
        this.keyNextRoom = keyNextRoom;
        this.keyPrevRoom = keyPrevRoom;
    }

    /**
     * Method to update key state if a certain key has been pressed.
     * @param keyCode
     */
    public void notifyKeyPressed(final int keyCode) {
        this.setAllFalse();
        if (keyCode == this.keyEsc) {
            this.isEsc = true;
        } else if (keyCode == this.keyNextRoom) {
            this.isNextRoom = true;
        } else if (keyCode == this.keyPrevRoom) {
            this.isPrevRoom = true;
        }
    }

    /**
     * Clears all fields that keep track of the last key pressed.
     */
    private void setAllFalse() {
        this.isEsc = false;
        this.isNextRoom = false;
        this.isPrevRoom = false;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean isEsc() {
        if (this.isEsc) {
            this.setAllFalse();
            return true;
        }
        return false;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean isNextRoom() {
        if (this.isNextRoom) {
            this.setAllFalse();
            return true;
        }
        return false;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean isPrevRoom() {
        if (this.isPrevRoom) {
            this.setAllFalse();
            return true;
        }
        return false;
    }
}
