package view.panels;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import utility.GameValues;
import utility.Utilities;
import view.StartMenu;

/**
 * @author Paolo
 *
 */
public class SetOptions extends JPanel {

    private static boolean audio = GameValues.ISSOUNDENABLED;
    /**
     * 
     */
    private static final long serialVersionUID = 1L;
    /**
     * @param frame **The frame containing this panel**
     */
    public SetOptions(final JFrame frame) {
        super(new BorderLayout());
        final JPanel panel = new JPanel(new GridLayout(2, 2, 10, 10));
        panel.setBackground(Color.GRAY);
        final JButton comandi = createButton("Controls");
        final JButton music = createButton("Music : " + (audio ? "On " : "Off"));
        final JButton crediti = createButton("Credits");
        final JButton indietro = createButton("Back");
        final JLabel label = new JLabel();
        label.setText("Options");
        label.setFont(new Font("Options", Font.BOLD, GameValues.FONT_MEDIUM));
        label.setForeground(Color.RED);

        comandi.addActionListener(e -> {
            Utilities.changePanel(frame, this, new KeyBinding(frame));
        });

        music.addActionListener(e -> {
            setNotAudio();
            music.setText("Music : " + (audio ? "On " : "Off"));
            StartMenu.notifyEnableMusic(audio);
        });

        crediti.addActionListener(e -> {
            Utilities.changePanel(frame, this, new CreditsPanel(frame));
        });

        indietro.addActionListener(e -> {
                Utilities.changePanel(frame, this, new StartMenuPanel(new BorderLayout(), frame));
        });

        panel.add(comandi);
        panel.add(music);
        panel.add(crediti);
        panel.add(indietro);
        this.add(panel, BorderLayout.CENTER);
        this.add(label, BorderLayout.NORTH);
        this.setVisible(true);
        }
    /**
     * @param s **string text of this button**
     * @return **this button**
     */
    private JButton createButton(final String s) {
        return Utilities.initButton(s, Color.BLACK, Color.WHITE, GameValues.FONT_MEDIUM, new Dimension(GameValues.BUTTON_LARGE, GameValues.BUTTON_MEDIUM));
    }
    /**
     * change boolean value of audio.
     */
    private void setNotAudio() {
        audio = !audio;
    }

    /**
     * @return the boolean audio, to know if music has to start or not
     */
    public static boolean isAudio() {
        return audio;
    }
}
