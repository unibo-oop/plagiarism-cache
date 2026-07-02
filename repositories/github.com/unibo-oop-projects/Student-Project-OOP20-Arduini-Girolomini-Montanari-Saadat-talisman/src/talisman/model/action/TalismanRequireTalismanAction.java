package talisman.model.action;

import java.util.Objects;

import talisman.Controllers;

import talisman.model.cards.TalismanDeckFactory;
import talisman.model.character.CharacterModelImpl;

/**
 * An action that checks if the player has a specific item.
 * 
 * @author Alberto Arduini
 *
 */
public class TalismanRequireTalismanAction extends TalismanActionImpl {
    private static final long serialVersionUID = 3944869345872798127L;
    private static final String DESCRIPTION_FORMAT = "If you have a talisman then: %s; " + System.lineSeparator()
            + "otherwise: %s";

    private final TalismanAction successAction;
    private final TalismanAction failedAction;

    public TalismanRequireTalismanAction(final TalismanAction successAction, final TalismanAction failedAction) {
        this.successAction = Objects.requireNonNull(successAction);
        this.failedAction = Objects.requireNonNull(failedAction);
        this.successAction.setActionEndedListener(this::actionEnded);
        this.failedAction.setActionEndedListener(this::actionEnded);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String getDescription() {
        return String.format(TalismanRequireTalismanAction.DESCRIPTION_FORMAT, this.successAction.getDescription(),
                this.failedAction.getDescription());
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void apply() {
        final CharacterModelImpl character = Controllers.getCharactersController().getCurrentPlayer()
                .getCurrentCharacter();
        if (character.getInventory().listCards().stream().anyMatch(c -> c.getName() == TalismanDeckFactory.TALISMAN)) {
            this.successAction.apply();
        } else {
            this.failedAction.apply();
        }
    }
}
