package view;

import java.awt.BorderLayout;

import java.awt.Color;
import java.awt.Point;
import java.awt.Robot;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.File;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.UIManager;

import application.StealthNinja;

/**
 * 
 * Implements of UserInterface.
 *
 * Creates the window after the death.
 */
public class WindowDeath implements UserInterface {

    /**
     * @value #STATIC_FIELD static variable string separator for path
     */
    public static final String SEP = File.separator;

    private final MyFrame frame;
    private static final int SCREEN_WIDTH = Toolkit.getDefaultToolkit().getScreenSize().width;
    private static final int SCREEN_HEIGHT = Toolkit.getDefaultToolkit().getScreenSize().height;

    /**
     * 
     * constructor for window death.
     */
    public WindowDeath() {
        this.frame = new MyFrame(new BorderLayout(), SCREEN_WIDTH / 3, SCREEN_HEIGHT / 3);
    }

    @Override
    public final void createWindow() {

        final ImageIcon overImage = new ImageIcon(ClassLoader.getSystemResource("menu/over.png"));

        final ImageIcon retryImage = new ImageIcon(ClassLoader.getSystemResource("menu/retry.png"));

        final ImageIcon backImage = new ImageIcon(ClassLoader.getSystemResource("menu/back.png"));

        final JPanel p = new JPanel(new BorderLayout());
        final JLabel over = new JLabel();
        final JButton retry = new JButton();
        final JButton back = new JButton();

        over.setBackground(Color.black);
        over.setOpaque(true);
        retry.setBackground(Color.black);
        retry.setOpaque(true);
        back.setBackground(Color.black);
        back.setOpaque(true);
        over.setHorizontalAlignment(JLabel.CENTER);
        over.setVerticalAlignment(JLabel.CENTER);
        p.setBackground(Color.BLACK);

        over.setIcon(overImage);
        retry.setIcon(retryImage);
        back.setIcon(backImage);

        retry.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(final ActionEvent e) {
                frame.dispose();
                StealthNinja.GUICONTROLLER.getWinGame().removeLevel();
                StealthNinja.MODEL_ACTION.restartLevel();
                StealthNinja.GUICONTROLLER.getWinGame().addLevel();

            }
        });

        back.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(final ActionEvent e) {
                frame.dispose();
                StealthNinja.GUICONTROLLER.exitButton();
                StealthNinja.MODEL_ACTION.interruptLevel();
            }
        });

        p.add(over, BorderLayout.NORTH);
        p.add(retry, BorderLayout.CENTER);
        p.add(back, BorderLayout.SOUTH);
        try {
            UIManager.setLookAndFeel(UIManager.getCrossPlatformLookAndFeelClassName());
        } catch (Exception e) {
            e.printStackTrace();
        }
        frame.getMainPanel().add(p);
        frame.setUndecorated(true);
        frame.setAlwaysOnTop(true);
        frame.addWindowListener(new WindowAdapter() {
            @Override
            public void windowOpened(final WindowEvent e) {
                final Point pos = retry.getLocationOnScreen();
                pos.x += retry.getWidth() / 2;
                pos.y += retry.getHeight() / 2;
                try {
                    final Robot bot = new Robot();
                    bot.mouseMove(pos.x, pos.y);
                } catch (Exception e2) {
                    e2.printStackTrace();
                }
            }
        });
    }

    @Override
    public final void setDimensions(final int x, final int y) {
        this.frame.setSize(x, y);
    }

    @Override
    public final void show() {
        this.frame.setVisible(true);
    }

}
