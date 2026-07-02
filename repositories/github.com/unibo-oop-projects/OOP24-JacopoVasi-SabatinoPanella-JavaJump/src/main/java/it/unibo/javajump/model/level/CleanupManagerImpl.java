package it.unibo.javajump.model.level;

import it.unibo.javajump.model.GameModel;
import it.unibo.javajump.model.entities.GameObject;
import it.unibo.javajump.model.entities.collectibles.Coin;
import it.unibo.javajump.model.entities.collectibles.CoinState;
import it.unibo.javajump.model.entities.platforms.BreakablePlatform;

import java.util.ArrayList;
import java.util.List;

import static it.unibo.javajump.utility.Constants.CLEAN_UP_MARGIN_OFFSET;

/**
 * The implementation of CleanupManager interface.
 */
public final class CleanupManagerImpl implements CleanupManager {

    /**
     * {@inheritDoc} The method checks the position of the GameObjects and removes them if they are out of the screen.
     * In case of Coin, if the Coin is in FINISHED state, it can be removed. The removal of all object is handled
     * safely, with a safe copy of the GameObjects list to remove, and then removing them.
     */
    @Override
    public void cleanupObjects(final GameModel model) {
        final List<GameObject> toRemove = new ArrayList<>();

        final float cameraOffset = model.getCameraManager().getCurrentOffset();
        final float screenH = model.getScreenHeight();

        for (final GameObject go : model.getGameObjects()) {
            if (go instanceof Coin c && c.getState() == CoinState.FINISHED) {
                    toRemove.add(c);
                    continue;
            }

            if (go instanceof BreakablePlatform bp && bp.isBroken() && bp.isFinished()) {
                    toRemove.add(bp);
                    continue;
            }

            final float drawY = go.getY() - cameraOffset;
            if (drawY > screenH + CLEAN_UP_MARGIN_OFFSET) {
                toRemove.add(go);
            }

        }
        model.getGameObjects().removeAll(toRemove);
    }
}
