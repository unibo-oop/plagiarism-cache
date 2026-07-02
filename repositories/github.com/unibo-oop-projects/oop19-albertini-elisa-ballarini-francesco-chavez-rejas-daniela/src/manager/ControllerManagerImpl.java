package manager;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import factory.EnumFactory;
import factory.FactoryController;
import level.Levels;
import login.Player;
import piece.Piece;
import utility.Utils;
import view.View;

/**
 * Class implementation of {@link ControllerManager}.
 * @see ControllerManager.
 */
public class ControllerManagerImpl implements ControllerManager {

    private Levels speed = Utils.getDefaultSpeed();
    private final ViewManager viewManager;
    private Optional<Player> player;
    private Optional<List<Piece>> tempCustom;
    private Optional<List<Piece>> rtCustom;

    /**
     * 
     */
    public ControllerManagerImpl() {
        this.viewManager = new ViewManagerImpl();
        this.player = Optional.empty();
        this.tempCustom = Optional.empty();
        this.rtCustom = Optional.empty();
        this.setController(Utils.getDefaultController());
    }

    @Override
    public final void setController(final EnumFactory typeController) {
        FactoryController.createController(typeController, this);
    }

    @Override
    public final void setView(final View view) {
        this.viewManager.setView(view);
    }

    @Override
    public final Levels getSpeedLevel() {
        return speed;
    }

    @Override
    public final void setSpeed(final Levels speedLevel) {
        this.speed = speedLevel;
    }

    @Override
    public final void setPlayer(final Optional<Player> loggedPlayer) {
        this.player = loggedPlayer;
    }

    @Override
    public final Optional<Player> getPlayer() {
        return this.player;
    }

    @Override
    public final Optional<List<Piece>> getTempCustom() {
        return this.tempCustom;
    }

    @Override
    public final void initRtCustomList() {
        this.rtCustom = Optional.of(new ArrayList<>());
    }

    @Override
    public final Optional<List<Piece>> getRtCustom() {
        return this.rtCustom;
    }

    @Override
    public final void setTempCustom(final Optional<List<Piece>> gameStartList) {
        this.tempCustom = gameStartList;
    }

}
