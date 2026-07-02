package giocoscudetto.view.impl.match;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.GridLayout;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import javax.imageio.ImageIO;
import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.Timer;

import edu.umd.cs.findbugs.annotations.SuppressFBWarnings;
import giocoscudetto.controller.api.MatchController;
import giocoscudetto.view.impl.initialize.DefaultPanelImpl;

/**
 * This class represents the panel where the penalty is resolved.
 */
public class NetPanel extends DefaultPanelImpl {

    private static final long serialVersionUID = 1L;
    private static final Color BACKGROUND_COLOR = new Color(0xC8E6C9);
    private static final int ROWS = 2;
    private static final int COLS = 3;
    private static final int H_GAP = 6;
    private static final int BORDER_SIZE = 8;
    private static final int BOTTON1_POS = 1;
    private static final int BOTTON2_POS = 2;
    private static final int BOTTON3_POS = 3;
    private static final int BOTTON4_POS = 4;
    private static final int BOTTON5_POS = 5;
    private static final int BOTTON6_POS = 6;
    private static final int BOTTONS_COUNT = 6;
    private static final int DELAY = 70;
    private static final long TIME_WAIT = 1000;
    private static final int BOUND = 6;
    private static final Color SELECTION_COLOR = Color.green;

    private final MatchController controller;
    private final BufferedImage image;
    private final List<JButton> buttons = new ArrayList<>();
    private final JButton kickButton = new JButton("KICK THE PENALTY");
    private final JButton continueButton = new JButton("CONTINUE");
    private final JLabel label;
    private final JPanel net;
    private int count;

    /**
     * Constructor of the NetPanel class.
     * 
     * @param controller the game controller.
     * @throws IOException if an error occurs while loading the image.
     */
    @SuppressFBWarnings
    public NetPanel(final MatchController controller) throws IOException {
        this.controller = controller;
        this.setLayout(new BorderLayout()); //NOPMD
        this.label = new JLabel();
        this.label.setBackground(new Color(BACKGROUND_COLOR.getRGB()));
        label.setHorizontalAlignment(JLabel.CENTER);
        this.add(label, BorderLayout.NORTH); //NOPMD
        kickButton.setEnabled(false);
        this.net = new JPanel();
        net.setOpaque(false);
        net.setLayout(new GridLayout(ROWS, COLS, H_GAP, H_GAP));
        net.setBorder(BorderFactory.createEmptyBorder(BORDER_SIZE, BORDER_SIZE, BORDER_SIZE, BORDER_SIZE));
        this.add(net, BorderLayout.CENTER); //NOPMD
        this.add(kickButton, BorderLayout.SOUTH); //NOPMD

        kickButton.addActionListener(e -> {
                this.animateAndResolve();
        });

        continueButton.addActionListener(e -> {
            this.reset();
            this.controller.gameModeFinished();
        });

        try {
        this.image = ImageIO.read(new File("src/main/resources/images/backgrounds/net.png"));
        } catch (final IOException e) {
            e.printStackTrace(); //NOPMD
            throw new IOException("Failed to load image", e);
        }

        this.createBottons();
        this.setButtonsEnabled(false); //NOPMD
    }

    /**
     * This method creates the buttons of the net panel and adds the action listeners to them.
     */
    private void createBottons() {
        for (int i = 0; i < BOTTONS_COUNT; i++) {
            final int index = i + 1;
            final JButton btn = new JButton(String.valueOf(index));
            buttons.add(btn);
            btn.addActionListener(e -> {
                markSelected(btn);
                if (count == 0) {
                    this.controller.setKeeperPosition(index);
                    count++;
                    checkButtons(index);
                } else if (count == 1) {
                    this.controller.setKeeperPosition(index);
                    this.setButtonsEnabled(false);
                    count++;
                    kickButton.setEnabled(true);
                }
            });
        }
        buttons.forEach(net::add);
    }

    /**
     * This method marks the selected button with a different color.
     * 
     * @param btn the button to mark.
     */
    private void markSelected(final JButton btn) {
        btn.setBackground(SELECTION_COLOR);
        btn.setOpaque(true);
    }

    /**
     * This method enables or disables the buttons of the net panel.
     * 
     * @param b true to enable the buttons, false to disable them.
     */
    public void setButtonsEnabled(final boolean b) {
        buttons.forEach(x -> x.setEnabled(b));
        if (b) {
            label.setText(this.controller.getNotCurrentPlayer() + " choose the position of the keeper");
            this.kickButton.setEnabled(false);
        }
    }

    /**
     * This method checks the buttons that are enabled or disabled based on the position of the keeper.
     * 
     * @param position the position of the keeper.
     */
    private void checkButtons(final int position) {
        switch (position) {
            case BOTTON1_POS:
                buttons.get(BOTTON1_POS - 1).setEnabled(false);
                buttons.get(BOTTON5_POS).setEnabled(false);
                buttons.get(BOTTON2_POS).setEnabled(false);
                break;
            case BOTTON2_POS:
                buttons.get(BOTTON1_POS).setEnabled(false);
                break;
            case BOTTON3_POS:
                buttons.get(BOTTON2_POS).setEnabled(false);
                buttons.get(BOTTON1_POS - 1).setEnabled(false);
                buttons.get(BOTTON3_POS).setEnabled(false);
                break;
            case BOTTON4_POS:
                buttons.get(BOTTON3_POS).setEnabled(false);
                buttons.get(BOTTON5_POS).setEnabled(false);
                buttons.get(BOTTON2_POS).setEnabled(false);
                break;
            case BOTTON5_POS:
                buttons.get(BOTTON4_POS).setEnabled(false);
                break;
            case BOTTON6_POS:
                buttons.get(BOTTON5_POS).setEnabled(false);
                buttons.get(BOTTON1_POS - 1).setEnabled(false);
                buttons.get(BOTTON3_POS).setEnabled(false);
                break;
            default:
                break;
        }
    }

    /**
     * This method resets the net panel to the initial state.
     */
    public void reset() {
        buttons.forEach(x -> {
            x.setBackground(null);
            x.setOpaque(false);
            x.setEnabled(false);
        });
        this.count = 0;
        this.setContinueButton(false);
        this.kickButton.setEnabled(false);
        label.setText("");
    }

    /**
     *  {@inheritDoc}.
     */
    @Override
    @SuppressFBWarnings 
    public void paintComponent(final Graphics g) {
        super.paintComponent(g);
        final Graphics2D g2d = (Graphics2D) g;
        g2d.drawImage(this.image, 0, label.getSize().height, getWidth(), getHeight(), null);
    }

    /**
     * animetes the dice and resolves the event after a certain time, also updating the UI.
     */
    private void animateAndResolve() {
        final Random rnd = new Random();
        final long startTime = System.currentTimeMillis();
        final Timer animTimer = new Timer(DELAY, null);

        this.kickButton.setEnabled(false);
        animTimer.addActionListener(e -> {
            label.setText("Kicking the penalty..." + (rnd.nextInt(BOUND) + 1));
            if (System.currentTimeMillis() - startTime > TIME_WAIT) {
                final boolean goal = this.controller.kickPenalty();
                if (goal) {
                    label.setText("Shoot the penalty in position " + this.controller.getLastShootPosition() + " GOAL!!!");
                } else {
                    label.setText("Shoot the penalty in position " + this.controller.getLastShootPosition() + " NO GOAL");
                }
                this.setContinueButton(true);
                animTimer.stop();
            }
        });
        animTimer.start();
    }

    /**
     * This method sets the continue button in the south of the panel and removes the kick button, or vice versa.
     * 
     * @param b true to set the continue button, false to set the kick button.
     */
    private void setContinueButton(final boolean b) {
        if (b) {
            this.remove(kickButton);
            this.add(continueButton, BorderLayout.SOUTH);
        } else {
            this.remove(continueButton);
            this.add(kickButton, BorderLayout.SOUTH);
        }
        this.revalidate();
        this.repaint();
    }
}
