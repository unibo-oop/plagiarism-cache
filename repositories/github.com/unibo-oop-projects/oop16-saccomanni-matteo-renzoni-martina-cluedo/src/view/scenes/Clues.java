package view.scenes;

import java.util.HashMap;
import java.util.Map;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.GridPane;
import model.cards.Card;

/**
 * This class represents the notebook for the clues.
 */
public class Clues extends ScrollPane {
    private static final int LABEL_W = 200;
    private static Map<Card, CheckBox> selections = new HashMap<Card, CheckBox>();

    /**
     * @param height
     *            the height of the pane
     * @param width
     *            the width of the pane
     */
    public Clues(final double height, final double width) {
        final GridPane innerPane = new GridPane();
        innerPane.setAlignment(Pos.CENTER);
        innerPane.setPadding(new Insets(10));
        innerPane.setHgap((width - LABEL_W) / 2);
        innerPane.setVgap(2);
        int i = 0;
        for (final Card card : Card.getAllCards()) {
            final Label label = (new Label(card.toString()));
            innerPane.add(label, 0, i);
            final CheckBox cb = new CheckBox();
            innerPane.add(cb, 1, i);
            i++;
            selections.put(card, cb);
        }
        this.setContent(innerPane);
        this.setMinHeight(height);
        this.setMaxHeight(height);
        this.setVbarPolicy(ScrollPane.ScrollBarPolicy.AS_NEEDED);
    }

    /**
     * Set notes.
     * 
     * @param n
     *            notes saved before.
     */
    public void setNotes(final Map<Card, Boolean> n) {
        for (final Card c : selections.keySet()) {
            if (!n.isEmpty()) {
                selections.get(c).setSelected(n.get(c));
            } else {
                selections.get(c).setSelected(false);
            }
        }
    }

    /**
     * Get notes.
     * 
     * @return the new map with data.
     */
    public static Map<Card, Boolean> getNotes() {
        final Map<Card, Boolean> newMap = new HashMap<>();
        for (final Card c : selections.keySet()) {
            newMap.put(c, selections.get(c).isSelected());
        }
        return newMap;
    }
}