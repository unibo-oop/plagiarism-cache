package it.unibo.monoopoly.view.panel.game;

import java.awt.Color;
import java.awt.Font;
import java.awt.GridLayout;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import javax.swing.BorderFactory;
import javax.swing.JPanel;
import javax.swing.JTextArea;

import org.apache.commons.lang3.tuple.Triple;

import it.unibo.monoopoly.controller.data.impl.ViewUpdateDTO;
import it.unibo.monoopoly.model.player.api.Player;
import it.unibo.monoopoly.view.panel.UpdatablePanel;

/**
 * {@link JPanel} where the players and their financial situation will be
 * displayed,
 * as well as the current {@link Player}.
 */
public final class VisualizePlayerPanel extends JPanel implements UpdatablePanel {

    private static final long serialVersionUID = 1L;
    /**
     * The {@link List} of {@link JTextArea} of the {@link JPanel}.
     */
    private final List<JTextArea> textList = new LinkedList<>();
    private static final double TEXT_RESIZE = 0.035;
    private static final Color GREEN_MONOPOLY = new Color(0xecfcf4);

    /**
     * Constructor of the class.
     * 
     * @param mainFrameHeight the height of the main frame
     * @param firstPlayer     the player that starts the game
     * @param initializedList the list of players with their respective color, name,
     *                        and initial money.
     */
    public VisualizePlayerPanel(final int mainFrameHeight, final String firstPlayer,
            final List<Triple<String, Integer, Color>> initializedList) {
        super();
        setLayout(new GridLayout(initializedList.size() * 2 + 2, 1));
        this.textList.add(new JTextArea("E' il turno di:"));
        this.textList.getLast().setBackground(GREEN_MONOPOLY);
        this.textList.add(new JTextArea(firstPlayer));
        this.textList.getLast().setBackground(GREEN_MONOPOLY);
        for (final Triple<String, Integer, Color> triple : initializedList) {
            this.textList.add(new JTextArea(triple.getLeft()));
            this.textList.getLast().setBackground(triple.getRight());
            this.textList.add(new JTextArea(triple.getMiddle() + " €"));
            this.textList.getLast().setBackground(triple.getRight());
        }
        for (final var text : this.textList) {
            text.setEnabled(false);
            text.setFont(new Font("Arial", Font.PLAIN, (int) (TEXT_RESIZE * mainFrameHeight)));
            text.setDisabledTextColor(Color.BLACK);
            add(text);
        }
        for (int i = 0; i < this.textList.size(); i++) {
            if (i % 2 == 0) {
                this.textList.get(i).setBorder(BorderFactory.createMatteBorder(2, 4, 1, 4, Color.BLACK));
            } else {
                this.textList.get(i).setBorder(BorderFactory.createMatteBorder(1, 4, 2, 4, Color.BLACK));
            }
        }
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void update(final ViewUpdateDTO updateData) {
        this.textList.get(1).setText(updateData.actualPlayer());
        for (int i = 2; i < this.textList.size(); i++) {
            if (i % 2 == 0) {
                this.textList.get(i + 1)
                        .setText(updateData.playersMoney().get(this.textList.get(i).getText()) + "€");
            }
        }
        this.textList.stream()
                .filter(t -> this.textList.indexOf(t) % 2 == 0 && this.textList.indexOf(t) > 1)
                .filter(t -> !keysList(updateData.playersMoney()).contains(t.getText()))
                .map(t -> this.textList.get(this.textList.indexOf(t) + 1))
                .forEach(t -> {
                    t.setText("BANCAROTTA");
                    t.setBackground(Color.GRAY);
                });
    }

    private List<String> keysList(final Map<String, Integer> map) {
        return map.entrySet().stream()
                .map(Entry::getKey)
                .toList();
    }
}
