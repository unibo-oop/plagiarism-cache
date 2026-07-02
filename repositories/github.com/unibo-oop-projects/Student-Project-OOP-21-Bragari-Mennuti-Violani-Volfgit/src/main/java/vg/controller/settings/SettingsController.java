package vg.controller.settings;

import vg.controller.Controller;
import vg.sound.SoundEffectImpl;
import vg.sound.manager.ESoundBackground;
import vg.sound.manager.SoundManager;
import vg.utils.path.PathSound;
import vg.view.ViewManager;
import vg.view.settings.SettingView;
import vg.view.utils.KeyAction;

public class SettingsController extends Controller<SettingView> {
    /**
     * Setting selection cursor.
     */
    private int idxSelection = 0;
    private final SoundManager soundManager;
    private boolean songIsOn = true;
    private boolean effectIsOn = true;

    public SettingsController(final SettingView view, final ViewManager viewManager, final SoundManager soundManager) {
        super(view, viewManager);
        this.getView().getViewController().highlightSelectedButton(idxSelection);
        this.soundManager = soundManager;
    }

    /**
     * Depending on selected button start corresponding view.
     */
    private void editSelectedSetting() {
        if (idxSelection == SettingOption.SONG.ordinal()) {
            toggleSong();
        } else if (idxSelection == SettingOption.EFFECTS.ordinal()) {
            toggleEffects();
        } else if (idxSelection == SettingOption.CLOSE_SETTING.ordinal()) {
            this.getViewManager().popView();
        }
    }

    /**
     * Toggle from mute and unmute state of sound.
     */
    private void toggleSong() {
        this.songIsOn = !songIsOn;
        this.getView().getViewController().changeMusicStateON(this.songIsOn);
        if (!this.songIsOn) {
            this.soundManager.stopBackground();
        } else {
            this.soundManager.playBackground(ESoundBackground.START);
        }
    }

    /**
     * Toggle from mute and unmute state of sound.
     */
    private void toggleEffects() {
        this.effectIsOn = !effectIsOn;
        this.getView().getViewController().changeEffectStateON(this.effectIsOn);
    }

    @Override
    public void keyTapped(final KeyAction k) {
        if (k == KeyAction.DOWN && idxSelection < SettingOption.values().length-1) {
            idxSelection++;
        } else if (k == KeyAction.UP && idxSelection > 0) {
             idxSelection--;
        } else if (k == KeyAction.ENTER) {
            editSelectedSetting();
        }
        this.getView().getViewController().highlightSelectedButton(idxSelection);
    }

    @Override
    public void keyPressed(final KeyAction k) {
        keyTapped(k);
    }

    @Override
    public void keyReleased(final KeyAction k) {
    }
}
