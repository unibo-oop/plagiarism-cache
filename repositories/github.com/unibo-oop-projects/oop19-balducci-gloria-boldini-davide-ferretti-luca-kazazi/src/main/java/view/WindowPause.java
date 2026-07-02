package view;

import java.awt.Color;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.Point;
import java.awt.Robot;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.UIManager;

import application.StealthNinja;

/**
 * Creates the window pause.
 * 
 *
 */
public class WindowPause {

    private static final int SCREEN_WIDTH = Toolkit.getDefaultToolkit().getScreenSize().width;
    private static final int SCREEN_HEIGHT = Toolkit.getDefaultToolkit().getScreenSize().height;

    private final MyFrame frame;

    /**
     * Constructor for WindowPause.
     */
    public WindowPause() {

        final ImageIcon title = new ImageIcon(ClassLoader.getSystemResource("menu/attention.png"));

        final ImageIcon swords = new ImageIcon(ClassLoader.getSystemResource("menu/cornice.png"));

        final JPanel p = new JPanel(new GridBagLayout());
        final GridBagConstraints cnst = new GridBagConstraints();
        final JLabel message = new JLabel();
        final JButton exit = new JButton("Exit");
        final JButton restart = new JButton("Restart");
        final JButton resume = new JButton("Resume");
        final JLabel frames = new JLabel();

        cnst.gridy = 0;
        cnst.insets = new Insets(SCREEN_WIDTH / 70, 40, SCREEN_HEIGHT / 70, 40);
        cnst.fill = GridBagConstraints.HORIZONTAL;

        p.setBackground(Color.black);
        p.setOpaque(true);
        message.setHorizontalAlignment(JLabel.CENTER);
        message.setVerticalAlignment(JLabel.CENTER);
        message.setIcon(title);
        frames.setIcon(swords);
        setButton(resume);
        setButton(restart);
        setButton(exit);

        exit.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(final ActionEvent e) {
                frame.dispose();
                StealthNinja.MODEL_ACTION.interruptLevel();
                StealthNinja.GUICONTROLLER.exitButton();
            }

        });

        restart.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(final ActionEvent e) {
                frame.dispose();
                StealthNinja.GUICONTROLLER.getWinGame().removeLevel();
                StealthNinja.MODEL_ACTION.restartLevel();
                StealthNinja.GUICONTROLLER.getWinGame().addLevel();

            }

        });

        resume.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(final ActionEvent e) {
                frame.dispose();
                StealthNinja.MODEL_ACTION.resumeLevel();
            }

        });

        p.add(message, cnst);
        cnst.gridy++;
        p.add(resume, cnst);
        cnst.gridy++;
        p.add(restart, cnst);
        cnst.gridy++;
        p.add(exit, cnst);
        cnst.gridy++;
        p.add(frames, cnst);

        try {
            UIManager.setLookAndFeel(UIManager.getCrossPlatformLookAndFeelClassName());
        } catch (Exception e) {
            e.printStackTrace();
        }

        this.frame = new MyFrame(new GridBagLayout(), SCREEN_WIDTH / 4, SCREEN_HEIGHT / 4);
        frame.getMainPanel().add(p);
        frame.setUndecorated(true);
        frame.pack();
        frame.setLocationRelativeTo(null);
        frame.setAlwaysOnTop(true);

        frame.addWindowListener(new WindowAdapter() {
            @Override
            public void windowOpened(final WindowEvent e) {
                final Point pos = resume.getLocationOnScreen();
                pos.x += resume.getWidth() / 2;
                pos.y += resume.getHeight() / 2;
                try {
                    final Robot bot = new Robot();
                    bot.mouseMove(pos.x, pos.y);
                } catch (Exception e2) {
                    e2.printStackTrace();
                }
            }
        });
    }

    /**
     * set the visibility of the window.
     */
    public void show() {
        this.frame.setVisible(true);
    }

    /**
     * 
     * @param x height
     * @param y width
     * 
     * Set the windows size.
     */
    public void setDimensions(final int x, final int y) {
        this.frame.setSize(x, y);
        this.frame.pack();
    }

    private void setButton(final JButton button) {
        button.setBackground(Color.red);
        button.setForeground(Color.black);
        button.setOpaque(true);
        button.setFont(new Font("Arial Black", Font.PLAIN, SCREEN_WIDTH / 45));
    }
}
