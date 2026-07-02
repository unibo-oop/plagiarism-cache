package thatlevelagain.view.various_jbutton;

import javax.swing.JDialog;

import thatlevelagain.sound.SoundManager;
import thatlevelagain.sound.SoundPath;
import thatlevelagain.view.state.various_state.LevelStateGeneral;

/**
 * 
 * door unlock button in menu.
 *
 */
public class DoorUnlock extends ButtonGeneral {
    /**
     * 
     */
    private static final long serialVersionUID = 1L;
    private static final String NAME = "OPEN DOOR";
    /**
     * constructor.
     * @param level
     *         level details 
     * @param dialog
     *         dialog to close
     */
    public DoorUnlock(final LevelStateGeneral level, final JDialog dialog) {
        super(NAME);
        level.getManager();
        this.addActionListener(e -> {
            if (!level.getMap().getDoor().isOpen()) {
                SoundManager.getListLoader().get(SoundPath.DOORSQUEAKPATH.getPosition()).play();
                level.getMap().getDoor().setOpen(true);
            }
            dialog.setVisible(false);
            dialog.dispose();
            level.setImpOpen(false);
        });
    }
}
