package talisman.model.cards;

import java.util.Collection;

import talisman.model.action.TalismanAction;

public class CardImpl implements Card {
    private final String name;
    private final String text;
    private final String imagepath;
    private final CardType type;
    private final Collection<TalismanAction> actions;

    public CardImpl(final String name, final String text, final String imagepath, final CardType type, final Collection<TalismanAction> actions) {
        this.name = name;
        this.text = text;
        this.imagepath = imagepath;
        this.type = type;
        this.actions = actions;
    }
    /**
     * 
     * @return The name of the card.
     */
    public String getName() {
        return this.name;
    }
    /**
     * {@inheritDoc}
     */
    public String getText() {
        return this.text;
    }

    /**
     * {@inheritDoc}
     */
    public CardType getType() {
        return this.type;
    }
    /**
     * 
     * @return a list of actions the card does.
     */
    public Collection<TalismanAction> getActions() {
        return this.actions;
    }
    /**
     * 
     * @return The path of the image of the card.
     */
    public String getImagePath() {
        return imagepath;
    }
    public static Card createCard(final String name, final String text, final String imagepath, final CardType type, final Collection<TalismanAction> actions) {
        return new CardImpl(name, text, imagepath, type, actions);
    }
}
