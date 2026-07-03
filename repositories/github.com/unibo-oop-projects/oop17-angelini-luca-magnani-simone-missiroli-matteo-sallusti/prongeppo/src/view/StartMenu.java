package view;



import java.awt.BorderLayout;


import java.awt.Color;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.WindowConstants;
import utility.GameValues;
import utility.Utilities;
import view.panels.StartMenuPanel;

/**
 * @author Luca
 *
 */
public class StartMenu extends JFrame {
    private static final Clip CLIP = Utilities.createClip();
    /**
     * 
     */
    private static final long serialVersionUID = -4088625953306494672L;

    /**
     * 
     */
    public StartMenu() {
        super("PONG-O-COMBO");
        this.setIconImage(new GraphicElem("pong_icon.png", GameValues.FONT_LARGE, GameValues.FONT_LARGE).getImage());
        this.setSize(GameValues.PANELWIDTH, GameValues.PANELHEIGHT);
        this.setResizable(true);
        this.setDefaultCloseOperation(WindowConstants.DO_NOTHING_ON_CLOSE);
        this.addWindowListener(new WindowAdapter() {
            private JFrame frame = new JFrame();
            public void windowClosing(final WindowEvent e) {
                final int n = JOptionPane.showConfirmDialog(frame, "Do you really want to exit?", "Quitting..", JOptionPane.YES_NO_OPTION);
                if (n == JOptionPane.YES_OPTION) {
                    new ExitFrame();
                }
            }
        });
        this.getContentPane().setBackground(Color.BLACK);
        this.add(new StartMenuPanel(new BorderLayout(), this));
        try {
            final AudioInputStream audioInputStream = AudioSystem.getAudioInputStream(this.getClass().getResource("/IncrediM.wav"));
            CLIP.open(audioInputStream);
            notifyEnableMusic(GameValues.ISSOUNDENABLED);
        } catch (Exception ex) {
            System.out.println("Error with playing sound.");
            ex.printStackTrace();
        }
    }

    /**
     * 
     */
    public void open() {
        this.setVisible(true);
    }

    /**
     * @param audio **music starts if audio == true**
     */
    public static void notifyEnableMusic(final boolean audio) {
        if (CLIP != null) {
            if (audio) {
                CLIP.start();
                CLIP.loop(Clip.LOOP_CONTINUOUSLY);
            } else {
                CLIP.stop();
            }
        }
    }

}
