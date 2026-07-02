package vg.view.player;

import javafx.geometry.Dimension2D;
import vg.utils.V2D;
import vg.utils.path.PathImagePlayer;
import vg.view.entity.EntityBlockImpl;

import java.util.List;


public class PlayerViewControllerImpl extends EntityBlockImpl implements PlayerViewController {
    private static final Dimension2D SIZE_PLAYER = new Dimension2D(40, 40);
    private static final V2D INIT_POSITION_PLAYER = new V2D(0, 0);
    public PlayerViewControllerImpl() {
        super(INIT_POSITION_PLAYER, SIZE_PLAYER, PathImagePlayer.PLAYER);
        this.setImageOverlay(PathImagePlayer.SHIELD);
    }

    @Override
    public void showShield() {
        this.showImageOverlay();
    }

    @Override
    public void hideShield() {
        this.hideImageOverlay();
    }
}