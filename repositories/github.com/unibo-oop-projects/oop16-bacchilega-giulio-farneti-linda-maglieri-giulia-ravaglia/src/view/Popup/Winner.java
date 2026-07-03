package view.Popup;

import utilities.Players;
import view.Chess;
/**
 * 
 * Author : Giulia Maglieri.
 *
 */
public final class Winner {
    /**
     * 
     */
     private Winner() { }
    /**
     * 
     * @return String
     */
    public static String winner() {
        if (Chess.getLog().getActualPlayer().equals(Players.playerWhite)) {
            return "Black";
        } else {
            return "White";
        }
    }

}
