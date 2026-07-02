package giocoscudetto.view.impl.match;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.Font;
import java.util.Random;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import javax.swing.Timer;

import edu.umd.cs.findbugs.annotations.SuppressFBWarnings;
import giocoscudetto.controller.api.MatchController;
import giocoscudetto.view.impl.initialize.DefaultPanelImpl;

/**
 * This class represents the panel where the events of the game are resolved.
 */
public class EventPanel extends DefaultPanelImpl {

    private static final long serialVersionUID = 1L;
    private static final Color BACKGROUND_COLOR = new Color(223, 189, 138);
    private static final String FONT_NAME = "Arial";
    private static final String QUESTION_MARK = "?";
    private static final int FONT_SIZE = 20;
    private static final long TIME_WAIT = 1000;
    private static final int BOTTOM_GAP = 5;
    private static final int DICE_GAP = 10;
    private static final int BOUND = 7;
    private static final int DELAY = 80;
    private static final int FREE_KICK_GOAL = 7;
    private static final int CORNER_GOAL = 1;

    /**
     * Enum representing the type of event.
     */
    public enum EventType {
        FREE_KICK, CORNER, RESULT
    }

    private final MatchController controller;
    private final JLabel dice1Label = new JLabel(QUESTION_MARK, SwingConstants.CENTER);
    private final JLabel dice2Label = new JLabel(QUESTION_MARK, SwingConstants.CENTER);
    private final JLabel outcomeLabel = new JLabel("", SwingConstants.CENTER);
    private final JButton spinButton = new JButton("kick");
    private final JButton continueButton = new JButton("continue");
    private final JLabel titleLabel = new JLabel("", SwingConstants.CENTER);
    private final JLabel plusLabel = new JLabel("+", SwingConstants.CENTER);

    private EventType currentType = EventType.CORNER;

    /**
     * Constructor of the EventPanel class.
     * 
     * @param controller the game controller.
     */
    @SuppressFBWarnings
    public EventPanel(final MatchController controller) {
        this.controller = controller;
        buildUI(); //NOPMD
        this.setBackground(BACKGROUND_COLOR); //NOPMD
    }

    private void buildUI() {
        setLayout(new BorderLayout());

        titleLabel.setFont(new Font(FONT_NAME, Font.BOLD, FONT_SIZE));
        titleLabel.setText(getTitleType(currentType));
        titleLabel.setBackground(BACKGROUND_COLOR);
        add(titleLabel, BorderLayout.NORTH);

        final JPanel dicePanel = new JPanel(new FlowLayout(FlowLayout.CENTER, DICE_GAP, DICE_GAP));
        dice1Label.setFont(new Font(FONT_NAME, Font.BOLD, FONT_SIZE));
        dice2Label.setFont(new Font(FONT_NAME, Font.BOLD, FONT_SIZE));
        plusLabel.setFont(new Font(FONT_NAME, Font.BOLD, FONT_SIZE));
        dicePanel.add(dice1Label);
        dicePanel.add(plusLabel);
        dicePanel.add(dice2Label);
        dicePanel.setBackground(BACKGROUND_COLOR);
        add(dicePanel, BorderLayout.CENTER);

        final JPanel bottomPanel = new JPanel(new BorderLayout(BOTTOM_GAP, BOTTOM_GAP));
        outcomeLabel.setFont(new Font(FONT_NAME, Font.BOLD, FONT_SIZE));
        continueButton.setVisible(false);
        bottomPanel.add(outcomeLabel, BorderLayout.NORTH);
        bottomPanel.add(spinButton, BorderLayout.CENTER);
        bottomPanel.add(continueButton, BorderLayout.SOUTH);
        bottomPanel.setBackground(BACKGROUND_COLOR);
        add(bottomPanel, BorderLayout.SOUTH);

        spinButton.addActionListener(e -> animateAndResolve());

        continueButton.addActionListener(e -> {
            this.controller.gameModeFinished();
        });
    }

    private String getTitleType(final EventType type) {
        return switch (type) {
            case FREE_KICK -> "FREE KICK!";
            case CORNER -> "CORNER!";
            case RESULT -> "RISULTATO!";
        };
    }

    private void animateAndResolve() {
        spinButton.setEnabled(false);
        dice1Label.setText(QUESTION_MARK);
        dice2Label.setText(QUESTION_MARK);
        outcomeLabel.setText("");
        continueButton.setVisible(false);

        final Random rnd = new Random();
        final long startTime = System.currentTimeMillis();
        final Timer animTimer = new Timer(DELAY, null);

        animTimer.addActionListener(e -> {
            dice1Label.setText(String.valueOf(rnd.nextInt(BOUND)));
            dice2Label.setText(String.valueOf(rnd.nextInt(BOUND)));

            if (System.currentTimeMillis() - startTime > TIME_WAIT) {
                animTimer.stop();
                showResult();
            }
        });
        animTimer.start();
    }

    private void showResult() {
        dice1Label.setText(String.valueOf(this.controller.diceEvent()));
        dice2Label.setText(String.valueOf(this.controller.diceEvent()));

        if (EventType.FREE_KICK == currentType) {
            if (Integer.parseInt(dice1Label.getText()) + Integer.parseInt(dice2Label.getText()) == FREE_KICK_GOAL) {
                outcomeLabel.setText("GOAL");
            } else {
                outcomeLabel.setText("NO GOAL");
            }
        } else if (EventType.CORNER == currentType) {
            if (Integer.parseInt(dice1Label.getText()) == CORNER_GOAL || Integer.parseInt(dice2Label.getText()) == CORNER_GOAL) {
                outcomeLabel.setText("GOAL");
            } else {
                outcomeLabel.setText("NO GOAL");
            }
        }
        this.continueButton.setVisible(true);
    }

    /**
     * This method configures the panel for a specific event type, setting the appropriate texts and button states.
     * 
     * @param type the type of event.
     */
    public void configure(final EventType type) {
        this.currentType = type;
        titleLabel.setText(getTitleType(type));
        dice1Label.setText(QUESTION_MARK);
        dice2Label.setText(QUESTION_MARK);
        outcomeLabel.setText("");
        continueButton.setVisible(false);
        spinButton.setEnabled(true);
        if (type == EventType.RESULT) {
            plusLabel.setText("-");
            spinButton.setText("new Result");
        } else if (this.currentType == EventType.CORNER) {
            plusLabel.setText("");
            spinButton.setText("kick the " + getTitleType(type));
        } else if (this.currentType == EventType.FREE_KICK) {
            spinButton.setText("kick the " + getTitleType(type));
        }
    }
}
