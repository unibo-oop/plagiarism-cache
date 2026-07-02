package thatlevelagain.view.various_jbutton;

import java.io.IOException;

import javax.swing.JDialog;

import thatlevelagain.view.state.GameStateManagerImpl;
import thatlevelagain.view.state.various_state.LevelStateGeneral;
import thatlevelagain.view.state.various_state.level.Level7;

/**
 * 
 * levels button in menu.
 *
 */
public class Levels extends ButtonGeneral implements Runnable {
    /**
     * 
     */
    private static final long serialVersionUID = 1L;
    private static final String NAME = "SKIP LEVEL";
    private static final int ACTUAL_LEVEL = 6;
    private final LevelStateGeneral level;
    private final Thread th;
    private boolean go;
    private final JDialog dialog;
    /**
     * constructor.
     * @param level 
     *         level
     * @param dialog
     *         panel
     */
    public Levels(final LevelStateGeneral level, final JDialog dialog) {
        super(NAME);
        this.level = level;
        this.dialog = dialog;
        this.th = new Thread(this);
        this.go = false;
        this.addActionListener(e -> {
            nextLevel();
        });
    }

    private void nextLevel() {
        th.start();
        this.level.getManager().getStates().add(new Level7(this.level.getManager()));
        this.go = true;
    }

    @Override
    public final void run() {
        dialog.setVisible(false);
        dialog.dispose();
        level.setImpOpen(false);
        this.level.getManager().getStates().get(GameStateManagerImpl.PAUSE_LEVEL).setLevelIndex(ACTUAL_LEVEL + 1);
        try {
             this.level.getManager().setState(GameStateManagerImpl.PAUSE_LEVEL);
        } catch (IOException e1) {
             e1.printStackTrace();
        }
        try {
            Thread.sleep(GameStateManagerImpl.TIMEWAIT);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        while (!this.go) {
            aspetta();
        }
        try {
             this.level.getManager().setState(GameStateManagerImpl.LEVEL7);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    private void aspetta() { }
}
