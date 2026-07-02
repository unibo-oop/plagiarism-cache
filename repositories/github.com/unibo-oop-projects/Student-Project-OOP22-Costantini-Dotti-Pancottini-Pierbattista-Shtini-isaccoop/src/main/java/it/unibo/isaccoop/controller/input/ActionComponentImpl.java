/**
 *
 */
package it.unibo.isaccoop.controller.input;

import it.unibo.isaccoop.core.GameLoop;

/**
 * Class that execute the update of the ActionController on the level.
 */
public class ActionComponentImpl implements ActionComponent {

    /**
     * {@inheritDoc}
     */
    @Override
    public void update(final ActionController ctrl, final GameLoop loop) {
        if (ctrl.isEsc()) {
            loop.setPause(!loop.isPause());
        } else if (!loop.isPause() && ctrl.isNextRoom()) {
            loop.getLevel().moveToNextRoom();
        } else if (!loop.isPause() && ctrl.isPrevRoom()) {
            loop.getLevel().moveToPreviousRoom();
        }
    }

}
