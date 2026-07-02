package controller.entities;

import common.events.JumpEvent;

/**
 * Give the possibility to jump when the linked events is received.
 */
public interface Jumper {

    void handleJumpEvent(JumpEvent event);
}
