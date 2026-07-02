package giocoscudetto.view.impl.result;

import javax.swing.border.TitledBorder;

import edu.umd.cs.findbugs.annotations.SuppressFBWarnings;

import java.awt.Font;
import java.awt.GridBagLayout;

import java.awt.BorderLayout;
import java.awt.Color;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JTable;
import javax.swing.JScrollPane;
import java.awt.Dimension;
import java.awt.Toolkit;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import java.awt.Image;
import java.awt.Graphics;
import java.awt.Graphics2D;

import giocoscudetto.view.api.ViewManager;
import giocoscudetto.view.impl.initialize.DefaultPanelImpl;
import giocoscudetto.view.impl.match.MatchPanel;
import giocoscudetto.controller.api.CreateUpdateController;
import giocoscudetto.controller.api.MatchController;
import giocoscudetto.controller.api.Starter;

/**
 * View component for displaying the table and the fixture.
 */
public final class PreMatchView extends DefaultPanelImpl {

    private static final long serialVersionUID = 1L;
    private static final int TITLE_FONT_REDUCTION = 70;
    private static final int FONT_REDUCTION = 100;
    private static final int BORDER = 5;
    private static final int REDR = 195;
    private static final int REDG = 45;
    private static final int REDB = 35;
    private static final int ZERO = 0;
    private static final int ROW_MARGIN = 3;
    private static final int SIZE_REDUCTION = 3;

    private final JTable fixtureTable;
    private final JTable leagueTable;

    private final Starter starter;
    private final CreateUpdateController controller;
    private final ViewManager viewManager;
    private final MatchController matchController;
    private int count;

    private final Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
    private final int minimumWidht = screenSize.width / 2;
    private final transient Image image;

    /**
     * Constructor for the PreMatchView.
     * 
     * @param starter the controller responsible for changing views
     * @param controller the controller responsible for providing data to the view
     * @param viewManager the manager responsible for managing the views
     * @param matchController the controller responsible for managing the match logic
     */
    @SuppressFBWarnings
    public PreMatchView(final Starter starter, final CreateUpdateController controller, final ViewManager viewManager,
                             final MatchController matchController) {
        this.starter = starter;
        this.controller = controller;
        this.viewManager = viewManager;
        this.matchController = matchController;
        this.fixtureTable = (JTable) createComponent(new JTable(), getTitleFont(), Color.BLACK, null);
        this.leagueTable = (JTable) createComponent(new JTable(), getTitleFont(), Color.BLACK, null);

        this.setLayout(new BorderLayout());

        try {
            this.image = ImageIO.read(new File("src/main/resources/images/backgrounds/pre-match-background.jpeg"));
        } catch (final IOException firstE) {
            throw new IllegalStateException("Failed to load image", firstE);
        }

        //pannello centrale
        final JPanel centralPanel = new JPanel(new GridBagLayout());
        centralPanel.setOpaque(false);

        //pannello inferiore
        final JPanel lowerPanel = new JPanel(new BorderLayout());
        lowerPanel.setBorder(BorderFactory.createEmptyBorder(ZERO, BORDER, BORDER, BORDER));
        lowerPanel.setOpaque(false);

        //prima tabella
        fixtureTable.setEnabled(false);
        fixtureTable.setOpaque(false);
        fixtureTable.getTableHeader().setReorderingAllowed(false);
        fixtureTable.setFont(new Font(FONT_SELECTED, Font.BOLD, minimumWidht / TITLE_FONT_REDUCTION));

        //seconda tabella
        leagueTable.setEnabled(false);
        leagueTable.setOpaque(false);
        leagueTable.getTableHeader().setReorderingAllowed(false);
        leagueTable.setFont(new Font(FONT_SELECTED, Font.BOLD, minimumWidht / TITLE_FONT_REDUCTION));

        //pulsanti in basso
        final JButton backButton = (JButton) createComponent(new JButton("BACK"), getExitFont(), Color.BLACK, null);
        final JButton continueButton = (JButton) createComponent(new JButton("CONTINUE"), getExitFont(), Color.BLACK, null);

        backButton.addActionListener(e -> { 
            this.controller.reset();
            this.starter.changeView("club");
        });

        continueButton.addActionListener(e -> {
            this.matchController.setMatch();
            if (count == ZERO) {
            MatchPanel matchPanel = null;
            try {
                matchPanel = new MatchPanel(this.starter, this.viewManager, this.controller, this.matchController);
            } catch (final IOException secondE) {
                secondE.printStackTrace(); //NOPMD
            }
            this.viewManager.addView(matchPanel, "match");
            count++;
            }
            this.controller.setPositionsZero();
            this.matchController.notifyViews();
            this.starter.changeView("match");
        });

        //aggiunte al panel inferiore
        lowerPanel.add(backButton, BorderLayout.WEST);
        lowerPanel.add(continueButton, BorderLayout.EAST);

        //aggiunte al panel centrale
        final JScrollPane scrollPaneF = new JScrollPane(fixtureTable);
        final TitledBorder titleF = new TitledBorder("FIXTURE");
        titleF.setTitleJustification(TitledBorder.CENTER);
        titleF.setTitleColor(new Color(REDR, REDG, REDB));
        scrollPaneF.setOpaque(false);
        scrollPaneF.getViewport().setOpaque(false);
        scrollPaneF.setBorder(titleF);
        centralPanel.add(scrollPaneF);

        final JScrollPane scrollPaneS = new JScrollPane(leagueTable);
        final TitledBorder titleS = new TitledBorder("STANDINGS");
        titleS.setTitleJustification(TitledBorder.CENTER);
        titleS.setTitleColor(new Color(REDR, REDG, REDB));
        scrollPaneS.setOpaque(false);
        scrollPaneS.getViewport().setOpaque(false);
        scrollPaneS.setBorder(titleS);
        centralPanel.add(scrollPaneS);

        //aggiunte al panel principale
        this.add(centralPanel, BorderLayout.CENTER);
        this.add(lowerPanel, BorderLayout.SOUTH);

        this.addComponentListener(new java.awt.event.ComponentAdapter() {

            @Override
            public void componentShown(final java.awt.event.ComponentEvent e) {
                PreMatchView.this.updateFixtureTable();
                PreMatchView.this.updateLeagueTable();
            }

            @Override
            public void componentResized(final java.awt.event.ComponentEvent e) {

                final int currentWidth = getWidth();
                final int currentHeight = getHeight();

                continueButton.setFont(new Font(FONT_SELECTED, Font.BOLD, currentWidth / SWITCHER_BUTTON_FONT_RESIZING));
                backButton.setFont(new Font(FONT_SELECTED, Font.BOLD, currentWidth / SWITCHER_BUTTON_FONT_RESIZING));
                leagueTable.setFont(new Font(FONT_SELECTED, Font.ROMAN_BASELINE, currentWidth / FONT_REDUCTION));
                fixtureTable.setFont(new Font(FONT_SELECTED, Font.ROMAN_BASELINE, currentWidth / FONT_REDUCTION));
                leagueTable.setRowMargin(ROW_MARGIN);
                fixtureTable.setRowMargin(ROW_MARGIN);
                leagueTable.setRowHeight(currentHeight / SWITCHER_BUTTON_FONT_RESIZING);
                fixtureTable.setRowHeight(currentHeight / SWITCHER_BUTTON_FONT_RESIZING);
                leagueTable.setPreferredScrollableViewportSize(new Dimension(currentWidth / SIZE_REDUCTION, 
                                                                                currentHeight / SIZE_REDUCTION));
                fixtureTable.setPreferredScrollableViewportSize(new Dimension(currentWidth / SIZE_REDUCTION, 
                                                                                currentHeight / SIZE_REDUCTION));
                titleS.setTitleFont(new Font(FONT_SELECTED, Font.BOLD, currentWidth / TITLE_FONT_REDUCTION));
                titleF.setTitleFont(new Font(FONT_SELECTED, Font.BOLD, currentWidth / TITLE_FONT_REDUCTION));
                revalidate();
            }
        });
    }

    @Override
    public void paintComponent(final Graphics g) {
        super.paintComponent(g);

        if (g instanceof Graphics2D g2d) {
            g2d.drawImage(this.image, ZERO, ZERO, getWidth(), getHeight(), null);
        }
    }

    /**
     * Updates the fixture table with the latest data from the controller.
     */
    private void updateFixtureTable() {
        fixtureTable.setModel(controller.getFixtureTableModel());
    }

    /**
     * Updates the league table with the latest data from the controller.
     */
    private void updateLeagueTable() {
        leagueTable.setModel(controller.getLeagueTableModel());
    }
}
