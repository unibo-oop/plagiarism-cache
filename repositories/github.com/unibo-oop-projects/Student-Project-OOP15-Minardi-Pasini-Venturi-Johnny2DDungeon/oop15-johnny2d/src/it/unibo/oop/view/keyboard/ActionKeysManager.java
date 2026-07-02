package it.unibo.oop.view.keyboard;

import java.util.ArrayList;
import java.util.List;

import it.unibo.oop.utilities.Action;

/**
 * Class for a keyboard manager which manages ActionKeys.
 */
public final class ActionKeysManager extends AbstractKeysManager<ActionKey, Action> {

    private static final int LIMIT = 0;

    @Override
    public synchronized Action processKeys() {
        final List<ActionKey> tmpList = new ArrayList<>();
        Action out = Action.NONE;

        this.processPressed(LIMIT, tmpList);
        this.processTyped(LIMIT, tmpList);
        if (!tmpList.isEmpty()) {
            out = tmpList.get(0).getAction();
        }
        this.removeKey(ActionKey.PAUSE);
        return out;
    }
}