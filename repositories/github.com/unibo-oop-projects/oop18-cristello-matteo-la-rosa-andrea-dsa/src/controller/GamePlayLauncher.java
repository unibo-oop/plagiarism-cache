package controller;

import java.io.IOException;
import java.util.Map;

/**
 * This is the launcher of the board in play.
 * 
 */
public class GamePlayLauncher {
    /**
     * This constructor create a game in play with all the needed parameters to start a complete match.
     * 
     * @param templateEnum
     *                             this is the Template of the tileline.
     * @param nameColorPlayers
     *                             this is a map of name and colors to create the color player.
     * @throws IOException this is a exception that occurs when ...
     */
    public GamePlayLauncher(final String templateEnum, final Map<String, String> nameColorPlayers) throws IOException {
        new DSAControllerImpl(nameColorPlayers, templateEnum);

    }

}
