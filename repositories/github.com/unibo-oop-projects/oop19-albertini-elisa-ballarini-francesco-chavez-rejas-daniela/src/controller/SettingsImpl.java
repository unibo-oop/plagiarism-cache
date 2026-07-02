package controller;

import java.util.List;
import java.util.Optional;

import factory.EnumFactory;
import level.Levels;
import manager.ControllerManager;
import piece.Piece;
import utility.Utils;
import utility.UtilsPlayer;
import view.ViewSettingsImpl;

/**
 * Class that implements {@linkplain ControllerSettings}.
 * 
 * @see ControllerSettings.
 */
public class SettingsImpl implements ControllerSettings {

    private final ControllerManager manager;

    /**
     * @param manager : controller manager of the application.
     */
    public SettingsImpl(final ControllerManager manager) {
        this.manager = manager;
        this.start();
        this.manager.setView(new ViewSettingsImpl(this));
    }

    private void start() {
        this.manager.setTempCustom(Optional.empty());
        this.manager.setSpeed(Utils.getDefaultSpeed());
    }

    @Override
    public final void chageController(final EnumFactory type) {
        this.manager.setController(type);
    }

    @Override
    public final void setStartLevel(final Levels startLevel) {
        this.manager.setSpeed(startLevel);
    }

    @Override
    public final boolean isPlayerPresent() {
        return this.manager.getPlayer().isPresent();
    }

    @Override
    public final void setCustomList() {
        this.manager.setTempCustom(this.getCustomList());
    }

    private Optional<List<Piece>> getCustomList() {
        Optional<List<Piece>> tempList = Optional.empty();

        if (this.manager.getPlayer().isPresent()) {
            if (this.manager.getPlayer().get().getCustomPiece().isPresent()) {
                tempList = Optional.of(UtilsPlayer.getBlockList(this.manager.getPlayer().get().getCustomPiece().get()));
            }
        } else {
            if (this.manager.getRtCustom().isPresent()) {
                tempList = this.manager.getRtCustom();
            }
        }
        return tempList;
    }

    @Override
    public final ControllerManager getManager() {
        return this.manager;
    }

    @Override
    public final boolean checkCustomsPresent() {
        return this.getCustomList().isPresent();
    }

}
