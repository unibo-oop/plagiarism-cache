package it.unibo.michelito.view.homeview;

import it.unibo.michelito.controller.homecontroller.api.ViewControllerListener;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JOptionPane;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.Dimension;
import java.awt.Toolkit;
import java.awt.Font;
import java.awt.Color;
import java.awt.FlowLayout;
import java.io.Serial;

/**
 * Represents the view of the home menu.
 */
public final class HomeView extends JFrame {
    @Serial
    private static final long serialVersionUID = 1L;
    /**
     * Scaling factor for the window size.
     */
    public static final double SCALING = 2.5;
    /**
     * Scaling factor for the button size.
     */
    public static final int BUTTON_X_SCALING = 6;
    /**
     * Scaling factor for the button size.
     */
    public static final int BUTTON_Y_SCALING = 16;
    /**
     * Font size for the title.
     */
    public static final int FONT_SIZE = 33;
    /**
     * Height of the rigid area.
     */
    public static final int RIGID_AREA_HEIGHT = 20;

    /**
     * Creates a new instance of {@link HomeView}.
     *
     * @param controller the controller to be used.
     */
    public HomeView(final ViewControllerListener controller) {
        super();
        final Dimension systemDimension = Toolkit.getDefaultToolkit().getScreenSize();

        final JPanel mainPanel = new JPanel();
        mainPanel.setLayout(new BoxLayout(mainPanel, BoxLayout.Y_AXIS));

        final JButton startButton = new JButton("Start Game");
        final JButton exitButton = new JButton("Quit");

        final JPanel titlePanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        final JLabel titleLabel = new JLabel("Michelito El Esqueleto Explosivo");
        titleLabel.setFont(new Font("Papyrus", Font.BOLD, FONT_SIZE));
        titlePanel.add(titleLabel);

        final JPanel buttonPanel1 = new JPanel(new FlowLayout(FlowLayout.CENTER));
        final JPanel buttonPanel2 = new JPanel(new FlowLayout(FlowLayout.CENTER));

        final Dimension buttonSize = new Dimension(systemDimension.width / BUTTON_X_SCALING,
                systemDimension.width / BUTTON_Y_SCALING);
        startButton.setPreferredSize(buttonSize);
        exitButton.setPreferredSize(buttonSize);

        buttonPanel1.add(startButton);
        buttonPanel2.add(exitButton);

        this.addWindowListener(new WindowAdapter() {
            /**
             * {@inheritDoc}
             */
            @Override
            public void windowClosing(final WindowEvent e) {
                final int response = JOptionPane.showConfirmDialog(
                        HomeView.this,
                        "Are you sure you want to exit?",
                        "Confirm",
                        JOptionPane.YES_NO_OPTION,
                        JOptionPane.WARNING_MESSAGE
                );
                if (response == JOptionPane.YES_OPTION) {
                    controller.quit();
                }
            }
        });

        mainPanel.add(Box.createVerticalGlue());
        mainPanel.add(titlePanel);
        mainPanel.add(Box.createRigidArea(new Dimension(0, RIGID_AREA_HEIGHT)));
        mainPanel.add(buttonPanel1);
        mainPanel.add(Box.createRigidArea(new Dimension(0, RIGID_AREA_HEIGHT)));
        mainPanel.add(buttonPanel2);
        mainPanel.add(Box.createRigidArea(new Dimension(0, RIGID_AREA_HEIGHT)));
        mainPanel.add(Box.createVerticalGlue());

        startButton.addActionListener(e -> controller.switchToGame());
        exitButton.addActionListener(e -> controller.quit());

        mainPanel.setBackground(Color.cyan);
        buttonPanel1.setBackground(Color.cyan);
        buttonPanel2.setBackground(Color.cyan);
        titlePanel.setBackground(Color.cyan);

        startButton.setForeground(Color.green);
        exitButton.setForeground(Color.red);

        configureFrame(mainPanel, systemDimension);
    }

    /**
     * Configures the main JFrame properties.
     *
     * @param mainPanel the main panel to be set as content pane.
     * @param screenSize the dimensions of the screen.
     */
    private void configureFrame(final JPanel mainPanel, final Dimension screenSize) {
        this.setTitle("Michelito");
        this.setResizable(false);
        this.setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);
        this.setContentPane(mainPanel);
        this.setLocationRelativeTo(null);
        this.setSize((int) (screenSize.width / SCALING), (int) (screenSize.height / SCALING));
        this.setVisible(true);
    }
}
