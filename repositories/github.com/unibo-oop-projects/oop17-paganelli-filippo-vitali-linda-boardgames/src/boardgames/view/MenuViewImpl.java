package boardgames.view;

import java.awt.Dimension;
import java.awt.Font;
import java.awt.FontFormatException;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.Toolkit;

import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.IOException;
import java.io.InputStream;

import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.WindowConstants;

import boardgames.controller.GameControllerImpl;

import boardgames.utility.BoardGame;

/**
 * Class that launch the boot menu.
 *
 **/
public final class MenuViewImpl implements MenuView {

    private final Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();

    private static final double PERC_RESIZE_MENU = 0.3;

    private static final int DIM_FONT = (int) (Toolkit.getDefaultToolkit().getScreenSize().getHeight() * 0.08);

    private static final String GAME_NAME = "Board Games";
    private static final String CHECKBOX_MESSAGE = "Play with time";
    private static final String CHESS = "Chess";
    private static final String CHECKERS = "Checkers";
    private static final String STATISTICS = "Statistics";
    private static final String EXIT = "Exit";


    private final JFrame frame = new JFrame(GAME_NAME);
    private final JPanel panel = new JPanel();
    private final JLabel label = new JLabel(GAME_NAME);
    private final JCheckBox checkBox = new JCheckBox(CHECKBOX_MESSAGE);

    private GameControllerImpl controller;
    private BoardGame gameType;


    /**
     * @param c 
     */
    public MenuViewImpl(final GameControllerImpl c) {
        this.controller = c;

        final JButton chess = new JButton(CHESS);
        final JButton checkers = new JButton(CHECKERS);
        final JButton stats = new JButton(STATISTICS);
        final JButton exit = new JButton(EXIT);

        this.frame.setSize((int) (this.screenSize.getWidth() * PERC_RESIZE_MENU),
                (int) (this.screenSize.getHeight() * PERC_RESIZE_MENU));
        this.frame.setDefaultCloseOperation(WindowConstants.DO_NOTHING_ON_CLOSE);

        this.frame.addWindowListener(new WindowAdapter() {
            public void windowClosing(final WindowEvent e) {
                final int n = JOptionPane.showConfirmDialog(frame, "Do you really want to quit ?", "Quitting ..",
                        JOptionPane.YES_NO_OPTION);
                if (n == JOptionPane.YES_OPTION) {
                    System.exit(0);
                }
            }
        });

        try {
            label.setFont(this.getFont());
        } catch (FontFormatException | IOException e1) {
            System.out.println("File not found");
        }

        panel.setLayout(new GridBagLayout());

        final GridBagConstraints cnst = new GridBagConstraints();
        cnst.insets = new Insets(3, 3, 3, 3);

        cnst.gridy = 0;
        panel.add(label, cnst);
        cnst.gridy++;
        panel.add(checkBox, cnst);
        cnst.gridy++;
        cnst.anchor = GridBagConstraints.WEST;
        panel.add(chess, cnst);
        cnst.gridx = 0;
        cnst.anchor = GridBagConstraints.EAST;
        panel.add(checkers, cnst);
        cnst.fill = GridBagConstraints.HORIZONTAL;
        cnst.gridx = 0;
        cnst.gridy++;
        panel.add(stats, cnst);
        cnst.gridy++;
        panel.add(exit, cnst);

        final ActionListener ac = e -> {
            final JButton jb = (JButton) e.getSource();
            this.gameType = jb.getText().equals("Chess") ? BoardGame.CHESS : BoardGame.CHECKERS;
            this.controller.setGameConfiguration(gameType, this.playWithTime());
            this.frame.dispose();
        };

        chess.addActionListener(ac);

        checkers.addActionListener(ac);

        stats.addActionListener(e -> this.frame.setVisible(false));

        exit.addActionListener(e -> System.exit(0));

        this.frame.getContentPane().add(panel);
        this.frame.setLocationRelativeTo(null);
    }

    @Override
    public void showMenu() {
        this.frame.setVisible(true);
    }

    /**
     * @return font from classpath
     * @throws FontFormatException
     * @throws IOException
     */
    private Font getFont() throws FontFormatException, IOException {
        final InputStream in = MenuViewImpl.class.getResourceAsStream("/font/theBreakdown.ttf");
        final Font font = Font.createFont(Font.TRUETYPE_FONT, in);
        return font.deriveFont(Font.PLAIN, DIM_FONT);
    }

    /**
     * @return true if is a game with timer
     */
    private boolean playWithTime() {
        return this.checkBox.isSelected();
    }

}

