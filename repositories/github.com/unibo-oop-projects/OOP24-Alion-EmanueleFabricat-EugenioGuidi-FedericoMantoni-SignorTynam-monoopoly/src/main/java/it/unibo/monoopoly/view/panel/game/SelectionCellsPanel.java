package it.unibo.monoopoly.view.panel.game;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionListener;
import java.util.Map;

import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.border.LineBorder;

import it.unibo.monoopoly.utils.impl.CellGiverListener;

/**
 * {@link JPanel} used to show and select a cell.
 */
public final class SelectionCellsPanel extends JPanel {

    private static final long serialVersionUID = 1L;
    private static final Color GREEN_MONOPOLY = new Color(0xecfcf4);
    private static final int TEXT_RESIZE = 60;

    /**
     * Constructor of the class.
     * 
     * @param mainFrameHeight   use to set the size of the text.
     * @param closeMethod       the {@link ActionListener} to attach to the
     *                          {@link JButton}.
     * @param cellMap           contain the entry name of the player -> amount.
     * @param text              to finish the title text.
     * @param addNoChooseButton to decide if the {@link JButton} noChoiceButton have
     *                          to display or not.
     */
    public SelectionCellsPanel(final int mainFrameHeight, final CellGiverListener closeMethod,
            final Map<String, Integer> cellMap, final String text, final boolean addNoChooseButton) {
        super();
        final JPanel innerPanel = new JPanel();
        final JTextArea title = new JTextArea("Scegli una proprietà " + text);
        final Font font = new Font("Arial", Font.PLAIN, mainFrameHeight / 45);
        title.setLineWrap(true);
        title.setWrapStyleWord(true);
        title.setFont(font);
        title.setEnabled(false);
        title.setDisabledTextColor(Color.BLACK);
        title.setBorder(new LineBorder(Color.BLACK));
        title.setBackground(GREEN_MONOPOLY);
        this.setLayout(new BorderLayout());
        innerPanel.setLayout(
                new GridLayout(cellMap.size() / 2 + cellMap.size() % 2, cellMap.size() > 2 ? 2 : cellMap.size()));
        this.add(title, BorderLayout.NORTH);
        for (final var entry : cellMap.entrySet()) {
            final JButton button = new JButton(entry.getKey() + "\n " + entry.getValue() + " €");
            button.setFont(new Font("Arial", Font.PLAIN, mainFrameHeight / TEXT_RESIZE));
            innerPanel.add(button, BorderLayout.CENTER);
            button.addActionListener(closeMethod);
        }
        this.add(innerPanel, BorderLayout.CENTER);
        if (addNoChooseButton) {
            final JButton noChoiceButton = new JButton(CellGiverListener.NO_CHOICE);
            noChoiceButton.setFont(font);
            noChoiceButton.addActionListener(closeMethod);
            this.add(noChoiceButton, BorderLayout.SOUTH);
        }
    }
}
