package view.board.sideview;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.GridLayout;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JSlider;
import javax.swing.SwingConstants;
import javax.swing.border.Border;
import javax.swing.border.EmptyBorder;
import javax.swing.border.MatteBorder;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

import controller.Controller;
import controller.ControllerImpl;
import controller.image.BoardImageManager;
import utilities.Colors;

/**
 *
 * Piero Sanchi. This class is a side panel for the BoardView.
 *
 */
public final class GameView extends JPanel implements GameViewInterface {
    private static final long serialVersionUID = 4800631179056854141L;

    private static final int WIDTH = 350;
    private static final int HEIGHT = 450;
    private static final int TITLEFONT = 17;
    private static final int CONTENTFONT = 16;

    private final JPanel northPanel = new JPanel();
    private final JPanel midPanel = new JPanel();
    private final JPanel southPanel = new JPanel();

    private final JPanel northUpPanel = new JPanel();
    private final JPanel northDownPanel = new JPanel();

    private final JPanel southUpPanel = new JPanel();
    private final JPanel southDownPanel = new JPanel();

    private final MyButton diceRoll = new MyButton("Lancia il dado");
    private final MyButton audio = new MyButton(" ");
    private final MyButton undo = new MyButton("UNDO");
    private final JSlider volume = new JSlider(JSlider.HORIZONTAL, 60, 100, 80);
    private final JLabel instructions = new JLabel();
    private final JLabel turn = new JLabel("Turno:");
    private final JLabel turnPlayer = new JLabel();
    private final JLabel availableSteps = new JLabel("Passi disponibili: ");
    private final JLabel availableNSteps = new JLabel();
    private final JLabel lblDice = new JLabel();
    private final JLabel lblEroiAlTempio = new JLabel("Eroi al tempio:");
    private final JLabel lblArrived = new JLabel();
    private final Border border = new MatteBorder(2, 0, 2, 0, new Color(0, 0, 0));
    private final Border northUpBorder = new EmptyBorder(10, 0, 0, 0);
    private final Border southUpBorder = new EmptyBorder(0, 12, 40, 0);
    private final Border southDownBorder = new EmptyBorder(0, 0, 30, 0);
    private final Border padding = new EmptyBorder(20, 10, 20, 10);
    private final Border instructionPadding = BorderFactory.createEmptyBorder(10, 0, 10, 0);
    private final GridLayout gridlayout = new GridLayout(3, 2, 0, 20);

    private final Controller controller = ControllerImpl.getLog();
    private final BoardImageManager iconManager;

    private AudioState aState = this.controller.isAudioOn() ? new AudioOff() : new AudioOn();

    /**
     * Constructor of GameView.
     */
    public GameView() {
        super();

        this.iconManager = new BoardImageManager();

        this.setPreferredSize(new Dimension(WIDTH, HEIGHT));
        this.setMinimumSize(new Dimension(WIDTH, HEIGHT));
        this.setBorder(this.padding);

        // operations on slider:
        volume.setOpaque(false);
        volume.addChangeListener(new ChangeListener() {

            @Override
            public void stateChanged(final ChangeEvent e) {
                controller.setVolume(volume.getValue());
            }

        });
        controller.setVolume(volume.getValue());

        // operations on buttons:
        // diceroll
        this.diceRoll.addActionListener(e -> ControllerImpl.getLog().rollDice());

        // undo
        this.undo.addActionListener(e -> ControllerImpl.getLog().undo());

        // audio
        this.aState.execute(this);
        this.audio.addActionListener(e -> {
            this.aState.execute(this);
        });

        // operations on labels
        this.instructions.setBorder(BorderFactory.createCompoundBorder(this.border, this.instructionPadding));
        this.instructions.setVerticalAlignment(SwingConstants.TOP);
        this.applyLabelStyle(this.instructions);
        this.turnPlayer.setFont(new Font("Tahoma", Font.BOLD, CONTENTFONT));
        this.applyLabelStyle(this.turn);
        this.applyLabelStyle(this.availableSteps);
        this.applyLabelStyle(this.lblEroiAlTempio);
        this.applyContentStyle(this.availableNSteps);
        this.applyContentStyle(this.lblArrived);

        // operations on inner panels
        this.northUpPanel.add(this.lblDice, BorderLayout.CENTER);
        this.northUpPanel.setBorder(this.northUpBorder);
        this.northUpPanel.setOpaque(false);
        this.northDownPanel.add(this.diceRoll, BorderLayout.CENTER);
        this.northDownPanel.setOpaque(false);
        this.southUpPanel.setLayout(this.gridlayout);
        this.southUpPanel.add(this.turn);
        this.southUpPanel.add(this.turnPlayer);
        this.southUpPanel.add(this.availableSteps);
        this.southUpPanel.add(this.availableNSteps);
        this.southUpPanel.add(this.lblEroiAlTempio);
        this.southUpPanel.add(this.lblArrived);
        this.southUpPanel.setBorder(this.southUpBorder);
        this.southUpPanel.setOpaque(false);
        this.southDownPanel.add(this.undo);
        this.southDownPanel.add(this.audio);
        this.southDownPanel.add(volume);
        this.southDownPanel.setBorder(this.southDownBorder);
        this.southDownPanel.setOpaque(false);

        // operations on intermediate panels
        this.northPanel.setLayout(new BoxLayout(this.northPanel, BoxLayout.Y_AXIS));
        this.southPanel.setLayout(new BoxLayout(this.southPanel, BoxLayout.Y_AXIS));
        this.northPanel.add(this.northUpPanel);
        this.northPanel.add(this.northDownPanel);
        this.northPanel.setOpaque(false);
        this.midPanel.add(this.instructions);
        this.midPanel.setOpaque(false);
        this.southPanel.add(this.southUpPanel);
        this.southPanel.add(this.southDownPanel);
        this.southPanel.setOpaque(false);

        // operations on extern panels
        this.setLayout(new BorderLayout());
        this.add(this.northPanel, BorderLayout.NORTH);
        this.add(this.midPanel);
        this.add(this.southPanel, BorderLayout.SOUTH);
        this.setOpaque(false);

    }

    private void applyContentStyle(final JLabel label) {
        label.setForeground(Color.BLACK);
        label.setFont(new Font("Tahoma", Font.PLAIN, CONTENTFONT));
    }

    private void applyLabelStyle(final JLabel label) {
        label.setForeground(Color.BLACK);
        label.setFont(new Font("Tahoma", Font.BOLD, TITLEFONT));
    }

    private void disable(final JButton button) {
        button.setEnabled(false);
        button.setBackground(Color.GRAY);
    }

    private void enable(final MyButton button) {
        button.setEnabled(true);
        button.setBackground(button.getColor());
    }

    @Override
    public void paintComponent(final Graphics g) {
        g.drawImage(this.iconManager.getPaper().getImage(), 0, 0, this.getSize().width, this.getSize().height, this);
    }

    @Override
    public void redrawLabel(final String avviso) {
        switch (avviso) {
        case "minotauro":
            this.setInstructionsText(
                    "<html>Minotauro:<br>seleziona il minotauro con il mouse <br>e poi spostalo con le frecce <br>direzionali. <br>Per terminare il turno premi INVIO.</html>");
            break;
        case "muro":
            this.setInstructionsText(
                    "<html>Muro: <br>seleziona il muro che vuoi spostare<br>con il mouse, ruotalo con R e <br>spostalo con le frecce direzionali. <br>Per terminare il turno premi INVIO.</html>");
            break;
        case "jump":
            this.setInstructionsText(
                    "<html>Eroe hedgeJump:<br>seleziona l'eroe che vuoi muovere e<br>spostalo con le frecce direzionali.<br>Hai diritto a 3 passi, anche <br>transitando su siepi. <br>Per terminare il turno premi INVIO.</html>");
            break;
        default:
            this.setInstructionsText(
                    "<html>Eroe: <br>seleziona l'eroe che vuoi muovere <br>con il mouse e poi spostalo con le <br>frecce direzionali. <br>Per terminare il turno premi INVIO. </html>");
            break;
        }
    }

    @Override
    public void repaintLabel(final Colors color) {
        switch (color) {
        case Blue:
            this.turnPlayer.setForeground(Color.BLUE);
            break;
        case Red:
            this.turnPlayer.setForeground(Color.RED);
            break;
        case White:
            this.turnPlayer.setForeground(Color.WHITE);
            break;
        case Yellow:
            this.turnPlayer.setForeground(Color.ORANGE);
            break;
        default:
            this.turnPlayer.setForeground(Color.BLACK);
            break;
        }
    }

    @Override
    public void setAudioText(final String text) {
        this.audio.setText(text);
    }

    @Override
    public void setAvailableNStepsText(final Integer nSteps) {
        if (nSteps != null) {
            this.availableNSteps.setText(nSteps.toString());
        } else {
            this.availableNSteps.setText(null);
        }
    }

    @Override
    public void setDiceImage(final String imagePath) {
        try {
            this.lblDice.setIcon(new ImageIcon(ImageIO.read(this.getClass().getResourceAsStream(imagePath))));
        } catch (final IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void setHeroArrived(final Integer arrived, final Integer requested) {
        this.lblArrived.setText("\t" + arrived.toString() + " / " + requested.toString());
    }

    private void setInstructionsText(final String instruction) {
        this.instructions.setText(instruction);
    }

    @Override
    public void setState(final AudioState state) {
        this.aState = state;
    }

    @Override
    public void setTurnPlayerText(final String player) {
        this.turnPlayer.setText(player);
    }

    @Override
    public void waitDice() {
        this.enable(this.diceRoll);
        this.disable(this.undo);
        this.setInstructionsText("Lancia il dado, Buona fortuna!");
        this.lblDice.setVisible(false);
    }

    @Override
    public void waitMove() {
        this.enable(this.undo);
        this.disable(this.diceRoll);
    }

    @Override
    public void waitSelect() {
        this.disable(this.diceRoll);
        this.disable(this.undo);
        this.lblDice.setVisible(true);
    }

}
