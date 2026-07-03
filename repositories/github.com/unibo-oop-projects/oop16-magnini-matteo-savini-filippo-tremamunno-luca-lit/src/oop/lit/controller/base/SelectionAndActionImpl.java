package oop.lit.controller.base;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;

import oop.lit.model.Action;
import oop.lit.model.GameElementModel;
import oop.lit.model.GameModel;
import oop.lit.model.PlayerModel;
import oop.lit.model.SelectableElementGroupModel;
import oop.lit.util.IllegalInputException;
import oop.lit.util.InputRequest;
import oop.lit.view.ViewRequests;

/**
 * An implementation for SelectionAndAction.
 */
public class SelectionAndActionImpl implements SelectionAndAction {
    private static final String UNEXPECTED_ERROR = "An unexpected error has occured while trying to perform provided action";
    private static final String ACTION_NOT_PERFORMABLE = "This action can't be performed right now";
    private static final String LOGGER_TYPE = "Controller";
    private final GameModel gm;
    private final ViewRequests view;
    private final EditSelectionStrategy primaryESS;
    private final EditSelectionStrategy secondaryESS;

    /**
     * Constructor.
     * 
     * @param gm
     *            GameModel.
     * @param view
     *            the view.
     * @param primaryESS
     *            a EditSelectionStrategy.
     * @param secondaryESS
     *            a EditSelectionStrategy.
     */
    public SelectionAndActionImpl(final GameModel gm, final ViewRequests view, final EditSelectionStrategy primaryESS,
            final EditSelectionStrategy secondaryESS) {
        this.gm = gm;
        this.view = view;
        this.primaryESS = primaryESS;
        this.secondaryESS = secondaryESS;
    }

    @Override
    public void clearSelection(final SelectableElementGroupModel segm) {
        segm.clearSelection();
    }

    @Override
    public void forceClearSelection() {
        gm.getBoard().clearSelection();
    }

    @Override
    public void editSelection(final GameElementModel gem, final SelectableElementGroupModel segm,
            final boolean keepOldSelection) {
        if (gm.getPlayingPlayer().isPresent()) {
            this.primaryESS.editSelection(gem, segm, keepOldSelection);
        }
    }

    @Override
    public List<Action> editSelectionAndGetAction(final Optional<GameElementModel> gem,
            final SelectableElementGroupModel segm) {
        if (gm.getPlayingPlayer().isPresent()) {
            if (gem.isPresent() && !segm.getSelected().contains(gem.get())) {
                this.secondaryESS.editSelection(gem.get(), segm, false);
            }
            LogManager.getLogger(LOGGER_TYPE).log(Level.INFO, "Actions showed");
            return segm.getSelectedActions(gm.getPlayingPlayer().get(), gm.getActivePlayers());
        }
        return Collections.emptyList();
    }

    @Override
    public void performMainAction(final GameElementModel gem) {
        if (gm.getPlayingPlayer().isPresent()
                && gem.getMainAction(gm.getPlayingPlayer().get(), gm.getPlayers()).isPresent()
                && gem.getMainAction(gm.getPlayingPlayer().get(), gm.getPlayers()).get().canBePerformed()) {
            performAction(gem.getMainAction(gm.getPlayingPlayer().get(), gm.getPlayers()).get());
        }
    }

    @Override
    public void performAction(final Action action) {
        try {
            final List<InputRequest<?>> actionRequests = action.getRequests(this.view.getIRFactory());
            if (actionRequests.isEmpty()) {
                // Azione che non ha bisogno di input
                try {
                    final Optional<PlayerModel> playingPlayer = gm.getPlayingPlayer();
                    action.perform();
                    this.logAction(playingPlayer, action);
                } catch (IllegalInputException e) {
                    this.view.displayError(UNEXPECTED_ERROR);
                }
            } else {
                boolean actionPerformed = false;
                boolean playerConfirmed;
                do {
                    playerConfirmed = this.view.askInput(actionRequests);
                    if (playerConfirmed) {
                        try {
                            final Optional<PlayerModel> playingPlayer = gm.getPlayingPlayer();
                            action.perform();
                            this.logAction(playingPlayer, action);
                            actionPerformed = true;
                        } catch (IllegalInputException e) {
                            this.view.displayError(e.getMessage());
                        } catch (IllegalStateException e) {
                            this.view.displayError("Selected action can't be performed");
                            playerConfirmed = true;
                        }
                    }
                } while (playerConfirmed && !actionPerformed);
            }
        } catch (IllegalStateException e) {
            this.view.displayError(ACTION_NOT_PERFORMABLE);
        }
    }

    private void logAction(final Optional<PlayerModel> playingPlayer, final Action action) {
        if (playingPlayer.isPresent()) {
            LogManager.getLogger(LOGGER_TYPE).log(Level.INFO,
                    "Player " + playingPlayer.get().getName() + " performed action "
                            + action.getLabel());
        } else {
            LogManager.getLogger(LOGGER_TYPE).log(Level.INFO,
                    "Action " + action.getLabel() + " performed.");
        }
    }
}
