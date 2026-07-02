package casim.ui.components.menu;

import javafx.scene.control.Button;

/**
 * An abstract button for {@link AbstractMenu}.
 * 
 * @param <T> the type of the data contained in the button.
 */
public abstract class AbstractMenuButton<T> extends Button {
    private final T data;
    private final AbstractMenu menu;

    /**
     * Build a menu button with it's data and label.
     * 
     * @param text the label of the button.
     * @param data the data of the button.
     * @param menu the menu of the button.
     */
    public AbstractMenuButton(final String text, final T data, final AbstractMenu menu) {
        super(text);
        this.data = data;
        this.menu = menu;
        this.setOnAction(e -> this.onClick());
    }

    /**
     * The function called when the menu button is pressed.
     */
    public abstract void onClick();

    /**
     * Return the data that the button holds.
     * 
     * @return the button data.
     */
    protected T getData() {
        return this.data;
    }

    /**
     * Return the menu containing the button.
     * 
     * @return the menu where the button is.
     */
    protected AbstractMenu getMenu() {
        return this.menu;
    }
}
