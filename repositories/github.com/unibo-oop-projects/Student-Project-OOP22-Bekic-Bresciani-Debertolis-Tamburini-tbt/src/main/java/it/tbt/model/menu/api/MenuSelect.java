package it.tbt.model.menu.api;

import it.tbt.model.command.api.Command;

import java.util.Map;

/**
 * The {@code MenuSelect} interface represents a selectable menu with multiple options.
 *
 * @param <I> the type of the options in the menu
 */
public interface MenuSelect<I> {
    /**
     * Returns a map of options in the menu, where each option is associated with a unique key.
     *
     * @return a map of options in the menu
     */
    Map<String, I> getOptions();

    /**
     * Returns the label of the menu select.
     *
     * @return the label of the menu select
     */
    String getLabel();

    /**
     * Returns the button command to select the next option in the menu.
     *
     * @return the button command to select the next option
     */
    Command nextOption();

    /**
     * Returns the button command to select the previous option in the menu.
     *
     * @return the button command to select the previous option
     */
    Command previousOption();

    /**
     * Sets the selected option index in the menu.
     *
     * @param key the key of the selected option
     */
    void setSelectedOptionIndex(String key);

    /**
     * Returns the key of the currently selected option in the menu.
     *
     * @return the key of the selected option
     */
    String getSelectedOptionIndex();
}
