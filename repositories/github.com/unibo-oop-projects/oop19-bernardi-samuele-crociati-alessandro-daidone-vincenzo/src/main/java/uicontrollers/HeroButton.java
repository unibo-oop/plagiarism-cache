package uicontrollers;

import javafx.scene.control.Button;

/**
 * Rappresents a button dedicated to an hero during the hero picking stage.
 */
public final class HeroButton extends Button {

    private int identifier;

    /**
     * Getter of the button identifier.
     * 
     * @return the identifier of the button
     */
    public int getIdentifier() {
        return this.identifier;
    }

    /**
     * Setter of the button identifier.
     * 
     * @param newIdentifier
     *            new identifier of the button
     */
    public void setIdentifier(final int newIdentifier) {
        this.identifier = newIdentifier;
    }

}
