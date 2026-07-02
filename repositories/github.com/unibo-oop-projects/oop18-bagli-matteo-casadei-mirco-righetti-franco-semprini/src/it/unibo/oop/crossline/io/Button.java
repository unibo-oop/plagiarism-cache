package it.unibo.oop.crossline.io;

import java.util.Optional;

/**
 * The class of a generic button.
 */
public class Button {

    /**
     * The keyCode mapped to this button.
     */
    private int keyCode;
    /**
     * The name of the button.
     */
    private String name;
    /**
     * The action mapped to this button.
     */
    private Runnable action;

    /**
     * Constructor of Button.
     * 
     * @param keyCode the keyCode
     * @param name    the name
     * @param action  the action
     */
    public Button(final int keyCode, final String name, final Runnable action) {
        this.keyCode = keyCode;
        this.name = name;
        this.action = action;
    }

    /**
     * @return the action
     */
    public Runnable getAction() {
        return action;
    }

    /**
     * @param action the action to set
     */
    public void setAction(final Runnable action) {
        this.action = action;
    }

    /**
     * @return the keyCode
     */
    public int getKeyCode() {
        return keyCode;
    }

    /**
     * @param keyCode the keyCode to set
     */
    public void setKeyCode(final int keyCode) {
        this.keyCode = keyCode;
    }

    /**
     * @return the name
     */
    public Optional<String> getName() {
        return Optional.ofNullable(name);
    }

    /**
     * @param name the name to set
     */
    public void setName(final String name) {
        this.name = name;
    }

    /**
     * ToString method.
     */
    @Override
    public String toString() {
        return "Button [" + "keyCode=" + this.getKeyCode() + ", action=" + this.getAction() + "]";
    }

    /**
     * HashCode method.
     */
    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((action == null) ? 0 : action.hashCode());
        result = prime * result + keyCode;
        result = prime * result + ((name == null) ? 0 : name.hashCode());
        return result;
    }

    /**
     * Equals method.
     */
    @Override
    public boolean equals(final Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Button other = (Button) obj;
        if (action == null) {
            if (other.action != null) {
                return false;
            }
        } else if (!action.equals(other.action)) {
            return false;
        }
        if (keyCode != other.keyCode) {
            return false;
        }
        if (name == null) {
            if (other.name != null) {
                return false;
            }
        } else if (!name.equals(other.name)) {
            return false;
        }
        return true;
    }
}
