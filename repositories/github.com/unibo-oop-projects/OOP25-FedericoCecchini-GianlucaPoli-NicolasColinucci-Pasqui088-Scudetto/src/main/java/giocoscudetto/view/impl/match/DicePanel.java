package giocoscudetto.view.impl.match;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.util.Random;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.SwingConstants;
import javax.swing.Timer;
import javax.swing.border.EmptyBorder;

import edu.umd.cs.findbugs.annotations.SuppressFBWarnings;
import giocoscudetto.controller.api.MatchController;
import giocoscudetto.view.api.GameObserver;
import giocoscudetto.view.impl.initialize.DefaultPanelImpl;

/**
 * This class represents the panel where the dice are rolled.
 */
public class DicePanel extends DefaultPanelImpl implements GameObserver {

    private static final long serialVersionUID = 1L;
    private static final Color BACKGROUND_COLOR = new Color(223, 189, 138);
    private static final int FONT_SIZE = 20;
    private static final long TIMER_WAIT = 700;
    private static final int TIMER_DELAY = 80;
    private static final int BOUND = 13;
    private final MatchController controller;
    private final JLabel messageLabel;
    private final BoardPanel board;
    private final JButton rollDiceButton;

    /**
     * Constructor of the DicePanel class.
     * 
     * @param controller the controller of the game.
     * @param board the board panel to update the positions of the players after the dice roll.
     */
    @SuppressFBWarnings
    public DicePanel(final MatchController controller, final BoardPanel board) {

        this.rollDiceButton = new JButton("Roll Dice");
        this.controller = controller;
        this.controller.addObserver(this);
        this.board = board;
        this.setLayout(new BorderLayout()); //NOPMD
        this.setBackground(BACKGROUND_COLOR); //NOPMD
        messageLabel = new JLabel();
        messageLabel.setBackground(BACKGROUND_COLOR);
        messageLabel.setFont(new Font("SansSerif", Font.PLAIN, FONT_SIZE));
        messageLabel.setHorizontalAlignment(SwingConstants.CENTER);
        messageLabel.setBorder(new EmptyBorder(8, 4, 8, 4));

        this.add(rollDiceButton, BorderLayout.SOUTH); //NOPMD
        this.add(messageLabel, BorderLayout.CENTER); //NOPMD

        rollDiceButton.addActionListener(e -> {
            this.board.resetCheckBoxDone();
            this.rollDiceButton.setEnabled(false);
            this.animateAndResolve();
        });
    }

    /**
     * This method animates the dice roll and then shows the result of the roll.
     */
    private void animateAndResolve() {
        final Random rnd = new Random();
        final long startTime = System.currentTimeMillis();
        final Timer animTimer = new Timer(TIMER_DELAY, null);

        animTimer.addActionListener(e -> {
            messageLabel.setText(String.valueOf(rnd.nextInt(BOUND)));
            if (System.currentTimeMillis() - startTime > TIMER_WAIT) {
                animTimer.stop();
                showResult();
            }
        });
        animTimer.start();
    }

    /**
     * This method shows the result of the dice roll and repaints the board.
     */
    private void showResult() {
        messageLabel.setText(String.valueOf(this.controller.move()));
        this.board.repaint();
    }

    /**
     * This method enables or disables the roll dice button.
     * 
     * @param active true to enable the button, false to disable it.
     */
    public void setDice(final boolean active) {
       this.rollDiceButton.setEnabled(active);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void updateState() {
            final boolean isAnimating = controller.getHomePosition() != board.getAnimatedHomePosition()
                                || controller.getGuestPosition() != board.getAnimatedGuestPosition();
            if (isAnimating || !"NONE".equals(this.controller.getGameMode())) {
                this.setDice(false);
            } else {
                this.setDice(true);
            }
    }
}
