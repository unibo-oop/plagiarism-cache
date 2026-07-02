package bzzbomber.game;

/**
 * Represents the starting point of the game.
 */
public final class Launcher {

    private Launcher() {

    }

    /**
     * Main method of game.
     * 
     * @param args
     *            The main starting method of the game
     */
    public static void main(final String[] args) {
        final Game game = new Game();
        game.start();
    }
}
