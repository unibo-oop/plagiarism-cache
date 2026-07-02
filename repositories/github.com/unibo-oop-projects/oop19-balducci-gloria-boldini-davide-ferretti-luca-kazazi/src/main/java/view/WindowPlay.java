package view;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.ToolTipManager;
import javax.swing.UIManager;
import javax.swing.border.TitledBorder;
import application.StealthNinja;

/**
 * Creates window for selecting level.
 * 
 *
 */
public class WindowPlay implements UserInterface {

    /**
     * @value #STATIC_FIELD static variable string separator
     */
    public static final String SEP = File.separator;

    /**
     * @value #STATIC_FIELD static variable scores path
     */
    public static final String FILE_NAME = System.getProperty("user.home") + SEP + "scores";

    private final MyFrame frame;
    private static final int SCREEN_WIDTH = Toolkit.getDefaultToolkit().getScreenSize().width;
    private static final int SCREEN_HEIGHT = Toolkit.getDefaultToolkit().getScreenSize().height;

    /**
     * Constructor for WindowPlay.
     */
    public WindowPlay() {
        this.frame = new MyFrame(new BorderLayout(), SCREEN_WIDTH / 2, SCREEN_HEIGHT / 2);
    }

    @Override
    public final void createWindow() {

        final ImageIcon enemy1Image = new ImageIcon(ClassLoader.getSystemResource("menu/enemy.png"));

        final ImageIcon enemy2Image = new ImageIcon(ClassLoader.getSystemResource("menu/enemy2.png"));

        final ImageIcon enemy3Image = new ImageIcon(ClassLoader.getSystemResource("menu/enemy3.png"));

        final ImageIcon enemy4Image = new ImageIcon(ClassLoader.getSystemResource("menu/enemy4.png"));

        final ImageIcon enemy5Image = new ImageIcon(ClassLoader.getSystemResource("menu/enemy5.png"));

        final JPanel p = new JPanel(new BorderLayout());
        final JPanel pInternal = new JPanel(new GridLayout(5, 2));
        final TitledBorder title = new TitledBorder("SELECT LEVEL");
        final JLabel enemy1 = new JLabel();
        final JLabel enemy2 = new JLabel();
        final JLabel enemy3 = new JLabel();
        final JLabel enemy4 = new JLabel();
        final JLabel enemy5 = new JLabel();
        final JButton level1 = new JButton("LEVEL 1");
        final JButton level2 = new JButton("LEVEL 2");
        final JButton level3 = new JButton("LEVEL 3");
        final JButton level4 = new JButton("LEVEL 4");
        final JButton level5 = new JButton("LEVEL 5");
        final JButton back = new JButton("Go Back");

        Image scaledEnemy1Image = enemy1Image.getImage().getScaledInstance(SCREEN_WIDTH / 10, SCREEN_HEIGHT / 6,
                Image.SCALE_DEFAULT);
        enemy1Image.setImage(scaledEnemy1Image);
        Image scaledEnemy2Image = enemy2Image.getImage().getScaledInstance(SCREEN_WIDTH / 10, SCREEN_HEIGHT / 6,
                Image.SCALE_DEFAULT);
        enemy2Image.setImage(scaledEnemy2Image);
        Image scaledEnemy3Image = enemy3Image.getImage().getScaledInstance(SCREEN_WIDTH / 10, SCREEN_HEIGHT / 6,
                Image.SCALE_DEFAULT);
        enemy3Image.setImage(scaledEnemy3Image);
        Image scaledEnemy4Image = enemy4Image.getImage().getScaledInstance(SCREEN_WIDTH / 10, SCREEN_HEIGHT / 6,
                Image.SCALE_DEFAULT);
        enemy4Image.setImage(scaledEnemy4Image);
        Image scaledEnemy5Image = enemy5Image.getImage().getScaledInstance(SCREEN_WIDTH / 10, SCREEN_HEIGHT / 6,
                Image.SCALE_DEFAULT);
        enemy5Image.setImage(scaledEnemy5Image);

        enemy1.setIcon(enemy1Image);
        enemy2.setIcon(enemy2Image);
        enemy3.setIcon(enemy3Image);
        enemy4.setIcon(enemy4Image);
        enemy5.setIcon(enemy5Image);

        enemy1.setToolTipText("<html>" + "The adventure has started!" + "<br>"
                + "Find the exit showing your stealth abilities," + "<br>" + "avoid enemies and have a look around."
                + "<br>" + "Some objects may help you and others may not." + "</html>");

        enemy2.setToolTipText("<html>" + "Well done, now let's make it a little bit harder." + "<br>"
                + "It's not that easy escaping from this cave." + "<br>" + "Good luck." + "</html>");

        enemy3.setToolTipText("<html>" + "Welcome to the underworld." + "<br>" + "Make sure you come back alive."
                + "<br>" + "New friends are waiting for you..." + "</html>");

        enemy4.setToolTipText("<html>" + "That was just a warming up." + "<br>" + "A mystic creature has appeared,"
                + "<br>" + "hide yourself quickly!" + "<br>" + "Don't play with fire." + "</html>");

        enemy5.setToolTipText("<html>" + "Winter has come." + "<br>" + "Archaic wizard statues have been revived,"
                + "<br>" + "don't underrate the ancient power." + "<br>" + "The last level between you and the glory."
                + "</html>");

        UIManager.put("ToolTip.background", Color.black);
        UIManager.put("ToolTip.foreground", Color.red.brighter());
        UIManager.put("ToolTip.font", new Font("Impact", Font.PLAIN, 20));
        ToolTipManager.sharedInstance().setDismissDelay(10000);

        back.setBackground(Color.gray);
        back.setForeground(Color.white);
        back.setOpaque(true);
        back.setFont(new Font("Arial Black", Font.PLAIN, SCREEN_WIDTH / 100));

        level1.setBackground(Color.yellow);
        level1.setFont(new Font("Arial Black", Font.PLAIN, SCREEN_WIDTH / 70));
        level1.setOpaque(true);
        level2.setBackground(Color.orange);
        level2.setFont(new Font("Arial Black", Font.PLAIN, SCREEN_WIDTH / 70));
        level2.setOpaque(true);
        level3.setBackground(Color.orange.darker());
        level3.setFont(new Font("Arial Black", Font.PLAIN, SCREEN_WIDTH / 70));
        level3.setOpaque(true);
        level4.setBackground(Color.red);
        level4.setFont(new Font("Arial Black", Font.PLAIN, SCREEN_WIDTH / 70));
        level4.setOpaque(true);
        level5.setBackground(Color.red.darker());
        level5.setFont(new Font("Arial Black", Font.PLAIN, SCREEN_WIDTH / 70));
        level5.setOpaque(true);
        title.setTitleColor(Color.black);
        title.setTitleFont(new Font("Arial Black", Font.PLAIN, SCREEN_WIDTH / 80));
        p.setBorder(title);
        p.setBackground(Color.white);
        p.setOpaque(true);
        pInternal.setBackground(Color.white);
        pInternal.setOpaque(true);

        level1.addActionListener((ActionListener) new ActionListener() {

            @Override
            public void actionPerformed(final ActionEvent e) {
                frame.dispose();
                StealthNinja.MODEL_ACTION.initLevel(1);
                StealthNinja.GUICONTROLLER.getWinGame().addLevel();

            }
        });

        level2.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(final ActionEvent e) {
                frame.dispose();
                StealthNinja.MODEL_ACTION.initLevel(2);
                StealthNinja.GUICONTROLLER.getWinGame().addLevel();

            }
        });

        level3.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(final ActionEvent e) {
                frame.dispose();
                StealthNinja.MODEL_ACTION.initLevel(3);
                StealthNinja.GUICONTROLLER.getWinGame().addLevel();

            }
        });

        level4.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(final ActionEvent e) {
                frame.dispose();
                StealthNinja.MODEL_ACTION.initLevel(4);
                StealthNinja.GUICONTROLLER.getWinGame().addLevel();

            }
        });
        level5.addActionListener((ActionListener) new ActionListener() {

            @Override
            public void actionPerformed(final ActionEvent e) {
                frame.dispose();
                StealthNinja.MODEL_ACTION.initLevel(5);
                StealthNinja.GUICONTROLLER.getWinGame().addLevel();

            }
        });

        back.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(final ActionEvent e) {
                frame.dispose();
                StealthNinja.GUICONTROLLER.goBackButton();
                StealthNinja.GUICONTROLLER.writeName(StealthNinja.GUICONTROLLER.getUserInterface().getJTextField());
            }
        });

        p.add(pInternal, BorderLayout.CENTER);
        p.add(back, BorderLayout.SOUTH);

        pInternal.add(level1);
        pInternal.add(enemy1);

        pInternal.add(enemy2);
        pInternal.add(level2);

        pInternal.add(level3);
        pInternal.add(enemy3);

        pInternal.add(enemy4);
        pInternal.add(level4);

        pInternal.add(level5);
        pInternal.add(enemy5);

        try {
            UIManager.setLookAndFeel(UIManager.getCrossPlatformLookAndFeelClassName());
        } catch (Exception e) {
            e.printStackTrace();
        }

        frame.getMainPanel().add(p);
        frame.setUndecorated(true);
    }

    @Override
    public final void setDimensions(final int x, final int y) {
        this.frame.setSize(x, y);
    }

    @Override
    public final void show() {
        this.frame.setLocationRelativeTo(null);
        this.frame.setVisible(true);
    }
}
