package it.tbt.model.menu.impl;

import it.tbt.model.menu.api.MenuSelect;

import java.util.NavigableMap;

/**
 * The {@code MenuSelect} class represents a selectable menu with options.
 * It extends the {@link AbstractMenuItem} class and implements the {@link it.tbt.model.menu.api.MenuSelect} interface.
 *
 * @param <I> the type of the menu options
 */
public abstract class AbstractMenuSelect<I> extends AbstractMenuItem implements MenuSelect {

    private final NavigableMap<String, I> options;
    private String selectedOptionIndex;

    /**
     * Creates a new instance of {@code MenuSelect} with the specified text and options.
     *
     * @param text    the text of the menu
     * @param options the map of options
     */
    public AbstractMenuSelect(final String text, final NavigableMap<String, I> options) {
        super(text);
        // Add exception handling code for invalid arguments here
        if (options == null || options.isEmpty()) {
            throw new IllegalArgumentException("Options cannot be null or empty");
        }
        this.options = options;
        selectedOptionIndex = options.keySet().iterator().next();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void setSelectedOptionIndex(final String key) {
        // Add exception handling code for invalid argument here
        if (!options.containsKey(key)) {
            throw new IllegalArgumentException("Invalid option key: " + key);
        }
        this.selectedOptionIndex = String.copyValueOf(key.toCharArray());
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String getSelectedOptionIndex() {
        return this.selectedOptionIndex;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public NavigableMap<String, I> getOptions() {
        return options;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String getLabel() {
        return selectedOptionIndex;
    }
}
