package thatlevelagain.view.various_jbutton;

import javax.swing.JDialog;

import thatlevelagain.sound.SoundManager;
import thatlevelagain.sound.SoundPath;
import thatlevelagain.view.state.various_state.LevelStateGeneral;

/**
 * 
 * light button in menu.
 *
 */
public class Light extends ButtonGeneral {
    /**
     * 
     */
    private static final long serialVersionUID = 1L;
    private static final String NAME = "LIGHT ON";
    /**
     * constructor.
     * @param level 
     *         level for light on/off
     * @param dialog
     *         dialog to close
     */
    public Light(final LevelStateGeneral level, final JDialog dialog) {
        super(NAME);
        this.addActionListener(e -> {
            level.getMap().setLights(false);
            SoundManager.getListLoader().get(SoundPath.CLICKYPATH.getPosition()).play();
            dialog.setVisible(false);
            dialog.dispose();
            level.setImpOpen(false);
        });
    }

}
