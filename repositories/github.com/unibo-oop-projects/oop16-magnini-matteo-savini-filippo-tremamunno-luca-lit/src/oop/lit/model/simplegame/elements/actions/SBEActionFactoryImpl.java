package oop.lit.model.simplegame.elements.actions;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import oop.lit.model.Action;
import oop.lit.model.simplegame.SimplePlayerManager;
import oop.lit.model.util.permission.ActionsManager;

/**
 * A SBEActionFactory implementation.
 */
public class SBEActionFactoryImpl implements SBEActionFactory {
    /**
     * 
     */
    private static final long serialVersionUID = -6448003718610801011L;
    private final SimplePlayerManager pManager;
    /**
     * @param pManager
     *      this game player manager.
     */
    public SBEActionFactoryImpl(final SimplePlayerManager pManager) {
        this.pManager = pManager;
    }
    @Override
    public List<Action> getPermissionEditActions(final ActionsManager manager) {
        if (!pManager.getPlayingPlayer().isPresent() || !pManager.getPlayingPlayer().get().isGM()) {
            return Collections.emptyList();
        }
        final List<Action> res = new ArrayList<>();
        pManager.getPlayers().stream().filter(player -> !player.isGM()).forEach(player -> {
            res.add(manager.getPermissionEditAction(player));
        });
        return res;
    }
}
