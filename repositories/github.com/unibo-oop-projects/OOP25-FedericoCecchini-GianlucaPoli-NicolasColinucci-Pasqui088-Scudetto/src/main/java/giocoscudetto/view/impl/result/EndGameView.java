package giocoscudetto.view.impl.result;

import edu.umd.cs.findbugs.annotations.SuppressFBWarnings;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Toolkit;

import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.SwingConstants;

import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.border.TitledBorder;

import giocoscudetto.controller.api.CreateUpdateController;
import giocoscudetto.controller.api.Starter;
import giocoscudetto.view.impl.initialize.DefaultPanelImpl;

/**
 * View displayed at the end of the League showing
 * the winner and the final ranking.
 */
public final class EndGameView extends DefaultPanelImpl {

    private static final int BUTTON_BORDER = 5;
    private static final int WINNER_FONT_SIZE = 30;
    private static final int TABLE_FONT_DIVISOR = 70;

    private static final int TABLE_WIDTH = 1000;
    private static final int TABLE_HEIGHT = 600;
    private static final int WIDTH_RESIZING = 2;
    private static final int HEIGHT_RESIZING = 3;

    private static final int TITLE_RED = 195;
    private static final int TITLE_GREEN = 45;
    private static final int TITLE_BLUE = 35;

    private static final int WINNER_SPACING = 80;
    private static final int TABLE_SPACING = 120;

    private static final int RESIZE_FONT_DIVISOR = 30;
    private static final int RESIZE_TABLE_FONT_DIVISOR = 100;
    private static final int RESIZE_ROW_HEIGHT_DIVISOR = 20;
    private static final int ROW_MARGIN = 3;
    private static final int TITLE_FONT_REDUCTION = 70;

    private static final long serialVersionUID = 1L;
    private final transient Starter controller;
    private final transient CreateUpdateController createUpdateController;
    private final transient Image image;
    private final Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
    private final int minimumWidht = screenSize.width / 2;

    /**
     * Creates the end game view.
     *
     * @param controller application controller
     * @param createUpdateController League controller
     */
    @SuppressFBWarnings
    public EndGameView(final Starter controller,
        final CreateUpdateController createUpdateController) {
        this.controller = controller;
        this.createUpdateController = createUpdateController;
        this.setLayout(new BorderLayout());

        try {
            this.image = ImageIO.read(
                new File("src/main/resources/images/backgrounds/end-game-background.jpeg")
            );
        } catch (final IOException e) {
            throw new IllegalStateException("Failed to load image", e);
        }

        //vincitore
        final JLabel winnerLabel = new JLabel("WINNER:" + this.createUpdateController.getLeagueTableModel().
                                                getValueAt(0, 0), SwingConstants.RIGHT);
        winnerLabel.setFont(
            new Font(FONT_SELECTED, Font.BOLD, WINNER_FONT_SIZE)
        );
        winnerLabel.setForeground(Color.BLACK);
        winnerLabel.setAlignmentX(RIGHT_ALIGNMENT);

        //tabella
        final JTable standingsTable = new JTable(this.createUpdateController.getLeagueTableModel());
        standingsTable.setEnabled(false);
        standingsTable.setOpaque(false);
        standingsTable.setFont(
            new Font(FONT_SELECTED, Font.BOLD, minimumWidht / TABLE_FONT_DIVISOR)
        );

        final JScrollPane tableScroll = new JScrollPane(standingsTable);

        tableScroll.setMaximumSize(
            new Dimension(TABLE_WIDTH, TABLE_HEIGHT)
        );
        tableScroll.setOpaque(false);
        tableScroll.getViewport().setOpaque(false);

        final TitledBorder titleS = new TitledBorder("FINAL RANKING");
        titleS.setTitleJustification(TitledBorder.CENTER);
        titleS.setTitleColor(new Color(TITLE_RED, TITLE_GREEN, TITLE_BLUE));

        tableScroll.setBorder(titleS);

        //pannello inferiore
        final JPanel lowerPanel = new JPanel(new BorderLayout());
        lowerPanel.setOpaque(false);
        lowerPanel.setBorder(BorderFactory.createEmptyBorder(0, BUTTON_BORDER, BUTTON_BORDER, BUTTON_BORDER));

        //pulsanti
        final JButton menuButton = new JButton("MENU");
        final JButton restartButton = new JButton("RESTART");

        //aggiunte al panel inferiore
        lowerPanel.add(menuButton, BorderLayout.WEST);
        lowerPanel.add(restartButton, BorderLayout.EAST);

        //torna al menu
        menuButton.addActionListener(e -> {
            this.createUpdateController.reset();
            this.controller.changeView("club");
        });

        //ricomincia
        restartButton.addActionListener(e -> {
            this.createUpdateController.restartLeague();
            this.controller.changeView("pre");
        });

        final JPanel centerPanel = new JPanel();
        centerPanel.setOpaque(false);
        centerPanel.setLayout(new BoxLayout(centerPanel, BoxLayout.Y_AXIS));

        //aggiunte al panel centrale
        centerPanel.add(Box.createVerticalStrut(WINNER_SPACING));
        centerPanel.add(winnerLabel);
        centerPanel.add(Box.createVerticalStrut(TABLE_SPACING));

        final JPanel tablePanel = new JPanel();
        tablePanel.setOpaque(false);

        tablePanel.add(tableScroll);

        centerPanel.add(tablePanel);

        //aggiunte al panel principale
        this.add(centerPanel, BorderLayout.CENTER);
        this.add(lowerPanel, BorderLayout.SOUTH);

        this.addComponentListener(new java.awt.event.ComponentAdapter() {
            @Override
            public void componentResized(final java.awt.event.ComponentEvent e) {
                final int width = getWidth();
                final int height = getHeight();

                winnerLabel.setFont(
                    new Font(
                        FONT_SELECTED,
                         Font.BOLD,
                          width / RESIZE_FONT_DIVISOR
                        )
                    );
                standingsTable.setFont(
                    new Font(
                        FONT_SELECTED,
                         Font.BOLD,
                          width / RESIZE_TABLE_FONT_DIVISOR
                        )
                    );
                standingsTable.setPreferredScrollableViewportSize(new Dimension(width / WIDTH_RESIZING, 
                                                                                    height / HEIGHT_RESIZING));
                titleS.setTitleFont(new Font(FONT_SELECTED, Font.BOLD, width / TITLE_FONT_REDUCTION));
                standingsTable.setRowHeight(height / RESIZE_ROW_HEIGHT_DIVISOR);
                standingsTable.setRowMargin(ROW_MARGIN);
                menuButton.setFont(new Font(FONT_SELECTED, Font.BOLD, width / SWITCHER_BUTTON_FONT_RESIZING));
                restartButton.setFont(new Font(FONT_SELECTED, Font.BOLD, width / SWITCHER_BUTTON_FONT_RESIZING));
                revalidate();
            }
        });
    }

    @Override
    public void paintComponent(final Graphics g) {
        super.paintComponent(g);

        if (g instanceof Graphics2D g2d) {
            g2d.drawImage(this.image, 0, 0, getWidth(), getHeight(), null);
        }
        //final Graphics2D g2d = (Graphics2D) g;
    }
}
