package it.unibo.javajump.model.states.ingame.gameutilities;

import it.unibo.javajump.model.GameModel;
import it.unibo.javajump.model.entities.character.Character;
import it.unibo.javajump.model.physics.MovementDirection;
import it.unibo.javajump.model.states.gameover.GameOverState;

import static it.unibo.javajump.utility.Constants.NULL_DIRECTION;
import static it.unibo.javajump.utility.Constants.SCREEN_LEFT_MARGIN;

/**
 * utility class for the InGameState, specifies some useful static methods.
 */
public final class InGameUtilities {
    /**
     * Private constructor, to avoid instantiating this class.
     *
     * @throws AssertionError if the constructor is called
     */
    private InGameUtilities() {
        throw new AssertionError("This is a utility class, it should not be instantiated!");
    }

    /**
     * Converts an integer (-1, 0, +1) in a MovementDirection.
     *
     * @param dir -1 = left, 0 = none, +1 = right
     * @return the movement direction
     */
    public static MovementDirection convertIntToMovementDirection(final int dir) {
        if (dir < NULL_DIRECTION) {
            return MovementDirection.LEFT;
        } else if (dir > NULL_DIRECTION) {
            return MovementDirection.RIGHT;
        } else {
            return MovementDirection.NONE;
        }
    }

    /**
     * Applies the PacMan effect to the character, so that if the character
     * trespasses the screen border, it will appear on the other side.
     *
     * @param player      the character to apply the effect
     * @param screenWidth the width of the screen
     */
    public static void applyPacManEffect(final Character player, final int screenWidth) {
        if (player.getX() + player.getWidth() < SCREEN_LEFT_MARGIN) {
            player.setX(screenWidth);
        } else if (player.getX() > screenWidth) {
            player.setX(-player.getWidth());
        }
    }

    /**
     * Verifies if the game has to be set to GameOverState, with the condition
     * for losing being that the character goes below the bottom of the screen.
     *
     * @param model  the game model, used to get the current offset and the screen height
     * @param player the character to check
     */
    public static void checkGameOver(final GameModel model, final Character player) {
        final float offset = model.getCameraManager().getCurrentOffset();
        final float drawY = player.getY() - offset;
        if (drawY > model.getScreenHeight()) {
            model.setState(new GameOverState());
        }
    }
}
