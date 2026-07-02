package javagotchi.view.minigame.component;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.Icon;
import javax.swing.JButton;

import javagotchi.controller.minigame.main.MiniGame;
import javagotchi.utility.Utility;

/**
 * 
 * @author marica
 *
 */
public class MusicButton extends JButton implements ActionListener {

    private static final long serialVersionUID = -6243052323881090693L;
    private static final String MUSICLAB = "Music";

    private static final String PATHON = "/minigame/audio/MusicOn.png";
    private static final String PATHOFF = "/minigame/audio/MusicOff.png";
    private static final int WIDTH = 25;
    private static final int HEIGHT = 25;
    private static final Icon ON = Utility.createIcon(PATHON, WIDTH, HEIGHT);
    private static final Icon OFF = Utility.createIcon(PATHOFF, WIDTH, HEIGHT);

    /**
     * Constructor for the MusicButton.
     */
    public MusicButton() {
        super();
        this.setText(MUSICLAB);
        this.addActionListener(this);
    }

    /**
     * Sets a different MusicButton icon depending on whether the music is active or
     * not.
     */
    public void setIconMusic() {
        if (MiniGame.getFactoryController().getMusic().isOn()) {
            this.setIcon(ON);
        } else {
            this.setIcon(OFF);
        }
    }

    @Override
    public final void actionPerformed(final ActionEvent e) {

        if (!MiniGame.getFactoryController().getMusic().isOn()) {
            MiniGame.getFactoryController().getMusic().startAudio();
            Utility.log("Click Music: Enable ...");
            this.setIcon(ON);
        } else {
            MiniGame.getFactoryController().getMusic().stopAudio();
            Utility.log("Click Music: Disenable ...");
            this.setIcon(OFF);
        }
    }

}
