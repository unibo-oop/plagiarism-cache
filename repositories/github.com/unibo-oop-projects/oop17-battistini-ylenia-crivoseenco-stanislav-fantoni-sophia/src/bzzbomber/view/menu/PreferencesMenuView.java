package bzzbomber.view.menu;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

import bzzbomber.game.Game;
import bzzbomber.view.GenericView;
import bzzbomber.view.Music;

/**
 * 
 * This class create a Preferences Menu.
 *
 */
public final class PreferencesMenuView implements GenericView {

    private final JFrame frame;
    private static final int TOPBOTTOM = 6;
    private static final int LEFTBOTTOM = 0;
    private static final int BOTTOM = 6;
    private static final int RIGHTPANE = 6;
    private static final double WINDOWSWIDHT = 0.65;
    private static final double WINDOWSHEIGHT = 0.7;
    private final Music music = new Music();

    /***
     * This class create a music's menu and set Key settings. The constructor
     * using @GenericView class and @GUIFactory class to create JButton JFrame and
     * so one.
     * 
     * The musicOn button and musicOff button have listener. When the musicOn button
     * 'll pressed 'll called musicPlay method in @Music class.
     * 
     * When the musicOff button 'll pressed 'll called stopclip method in @Music
     * class.
     * 
     * When the wasdON button 'll pressed 'll played with wasd key. When the wasdOFF
     * button 'll pressed 'll played with arrows key. Default: arrows key.
     * 
     */

    public PreferencesMenuView() {

        final JLabel label;
        final GUIFactory menu;
        menu = new GUIFactoryImpl();
        label = new JLabel("MUSIC:");
        label.setFont(GUIFactoryImpl.BUTTON_FONT);
        label.setBackground(Color.WHITE);
        label.setForeground(Color.RED);
        this.frame = menu.createBasicFrame();
        this.frame.setBackground(Color.WHITE);

        final JPanel buttonpanel = new JPanel(new GridBagLayout());
        buttonpanel.setBackground(Color.WHITE);

        final JButton musicOn = menu.createButton("On", "/preferences/Onmusic.png");
        final JButton musicOff = menu.createButton("Off", "/preferences/ofMusic.png");
        final GridBagConstraints constant = new GridBagConstraints();
        constant.gridy = 0;
        constant.insets = new Insets(TOPBOTTOM, LEFTBOTTOM, BOTTOM, RIGHTPANE);
        constant.fill = GridBagConstraints.HORIZONTAL;
        buttonpanel.add(musicOn, constant);
        constant.gridy++;
        buttonpanel.add(musicOff, constant);

        final JPanel imagepane = new JPanel(new BorderLayout());
        imagepane.setBackground(Color.WHITE);
        final JLabel lbl = new JLabel();
        lbl.setBackground(Color.WHITE);
        final ImageIcon imageicon = new ImageIcon(menu.createPathImage("/preferences/BombermanMusic.jpg"));
        lbl.setIcon(imageicon);
        imagepane.add(label, BorderLayout.BEFORE_FIRST_LINE);
        imagepane.add(lbl, BorderLayout.EAST);
        imagepane.add(buttonpanel, BorderLayout.WEST);

        musicOn.addActionListener(e -> {
            this.music.musicPlay();
        });

        musicOff.addActionListener(e -> {
            this.music.musicStop();
        });

        this.frame.getContentPane().add(imagepane);
        this.frame.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
        this.frame.setVisible(false);
        this.frame.setSize((int) (Game.WINDOW_WIDTH * WINDOWSWIDHT), (int) (Game.WINDOW_HEIGHT * WINDOWSHEIGHT));
        this.frame.setLocationRelativeTo(null);

    }

    @Override
    public void show() {
        this.frame.setVisible(true);
    }

    /**
     * This method get class @Music .
     * 
     * @return @Music 's class .
     */
    public Music getMusicClass() {
        return this.music;
    }

}
