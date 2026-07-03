package view.Items;

import java.util.Map;
import java.util.Set;

import javafx.geometry.Pos;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import model.utilities.*;
import model.utilities.pawns.Pawn;
import utilities.Pair;
import utilities.Players;
import view.Chess;
import view.Utilities.Utilities;

/**
 * Classe that shows the pawns eaten during the game in real time
 * 
 * Author: Linda Farneti.
 *
 */
public class PawnsRemoved {

    private HBox removedPanel = new HBox();
    private static final PawnsRemoved PAWNS_REMOVED = new PawnsRemoved();
    private VBox blackPanel;
    private VBox whitePanel;

    /**
     * Constructor.
     */
    public PawnsRemoved() {
        blackPanel = new VBox();
        whitePanel = new VBox();
        blackPanel.setAlignment(Pos.TOP_LEFT);
        whitePanel.setAlignment(Pos.TOP_RIGHT);
        removedPanel.getChildren().addAll(blackPanel, whitePanel);
    }

    /**
     * Method that displays eaten pawns.
     * 
     * @param map of eaten pawns for each player
     */
    public void pawnsDisplay(final Map <Players, Set<Pair<Pawn, Integer>>> map) {
        resetPanel();
        for (Players p : map.keySet()) {
            String s = p.equals(Players.playerWhite) ? "w_" : "b_";
            for (Pair<Pawn, Integer> pw : map.get(p)) {
                String path = new String();
                path = "/Pawns/" + Chess.getLog().getPawnStyle() + "/" + s + pw.getX().getType() + ".png";
                for (int i = 0; i < pw.getY(); i++) {
                    ImageView image = new ImageView(new Image(PawnsRemoved.class.getResourceAsStream(path), Utilities.MENU_ICON, Utilities.MENU_ICON, true, true));
                    if (p.equals(Players.playerBlack)) {
                        blackPanel.getChildren().add(image);
                    }
                    else {
                        whitePanel.getChildren().add(image);
                    }
                }
            }
        }
    }

    /**
     * Method that implements singleton pattern.
     * 
     * @return PAWNS_REMOVED
     */
    public static PawnsRemoved getLog() {
        return PAWNS_REMOVED;
    }

    /**
     * Method that reset the panel of pawns eaten for each eaten.
     */
    public void resetPanel() {
        blackPanel.getChildren().clear();
        whitePanel.getChildren().clear();
    }

    /**
     * 
     * 
     * @return removedPanel: panel of eaten pawns
     */
    public HBox getPane() {
        removedPanel.setPrefWidth(110);
        removedPanel.setId("pawns-removed");
        return removedPanel;
    }

}
