package view;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.Point;
import java.awt.Robot;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.UIManager;

import application.StealthNinja;
import scores.CreateScores;

/**
 * Creates window after winning.
 * 
 *
 */
public class WindowFinish implements UserInterface {

    private final MyFrame frame;
    private final WindowPlay windowPlay;
    private final CreateScores scores;
    private static final int SCREEN_WIDTH = Toolkit.getDefaultToolkit().getScreenSize().width;
    private static final int SCREEN_HEIGHT = Toolkit.getDefaultToolkit().getScreenSize().height;
    private final String separator = System.getProperty("line.separator");

    /**
     * @param #STATIC_FIELD static constant to get File separator
     */
    public static final String SEP = File.separator;

    /**
     * @param #STATIC_FIELD static constant to get scores.txt path
     */
    public static final String FILE_NAME = System.getProperty("user.home") + SEP + "scores";

    /**
     * Constructor for WindowFinish.
     */
    public WindowFinish() {
        this.frame = new MyFrame(new BorderLayout(), SCREEN_WIDTH / 3, SCREEN_HEIGHT / 3);
        this.windowPlay = new WindowPlay();
        this.scores = new CreateScores();
    }

    @Override
    public final void createWindow() {
        final ImageIcon starImage = new ImageIcon(ClassLoader.getSystemResource("menu/star1.png"));

        final JPanel p = new JPanel(new BorderLayout());
        final JPanel pInternal = new JPanel(new GridLayout(1, 2));
        final JLabel msg = new JLabel(" You have gained:");
        final JLabel star1 = new JLabel();
        final JLabel star2 = new JLabel();
        final JLabel star3 = new JLabel();
        final JButton next = new JButton("Next Level");
        final JButton select = new JButton("Select Level");

        p.setBackground(Color.yellow);
        p.setOpaque(true);
        msg.setForeground(Color.black);
        msg.setBackground(Color.yellow);
        msg.setFont(new Font("Impact", Font.PLAIN, SCREEN_WIDTH / 45));
        msg.setOpaque(true);
        star1.setBackground(Color.black);
        star1.setOpaque(true);
        star2.setBackground(Color.black);
        star2.setOpaque(true);
        star3.setBackground(Color.black);
        star3.setOpaque(true);
        setButton(next);
        setButton(select);

        next.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(final ActionEvent e) {
                frame.dispose();

                if (StealthNinja.MODEL_ACTION.getLevelController().getLevel() == 5) {
                    windowPlay.createWindow();
                    windowPlay.setDimensions(SCREEN_WIDTH / 3, SCREEN_WIDTH / 2);
                    windowPlay.show();
                } else {
                    StealthNinja.GUICONTROLLER.getWinGame().removeLevel();
                    StealthNinja.MODEL_ACTION.nextLevel();
                    StealthNinja.GUICONTROLLER.getWinGame().addLevel();

                }
            }
        });

        select.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(final ActionEvent e) {
                frame.dispose();
                StealthNinja.MODEL_ACTION.interruptLevel();
                StealthNinja.GUICONTROLLER.getWinGame().removeLevel();
                windowPlay.createWindow();
                windowPlay.setDimensions(SCREEN_WIDTH / 3, (SCREEN_HEIGHT + 600) / 2);
                windowPlay.show();
            }
        });


        StealthNinja.MODEL_ACTION.getLevelController().getTime().stop();

        final int star = StealthNinja.MODEL_ACTION.getLevelController().getTime().getStarsGained();
        if (star == 1) {
            star1.setIcon(starImage);
        } else if (star == 2) {
            star1.setIcon(starImage);
            star2.setIcon(starImage);
        } else if (star == 3) {
            star1.setIcon(starImage);
            star2.setIcon(starImage);
            star3.setIcon(starImage);
        }

        try (FileWriter fw = new FileWriter(FILE_NAME, true);
                BufferedWriter bw = new BufferedWriter(fw);
                PrintWriter out = new PrintWriter(bw)) {
            out.print("Level: " + StealthNinja.MODEL_ACTION.getLevelController().getLevel() + this.separator + "Stars gained: " + star + this.separator + "Time: " + " ( ");
            out.print(StealthNinja.MODEL_ACTION.getLevelController().getTime().getTimerTask().getHours() + ":"
                    + StealthNinja.MODEL_ACTION.getLevelController().getTime().getTimerTask().getMins() + ":"
                    + StealthNinja.MODEL_ACTION.getLevelController().getTime().getTimerTask().getSecs() + ")" + this.separator + this.separator);
        } catch (IOException e) {
            e.printStackTrace();
        }

        try {
            scores.calculateScores(StealthNinja.MODEL_ACTION.getLevelController().getTime().getTimerTask().getHours(),
                    StealthNinja.MODEL_ACTION.getLevelController().getTime().getTimerTask().getMins(),
                    StealthNinja.MODEL_ACTION.getLevelController().getTime().getTimerTask().getSecs(),
                    StealthNinja.MODEL_ACTION.getLevelController().getLevel());
        } catch (IOException e1) {
            e1.printStackTrace();
        }

        msg.setHorizontalAlignment(JLabel.CENTER);
        msg.setVerticalAlignment(JLabel.CENTER);
        star2.setHorizontalAlignment(JLabel.CENTER);

        pInternal.add(next);
        pInternal.add(select);

        p.add(msg, BorderLayout.NORTH);
        p.add(star1, BorderLayout.WEST);
        p.add(star2, BorderLayout.CENTER);
        p.add(star3, BorderLayout.EAST);
        p.add(pInternal, BorderLayout.SOUTH);

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
                final Point pos = select.getLocationOnScreen();
                pos.x += select.getWidth() / 2;
                pos.y += select.getHeight() / 2;
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

    private void setButton(final JButton button) {
        button.setBackground(Color.yellow);
        button.setForeground(Color.black);
        button.setOpaque(true);
        button.setFont(new Font("Impact", Font.PLAIN, SCREEN_WIDTH / 45));
    }

}
