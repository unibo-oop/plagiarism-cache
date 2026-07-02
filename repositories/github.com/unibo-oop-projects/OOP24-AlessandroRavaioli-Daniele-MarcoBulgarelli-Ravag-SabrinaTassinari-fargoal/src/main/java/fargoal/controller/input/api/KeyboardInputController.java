package fargoal.controller.input.api;

/**
 * Class that based on the inputs, changed the state of the variables
 * to let the player move/interact/do actions.
 */
public class KeyboardInputController implements InputController {

    private boolean isMoveUp;
    private boolean isMoveDown;
    private boolean isMoveLeft;
    private boolean isMoveRight;
    private boolean isInteracting;

    private boolean isUsingHealingPotion;
    private boolean isPlacingBeacon;
    private boolean isUsingTeleportSpell;
    private boolean isUsingShieldSpell;
    private boolean isUsingDriftSpell;
    private boolean isUsingRegenerationSpell;
    private boolean isUsingInvisibilitySpell;
    private boolean isUsingLightSpell;
    private boolean isTurningLight;

    /**
     * Constructor that sets all the local fields to false.
     */
    public KeyboardInputController() {
        isMoveDown = false;
        isMoveUp = false;
        isMoveLeft = false;
        isMoveRight = false;
        isInteracting = false;
        isUsingHealingPotion = false;
        isPlacingBeacon = false;
        isUsingTeleportSpell = false;
        isUsingShieldSpell = false;
        isUsingDriftSpell = false;
        isUsingRegenerationSpell = false;
        isUsingInvisibilitySpell = false;
        isUsingLightSpell = false;
        isTurningLight = false;
    }

    /** {@inheritDoc} */
    @Override
    public boolean isInteracting() {
        return isInteracting;
    }

    /** {@inheritDoc} */
    @Override
    public boolean isMoveDown() {
        return isMoveDown;
    }

    /** {@inheritDoc} */
    @Override
    public boolean isMoveLeft() {
        return isMoveLeft;
    }

    /** {@inheritDoc} */
    @Override
    public boolean isMoveRight() {
        return isMoveRight;
    }

    /** {@inheritDoc} */
    @Override
    public boolean isMoveUp() {
        return isMoveUp;
    }

    /** {@inheritDoc} */
    @Override
    public boolean isPlacingBeacon() {
        return isPlacingBeacon;
    }

    /** {@inheritDoc} */
    @Override
    public boolean isUsingDriftSpell() {
        return isUsingDriftSpell;
    }

    /** {@inheritDoc} */
    @Override
    public boolean isUsingHealingPotion() {
        return isUsingHealingPotion;
    }

    /** {@inheritDoc} */
    @Override
    public boolean isUsingInvisibilitySpell() {
        return isUsingInvisibilitySpell;
    }

    /** {@inheritDoc} */
    @Override
    public boolean isUsingLightSpell() {
        return isUsingLightSpell;
    }

    /** {@inheritDoc} */
    @Override
    public boolean isTurningLight() {
        return isTurningLight;
    }

    /** {@inheritDoc} */
    @Override
    public boolean isUsingRegenerationSpell() {
        return isUsingRegenerationSpell;
    }

    /** {@inheritDoc} */
    @Override
    public boolean isUsingShieldSpell() {
        return isUsingShieldSpell;
    }

    /** {@inheritDoc} */
    @Override
    public boolean isUsingTeleportSpell() {
        return isUsingTeleportSpell;
    } 

    /**
     * Method that set the local field {@link #isInteracting}
     * to true.
     */
    public void notifyInteracting() {
        isInteracting = true;
    }

    /**
     * Method that set the local field {@link #isInteracting}
     * to false.
     */
    public void notifyNoMoreInteracting() {
        isInteracting = false;
    }

    /**
     * Method that set the local field {@link #isMoveDown}
     * to true.
     */
    public void notifyMoveDown() {
        isMoveDown = true;
    }

    /**
     * Method that set the local field {@link #isMoveDown}
     * to false.
     */
    public void notifyNoMoreMoveDown() {
        isMoveDown = false;
    }

    /**
     * Method that set the local field {@link #isMoveLeft}
     * to true.
     */
    public void notifyMoveLeft() {
        isMoveLeft = true;
    }

    /**
     * Method that set the local field {@link #isMoveLeft}
     * to false.
     */
    public void notifyNoMoreMoveLeft() {
        isMoveLeft = false;
    }

    /**
     * Method that set the local field {@link #isMoveRight}
     * to true.
     */
    public void notifyMoveRight() {
        isMoveRight = true;
    }

    /**
     * Method that set the local field {@link #isMoveRight}
     * to false.
     */
    public void notifyNoMoreMoveRight() {
        isMoveRight = false;
    }

    /**
     * Method that set the local field {@link #isMoveUp}
     * to true.
     */
    public void notifyMoveUp() {
        isMoveUp = true;
    }

    /**
     * Method that set the local field {@link #isMoveUp}
     * to false.
     */
    public void notifyNoMoreMoveUp() {
        isMoveUp = false;
    }

    /**
     * Method that set the local field {@link #isPlacingBeacon}
     * to true.
     */
    public void notifyPlacingBeacon() {
        isPlacingBeacon = true;
    }

    /**
     * Method that set the local field {@link #isPlacingBeacon}
     * to false.
     */
    public void notifyNoMorePlacingBeacon() {
        isPlacingBeacon = false;
    }

    /**
     * Method that set the local field {@link #isUsingDriftSpell}
     * to true.
     */
    public void notifyUsingDriftSpell() {
        isUsingDriftSpell = true;
    }

    /**
     * Method that set the local field {@link #isUsingDriftSpell}
     * to false.
     */
    public void notifyNoMoreUsingDriftSpell() {
        isUsingDriftSpell = false;
    }

    /**
     * Method that set the local field {@link #isUsingHealingPotion}
     * to true.
     */
    public void notifyUsingHealingPotion() {
        isUsingHealingPotion = true;
    }

    /**
     * Method that set the local field {@link #isUsingHealingPotion}
     * to false.
     */
    public void notifyNoMoreUsingHealingPotion() {
        isUsingHealingPotion = false;
    }

    /**
     * Method that set the local field {@link #isUsingInvisibilitySpell}
     * to true.
     */
    public void notifyUsingInvisibilitySpell() {
        isUsingInvisibilitySpell = true;
    }

    /**
     * Method that set the local field {@link #isUsingInvisibilitySpell}
     * to false.
     */
    public void notifyNoMoreUsingInvisibilitySpell() {
        isUsingInvisibilitySpell = false;
    }

    /**
     * Method that set the local field {@link #isUsingLightSpell}
     * to true.
     */
    public void notifyUsingLightSpell() {
        isUsingLightSpell = true;
    }

    /**
     * Method that set the local field {@link #isUsingLightSpell}
     * to false.
     */
    public void notifyNoMoreUsingLightSpell() {
        isUsingLightSpell = false;
    }

    /**
     * Method that set the local field {@link #isTurningLight}
     * to true.
     */
    public void notifyTurnLight() {
        isTurningLight = true;
    }

    /**
     * Method that set the local field {@link #isTurningLight}
     * to false.
     */
    public void notifyNotTurnLight() {
        isTurningLight = false;
    }

    /**
     * Method that set the local field {@link #isUsingRegenerationSpell}
     * to true.
     */
    public void notifyUsingRegenerationSpell() {
        isUsingRegenerationSpell = true;
    }

    /**
     * Method that set the local field {@link #isUsingRegenerationSpell}
     * to false.
     */
    public void notifyNoMoreUsingRegenerationSpell() {
        isUsingRegenerationSpell = false;
    }

    /**
     * Method that set the local field {@link #isUsingShieldSpell}
     * to true.
     */
    public void notifyUsingShieldSpell() {
        isUsingShieldSpell = true;
    }

    /**
     * Method that set the local field {@link #isUsingShieldSpell}
     * to false.
     */
    public void notifyNoMoreUsingShieldSpell() {
        isUsingShieldSpell = false;
    }

    /**
     * Method that set the local field {@link #isUsingTeleportSpell}
     * to true.
     */
    public void notifyUsingTeleportSpell() {
        isUsingTeleportSpell = true;
    } 

    /**
     * Method that set the local field {@link #isUsingTeleportSpell}
     * to false.
     */
    public void notifyNoMoreUsingTeleportSpell() {
        isUsingTeleportSpell = false;
    } 

}
