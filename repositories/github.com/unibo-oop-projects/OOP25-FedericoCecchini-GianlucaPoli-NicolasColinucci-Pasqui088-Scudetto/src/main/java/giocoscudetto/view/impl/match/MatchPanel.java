package giocoscudetto.view.impl.match;

import giocoscudetto.controller.api.CreateUpdateController;
import giocoscudetto.controller.api.MatchController;
import giocoscudetto.controller.api.Starter;
import giocoscudetto.view.api.GameObserver;
import giocoscudetto.view.api.ViewManager;
import giocoscudetto.view.impl.initialize.DefaultPanelImpl;
import giocoscudetto.view.impl.result.EndGameView;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.io.IOException;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;

import edu.umd.cs.findbugs.annotations.SuppressFBWarnings;

/**
 * This class represents the panel where the match is played, 
 * it contains the board, 
 * the dice panel, the net panel and the event panel.
 */
public class MatchPanel extends DefaultPanelImpl implements GameObserver {

    private static final long serialVersionUID = 1L;
    private static final Color BACKGROUND_COLOR = new Color(223, 189, 138);
    private static final int DIM_X = 300;
    private static final int DIM_Y = 200;
    private static final int PANEL_W = 280;
    private static final int PANEL_H = 120;
    private static final int TURN_FONT_SIZE = 20;
    private final Starter controller;
    private final ViewManager viewManager;
    private final CreateUpdateController createUpdateController;
    private final MatchController matchController;
    private final JLabel turnLabel;
    private final NetPanel netPanel;
    private final DicePanel bottomDice;
    private final JButton continueButton;
    private final EventPanel eventPanel;
    private final JCheckBox helpBox;

    /**
     * Constructor of the MatchPanel class.
     * 
     * @param controller the starter controller to manage the views.
     * @param viewManager the view manager to switch between views.
     * @param createUpdateController the controller to manage the clubs and the matches.
     * @param matchController the match controller to manage the game logic.
     * @throws IOException if loading an image fails.
     */
    @SuppressFBWarnings
    public MatchPanel(final Starter controller, final ViewManager viewManager, 
                        final CreateUpdateController createUpdateController,
                        final MatchController matchController) throws IOException {

        final BoardPanel boardJPanel = new BoardPanel(matchController);
        boardJPanel.start();
        this.bottomDice = new DicePanel(matchController, boardJPanel);
        this.netPanel = new NetPanel(matchController);
        this.controller = controller;
        this.viewManager = viewManager;
        this.createUpdateController = createUpdateController;
        this.matchController = matchController;
        this.setLayout(new BorderLayout()); //NOPMD
        this.setBackground(BACKGROUND_COLOR); //NOPMD
        this.matchController.addObserver(this);
        this.add(boardJPanel, BorderLayout.CENTER); //NOPMD
        this.helpBox = new JCheckBox("Help for box");
        this.helpBox.setSelected(false);

        this.helpBox.addActionListener(e -> { 
            this.matchController.setHelpFlag(this.helpBox.isSelected());
        });

        this.eventPanel = new EventPanel(this.matchController);
        eventPanel.setMaximumSize(new Dimension(DIM_X, DIM_Y));

        final JPanel helpPanel = new JPanel();
        helpPanel.setBackground(BACKGROUND_COLOR);
        helpPanel.setLayout(new BoxLayout(helpPanel, BoxLayout.X_AXIS));
        helpPanel.add(helpBox);

        final JPanel rightPanel = new JPanel();
        rightPanel.setLayout(new BoxLayout(rightPanel, BoxLayout.Y_AXIS));
        rightPanel.setOpaque(false);
        rightPanel.setPreferredSize(new Dimension(PANEL_W, 0));

        final JPanel turnPanel = new JPanel();
        turnLabel = new JLabel("Turn of :" + this.matchController.getCurrentPlayer());
        turnPanel.setBackground(BACKGROUND_COLOR);
        turnLabel.setFont(new Font("Turn", Font.BOLD, TURN_FONT_SIZE));
        turnPanel.add(turnLabel);
        turnPanel.setAlignmentX(CENTER_ALIGNMENT);
        turnPanel.setMaximumSize(new Dimension(PANEL_W, PANEL_H));

        final JPanel netWrapper = new JPanel(new BorderLayout());
        netWrapper.setMaximumSize(new Dimension(DIM_X, DIM_Y));
        this.continueButton = (JButton) createComponent(new JButton("CONTINUE"), getExitFont(), Color.BLACK, null);
        continueButton.setEnabled(false);
        continueButton.setVisible(false);

        netWrapper.setOpaque(false);
        netWrapper.setAlignmentX(CENTER_ALIGNMENT);
        netWrapper.add(netPanel, BorderLayout.CENTER);

        bottomDice.setAlignmentX(CENTER_ALIGNMENT);
        bottomDice.setMaximumSize(new Dimension(PANEL_W, PANEL_H));

        rightPanel.add(helpPanel);
        rightPanel.add(turnPanel);
        rightPanel.add(netWrapper);
        rightPanel.add(bottomDice);
        rightPanel.add(eventPanel);
        rightPanel.add(continueButton);

        continueButton.addActionListener(e -> { 
            this.matchController.addPoints();
            if (this.matchController.isLastMatch()) {
                final EndGameView endGameView = new EndGameView(this.controller, this.createUpdateController);
                this.viewManager.addView(endGameView, "end");
                this.controller.changeView("end");
            } else {
                this.controller.changeView("pre");
            }
        });
        this.add(rightPanel, BorderLayout.EAST); //NOPMD

        this.addComponentListener(new java.awt.event.ComponentAdapter() { //NOPMD
            @Override
            public void componentResized(final java.awt.event.ComponentEvent e) {
                final int currentWidth = getWidth();

                revalidate();
                repaint();
                continueButton.setFont(new Font(FONT_SELECTED, Font.BOLD, currentWidth / (SWITCHER_BUTTON_FONT_RESIZING * 2)));
            }
        });
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void updateState() {
        SwingUtilities.invokeLater(() -> {
            turnLabel.setText("Turn of :" + this.matchController.getCurrentPlayer());

            switch (this.matchController.getGameMode()) {
                case "PENALTY": 
                    netPanel.setButtonsEnabled(true);
                    break;
                case "FREE_KICK":
                    this.eventPanel.configure(EventPanel.EventType.FREE_KICK);
                    this.eventPanel.setVisible(true);
                    break;
                case "CORNER":
                    this.eventPanel.configure(EventPanel.EventType.CORNER);
                    this.eventPanel.setVisible(true);
                    break;
                case "RESULT":
                    this.eventPanel.configure(EventPanel.EventType.RESULT);
                    this.eventPanel.setVisible(true);
                    break;
                default:
                    this.eventPanel.setVisible(false);
                    break;
            }

            continueButton.setVisible(this.matchController.isLastBox());
            continueButton.setEnabled(this.matchController.isLastBox());
            if (this.matchController.isLastBox()) {
                this.matchController.lastBox();
            }
        });
    }
}
