package model.pkglevels;

import java.util.Arrays;

import model.pkgplayer.Player;
import view.GameLevelImpl;
import view.GameWindow;

/**
 * Standard game level, this class extends the GameLevelImpl.
 * 
 * 
 *
 */
public class StandardGameLevel extends GameLevelImpl {
    /**
     * 
     */
    private static final long serialVersionUID = 1L;

    /**
     * Constructor.
     * 
     * @param player
     *            current player
     * @param game
     *            gameWindow
     * @param currLvl
     *            level number
     * @param custom
     *            is a custom level(always false in standard level);
     */
    public StandardGameLevel(final Player player, final GameWindow game, final int currLvl, final boolean custom) {
        super(player, game, currLvl, custom);

    }

    @Override
    public void loadImages() {
        super.loadImages();
        final Pipe t5 = super.getTube("SV");
        final Pipe t6 = super.getTube("SO");
        super.getTube("CD").addPipes(super.getTube("CL")); //calls the superclass method to obtain teh pipe
        t5.addPipes(t6);
        t6.addPipes(t5);
        super.getAllTubes().addAll(Arrays.asList(t5, t6));
    }

}
