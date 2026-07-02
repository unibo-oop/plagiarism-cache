package it.unibo.jtrs.view.impl;

import java.awt.Color;
import java.util.stream.Collectors;

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JComponent;
import javax.swing.JLabel;

import it.unibo.jtrs.controller.api.PreviewController;
import it.unibo.jtrs.view.api.GenericPanel;
import it.unibo.jtrs.view.custom.Constants;
import it.unibo.jtrs.view.custom.GridPanel;
import it.unibo.jtrs.view.custom.Label;

/**
 * The class models the preview panel. This view must show the next Tetromino.
 */
public class PreviewPanel extends GenericPanel {

    public static final long serialVersionUID = 4328743;

    private final GridPanel preview;
    private final transient PreviewController controller;

    /**
     * Constructor.
     *
     * @param controller the preview controller
     */
    public PreviewPanel(final PreviewController controller) {
        this.controller = controller;

        this.setOpaque(false);
        this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        this.add(Box.createVerticalStrut(Constants.PreviewPanel.PADDING));

        this.preview = new GridPanel(Constants.PreviewPanel.GRID_ROWS, Constants.PreviewPanel.GRID_COLS, 0);
        this.preview.setOpaque(false);
        this.preview.setAlignmentX(JComponent.CENTER_ALIGNMENT);

        final JLabel label = new Label("NEXT", Constants.PreviewPanel.FONT_SIZE);
        label.setAlignmentX(JComponent.CENTER_ALIGNMENT);

        this.add(label);
        this.add(Box.createVerticalStrut(Constants.PreviewPanel.INTERLINE));
        this.add(this.preview);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void redraw() {
        final var t = this.controller.getCurrentTetromino();
        t.translate(1, 1);
        final var c = t.getComponents()
            .stream()
            .collect(Collectors.toMap(k -> k, v -> Color.decode(t.getColor())));
        this.preview.setCells(c);
    }

}
