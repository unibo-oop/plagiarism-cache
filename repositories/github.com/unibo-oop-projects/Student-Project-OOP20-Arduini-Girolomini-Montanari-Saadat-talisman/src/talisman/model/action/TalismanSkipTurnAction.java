package talisman.model.action;

import talisman.Controllers;

/**
 * Models an action that skips to the next player turn.
 * 
 * @author Alberto Arduini
 *
 */
public class TalismanSkipTurnAction extends TalismanActionImpl {
    private static final long serialVersionUID = 7490593320775050948L;
    private static final String DESCRIPTION = "Skip your turn";

    /**
     * {@inheritDoc}
     */
    @Override
    public String getDescription() {
        return TalismanSkipTurnAction.DESCRIPTION;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void apply() {
        Controllers.getChoiceController().skipTurn();
        this.actionEnded();
    }
}
