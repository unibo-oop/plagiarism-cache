package players;

/**
 * 
 * this class should remain very simple, it contains only two
 * variables meant to have only 1 value during the game.
 */
public interface Player {

    /**
     * 
     * @return name of player.
     */
    String getNome();

    /**
     * 
     * @return colore(as index in colors' list) of player.
     */
    int getColore();

}
