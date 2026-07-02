package giocoscudetto.view.impl.initialize;

import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.GridBagLayout;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.awt.BorderLayout;
import java.awt.Color;

import javax.imageio.ImageIO;
import javax.swing.JButton;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

import giocoscudetto.controller.api.Starter;

/**
 * This class represents the home panel of the game, where the user can choose to play with bots or friends, or exit the game.
 */
public final class HomePanel extends DefaultPanelImpl {

    private static final int BUTTONS_HORIZONTAL_GAP = 80;
    private static final int BUTTON_FONT_RESIZING = 25;
    private static final Color BUTTONS_BACKGORUND = new Color(139, 90, 43);
    private static final Color BUTTONS_TEXT_COLOR = new Color(240, 220, 180);
    private static final Color MOUSE_ENTER_COLOR = new Color(198, 156, 58);
    private static final Color EXIT_TEXT_COLOR = new Color(224, 201, 166);
    private static final Color EXIT_BACKGROUND_COLOR = new Color(62, 91, 66);

    private static final long serialVersionUID = 1L;
    private final transient Starter controller;
    private final transient BufferedImage image;

    /**
     * @param controller the controller responsible for changing views
     */
    public HomePanel(final Starter controller) {
        this.controller = controller;

        this.setLayout(new BorderLayout());

        //Creating buttons to select to play with bots or friend
        final JPanel selectButtonPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, BUTTONS_HORIZONTAL_GAP, 0));
        selectButtonPanel.setOpaque(false);

        final JButton btnPlay = (JButton) createComponent(new JButton("<html>PLAY</html>"), 
                                                            getButtonFont(), BUTTONS_TEXT_COLOR, BUTTONS_BACKGORUND); 
        selectButtonPanel.add(btnPlay); 

        //Centralizing button vertically and responsively to the resolution changes
        final JPanel centerWrapper = new JPanel(new GridBagLayout());
        centerWrapper.add(selectButtonPanel);

        //Creating button to exit from the game
        final JPanel switchingButtonPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        final JButton btnExit = (JButton) createComponent(new JButton("EXIT"), getExitFont(), 
                                                            EXIT_TEXT_COLOR, EXIT_BACKGROUND_COLOR);
        switchingButtonPanel.add(btnExit);

        //Creating the listener to change the button hover background color
        final MouseAdapter hoverListener = new MouseAdapter() {

            @Override
            public void mouseEntered(final MouseEvent e) {
                e.getComponent().setBackground(MOUSE_ENTER_COLOR);
            }

            @Override
            public void mouseExited(final MouseEvent e) {
                e.getComponent().setBackground(BUTTONS_BACKGORUND);
            }
        };

        btnPlay.addMouseListener(hoverListener);

        //Adding the action listener to the buttons
        btnPlay.addActionListener(e -> {

            controller.changeView("club");

        });

        btnExit.addActionListener(e -> {
            final int confirm = JOptionPane.showConfirmDialog(this,
                        "Do you really want to quit?",
                        "QUITTING...",
                        JOptionPane.YES_NO_OPTION,
                        JOptionPane.QUESTION_MESSAGE);

                if (confirm == JOptionPane.YES_OPTION) {
                    this.controller.closeGame();
                }

        });

        //Istruction to make the panel components responsive to resolution changes
        this.addComponentListener(new java.awt.event.ComponentAdapter() {
            @Override
            public void componentResized(final java.awt.event.ComponentEvent e) {

                final int currentWidth = getWidth();

                btnPlay.setFont(new Font(FONT_SELECTED, Font.BOLD, currentWidth / BUTTON_FONT_RESIZING));
                btnExit.setFont(new Font(FONT_SELECTED, Font.BOLD, currentWidth / SWITCHER_BUTTON_FONT_RESIZING));

                revalidate();

            }
        });

        //Setting the main panels opacity on false to show the backgorund color
        centerWrapper.setOpaque(false);
        switchingButtonPanel.setOpaque(false);

        //Placing correctly the specific panels in the main one 
        this.add(centerWrapper, BorderLayout.CENTER);
        this.add(switchingButtonPanel, BorderLayout.SOUTH);

        try {
            this.image = ImageIO.read(new File("src/main/resources/images/backgrounds/home-background.jpeg"));
        } catch (final IOException e) {
            throw new IllegalStateException("Failed to load image", e);
        }
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
